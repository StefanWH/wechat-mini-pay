package so.sao.wechat.mini.pay.response;

public abstract class WechatResponse {

    private WechatResultCode returnCode;

    private String returnMsg;

    private String deviceInfo;

    private String nonceStr;

    private String sign;

    private WechatResultCode resultCode;

    private String errCode;

    private String errCodeDes;

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

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public WechatResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(WechatResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public boolean isSuccess() {
        return returnCode == WechatResultCode.SUCCESS && resultCode == WechatResultCode.SUCCESS;
    }
}
