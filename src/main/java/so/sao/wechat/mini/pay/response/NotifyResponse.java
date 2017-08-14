package so.sao.wechat.mini.pay.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "xml")
public class NotifyResponse {

    @JacksonXmlCData
    private WechatResultCode returnCode;

    @JacksonXmlCData
    private String returnMsg;

    public WechatResultCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(WechatResultCode returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

}
