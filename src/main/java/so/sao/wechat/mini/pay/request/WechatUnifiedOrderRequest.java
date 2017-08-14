package so.sao.wechat.mini.pay.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 微信小程序在线订单支付请求
 * */
public class WechatUnifiedOrderRequest extends WechatPaymentRequest {

    @JacksonXmlCData
    private String openid;

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
    private String timeStart;

    @JacksonXmlCData
    private String timeExpire;

    @JacksonXmlCData
    private String goodsTag;

    @JacksonXmlCData
    private String notifyUrl;

    @JacksonXmlCData
    private String tradeType;

    @JacksonXmlCData
    private String limitPay;

    @JacksonXmlCData
    private String partnerid;

    @JacksonXmlCData
    private String prepayid;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "package")
    private String extend;

    private String timestamp;

//    private String paymentKey;
//
//    public String getPaymentKey() {
//        return paymentKey;
//    }
//
//    public void setPaymentKey(String paymentKey) {
//        this.paymentKey = paymentKey;
//    }

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

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }


}
