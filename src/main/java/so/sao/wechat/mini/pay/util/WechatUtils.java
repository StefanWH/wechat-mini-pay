package so.sao.wechat.mini.pay.util;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.google.common.hash.Hashing;
import org.springframework.util.ReflectionUtils;
import so.sao.wechat.mini.pay.request.WechatResign;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class WechatUtils {

    private static final MapJoiner JOINER = Joiner.on("&").withKeyValueSeparator("=");

    private static final ConcurrentMap<Class<?>, PropertyDescriptor[]> PROPERTY_DESCRIPTORS_CACHE = new ConcurrentHashMap<>();

    /**
     * 微信支付签名算法
     * */
    public static String sign(Object request, String key) {

        //获得所有发送微信支付接口的参数
        PropertyDescriptor[] propertyDescriptors = PROPERTY_DESCRIPTORS_CACHE.computeIfAbsent(request.getClass(), clazz -> {
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
                return beanInfo.getPropertyDescriptors();
            } catch (IntrospectionException e) {
                throw new RuntimeException(e);
            }
        });

        SortedMap<String, Object> map = new TreeMap<>();

        //对所有非空参数按AscII码表排序，并拼接为URL键值对的格式
        for (PropertyDescriptor pd : propertyDescriptors) {
            //c端预支付后进行再签名时会有一个package字段
            if(pd.getName().equals("packagee")){pd.setName("package");}
            Object param = ReflectionUtils.invokeMethod(pd.getReadMethod(), request);
            if (param != null) {
                if(request instanceof WechatResign){
                    map.put(pd.getName(), param);
                }else {
                    map.put(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, pd.getName()), param);
                }
            }
        }


        //在最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue
        String s=JOINER.join(map) + "&key=" + key;
        return Hashing.md5().hashString(JOINER.join(map) + "&key=" + key, StandardCharsets.UTF_8).toString()
                .toUpperCase(Locale.ENGLISH);
    }

    public static void main(String []args) {
        System.out.print("goubi");
    }

}
