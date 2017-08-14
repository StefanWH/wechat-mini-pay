package so.sao.wechat.mini.pay.request;

public class WechatDownloadBillRequest extends WechatPaymentRequest {

    private String billDate;

    private String billType;

    private String tarType;

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getTarType() {
        return tarType;
    }

    public void setTarType(String tarType) {
        this.tarType = tarType;
    }
}
