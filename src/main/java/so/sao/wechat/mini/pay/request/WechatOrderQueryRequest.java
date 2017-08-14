package so.sao.wechat.mini.pay.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

public class WechatOrderQueryRequest extends WechatPaymentRequest {

    @JacksonXmlCData
    private String transactionId;

    @JacksonXmlCData
    private String outTradeNo;

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
}
