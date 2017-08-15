package so.sao.wechat.mini.pay.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;


@ConditionalOnProperty(prefix = "wechat.pay", name = {"app-id", "mch-id", "mch-key"})
public class WxPayAutoConfiguration {

}
