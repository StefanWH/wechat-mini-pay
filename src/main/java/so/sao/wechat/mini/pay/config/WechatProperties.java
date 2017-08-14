package so.sao.wechat.mini.pay.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "shop.wechat")
public class WechatProperties {

    /**
     * 微信公众号的appid
     */
    private String paymentAppId;

    /**
     * 微信支付商户号
     */
    private String mchId;

    private String serviceHost;

    private String enterpriseHost;

    private String paymentHost;

    private String paymentSecret;

    private String paymentKey;

    private String clientIp;

    private String notifyUrl;

    private Resource certFile;

    //小程序退款证书
    private Resource mircoProgramCertFile;

    //小程序商户号
    private String mircoProgramMchId;

    //小程序appid
    private String mircoAppId;

    //小程序私钥
    private String mircoPaymentKey;

    public String getMircoAppId() {
        return mircoAppId;
    }

    public void setMircoAppId(String mircoAppId) {
        this.mircoAppId = mircoAppId;
    }

    public String getMircoPaymentKey() {
        return mircoPaymentKey;
    }

    public void setMircoPaymentKey(String mircoPaymentKey) {
        this.mircoPaymentKey = mircoPaymentKey;
    }

    public String getMircoProgramMchId() {
        return mircoProgramMchId;
    }

    public void setMircoProgramMchId(String mircoProgramMchId) {
        this.mircoProgramMchId = mircoProgramMchId;
    }

    public Resource getMircoProgramCertFile() {
        return mircoProgramCertFile;
    }

    public void setMircoProgramCertFile(Resource mircoProgramCertFile) {
        this.mircoProgramCertFile = mircoProgramCertFile;
    }

    public String getServiceHost() {
        return serviceHost;
    }

    public void setServiceHost(String serviceHost) {
        this.serviceHost = serviceHost;
    }

    public String getEnterpriseHost() {
        return enterpriseHost;
    }

    public void setEnterpriseHost(String enterpriseHost) {
        this.enterpriseHost = enterpriseHost;
    }

    public String getPaymentHost() {
        return paymentHost;
    }

    public void setPaymentHost(String paymentHost) {
        this.paymentHost = paymentHost;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getPaymentAppId() {
        return paymentAppId;
    }

    public void setPaymentAppId(String paymentAppId) {
        this.paymentAppId = paymentAppId;
    }

    public String getPaymentSecret() {
        return paymentSecret;
    }

    public void setPaymentSecret(String paymentSecret) {
        this.paymentSecret = paymentSecret;
    }

    public String getPaymentKey() {
        return paymentKey;
    }

    public void setPaymentKey(String paymentKey) {
        this.paymentKey = paymentKey;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public Resource getCertFile() {
        return certFile;
    }

    public void setCertFile(Resource certFile) {
        this.certFile = certFile;
    }
}
