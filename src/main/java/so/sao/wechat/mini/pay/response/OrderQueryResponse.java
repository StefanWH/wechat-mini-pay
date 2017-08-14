package so.sao.wechat.mini.pay.response;

import so.sao.wechat.mini.pay.enums.WechatTradeState;

public class OrderQueryResponse extends MicropayResponse {

    private WechatTradeState tradeState;

    private Integer couponCount;

    private String tradeStateDesc;

    public WechatTradeState getTradeState() {
        return tradeState;
    }

    public void setTradeState(WechatTradeState tradeState) {
        this.tradeState = tradeState;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }
}
