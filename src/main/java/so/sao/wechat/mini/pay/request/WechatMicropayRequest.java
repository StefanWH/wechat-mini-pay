package so.sao.wechat.mini.pay.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

public class WechatMicropayRequest extends WechatPaymentRequest {

    @JacksonXmlCData
    private String deviceInfo;

    @JacksonXmlCData
    private String body;

    @JacksonXmlCData
    private String detail;

    @JacksonXmlCData
    private String attach;

    @JacksonXmlCData
    private String outTradeNo;

    private Integer totalFee;

    @JacksonXmlCData
    private String feeType;

    @JacksonXmlCData
    private String spbillCreateIp;

    @JacksonXmlCData
    private String goodsTag;

    @JacksonXmlCData
    private String authCode;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
