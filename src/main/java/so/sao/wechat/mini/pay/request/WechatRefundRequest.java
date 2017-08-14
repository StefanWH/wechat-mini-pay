package so.sao.wechat.mini.pay.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

public class WechatRefundRequest extends WechatPaymentRequest {

    @JacksonXmlCData
    private String transactionId;

    @JacksonXmlCData
    private String outTradeNo;

    @JacksonXmlCData
    private String outRefundNo;

    private Integer totalFee;

    private Integer refundFee;

    @JacksonXmlCData
    private String refundFeeType;

    @JacksonXmlCData
    private String opUserId;

    @JacksonXmlCData
    private String refundAccount;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public String getRefundFeeType() {
        return refundFeeType;
    }

    public void setRefundFeeType(String refundFeeType) {
        this.refundFeeType = refundFeeType;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }
}
