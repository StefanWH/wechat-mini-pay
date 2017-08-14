package so.sao.wechat.mini.pay.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.collect.Lists;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import so.sao.wechat.mini.pay.mapper.JsonMapper;
import so.sao.wechat.mini.pay.service.WechatTradeService;
import so.sao.wechat.mini.pay.util.XmlUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.List;


@Configuration
@EnableConfigurationProperties(WechatProperties.class)
@ConditionalOnProperty(prefix = "shop.wechat", name = { "payment-host", "mch-id", "payment-app-id", "payment-key" })
public class WechatPaymentAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatPaymentAutoConfiguration.class);

    @Autowired
    private WechatProperties properties;

    @Bean
    public XmlMapper wechatPaymentJacksonXmlMapper(Jackson2ObjectMapperBuilder builder) {

        XmlMapper wechatPaymentXmlMapper = Jackson2ObjectMapperBuilder.xml()
                .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE).build();
        builder.configure(wechatPaymentXmlMapper);

        XmlUtils.configureXmlMapperForSpeed(wechatPaymentXmlMapper);

        return wechatPaymentXmlMapper;
    }

    @Bean
    public JsonMapper wechatPaymentXmlMapper(Jackson2ObjectMapperBuilder builder) {
        return new JsonMapper(wechatPaymentJacksonXmlMapper(builder));
    }

    @Bean
    public WechatTradeService wechatTradeService(HttpClient httpClient, RestTemplateBuilder restTemplateBuilder,
                                                 Jackson2ObjectMapperBuilder xmlBuilder) throws Exception {

        List<HttpMessageConverter<?>> messageConverters = wechatPaymentHttpMessageConverters(xmlBuilder);

        WechatTradeService wechatTradeService = new WechatTradeService();

        ClientHttpRequestFactory requestFactory;
//        if (properties.getCertFile() == null) {
            requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//        } else {
//            requestFactory = new HttpComponentsClientHttpRequestFactory(wechatPaymentSecureHttpClient().getObject());
//        }

        RestTemplate wechatPaymentRestTemplate = restTemplateBuilder.rootUri(properties.getPaymentHost())
                .requestFactory(requestFactory).messageConverters(messageConverters).build();
        wechatTradeService.setWechatPaymentRestTemplate(wechatPaymentRestTemplate);
        wechatTradeService.setWechatPaymentXmlMapper(wechatPaymentXmlMapper(xmlBuilder));
        wechatTradeService.setWechatProperties(properties);


        return wechatTradeService;
    }

    private List<HttpMessageConverter<?>> wechatPaymentHttpMessageConverters(Jackson2ObjectMapperBuilder builder) {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter = new MappingJackson2XmlHttpMessageConverter(
                wechatPaymentJacksonXmlMapper(builder));
        return Lists.newArrayList(stringHttpMessageConverter, mappingJackson2XmlHttpMessageConverter);
    }

//    @Bean
//    @ConditionalOnProperty(prefix = "shop.wechat", name = "cert-file")
//    public HttpClientFactoryBean wechatPaymentSecureHttpClient() throws Exception {
//
//        try (InputStream input = properties.getCertFile().getInputStream()) {
//            KeyStore keyStore = KeyStore.getInstance("PKCS12");
//            char[] password = properties.getMchId().toCharArray();
//            keyStore.load(input, password);
//
//            HttpClientFactoryBean factoryBean = new HttpClientFactoryBean();
//            factoryBean.setKeyStore(keyStore);
//            factoryBean.setPassword(password);
//
//            return factoryBean;
//        }
//    }



}
