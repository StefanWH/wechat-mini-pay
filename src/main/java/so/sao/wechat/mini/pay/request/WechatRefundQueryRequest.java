package so.sao.wechat.mini.pay.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

public class WechatRefundQueryRequest extends WechatPaymentRequest {

    @JacksonXmlCData
    private String transactionId;

    @JacksonXmlCData
    private String outTradeNo;

    @JacksonXmlCData
    private String outRefundNo;

    @JacksonXmlCData
    private String refundId;

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

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }
}
