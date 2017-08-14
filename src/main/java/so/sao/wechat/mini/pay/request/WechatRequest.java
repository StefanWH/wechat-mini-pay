package so.sao.wechat.mini.pay.request;

public interface WechatRequest {

    void setAppid(String appid);

    void setMchId(String mchId);

    void setNonceStr(String nonceStr);

    void setSign(String sign);
}
