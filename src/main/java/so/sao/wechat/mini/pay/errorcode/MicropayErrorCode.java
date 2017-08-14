package so.sao.wechat.mini.pay.errorcode;

import org.springframework.http.HttpStatus;

public enum MicropayErrorCode implements ErrorCode {

    SYSTEMERROR(true), // 接口返回错误-系统超时
    PARAM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, PaymentErrorCode.PARAMETER_ERROR),  //请求参数未按指引进行填写
    ORDERPAID(HttpStatus.BAD_REQUEST, PaymentErrorCode.ORDER_PAID),  // 订单号重复
    NOAUTH(HttpStatus.INTERNAL_SERVER_ERROR, PaymentErrorCode.MERCHANT_STATUS_ABNORMAL),  //商户没有开通被扫支付权限
    AUTHCODEEXPIRE(HttpStatus.BAD_REQUEST, PaymentErrorCode.AUTH_CODE_INVALID),  //用户的条码已经过期
    NOTENOUGH(HttpStatus.BAD_REQUEST, PaymentErrorCode.BUYER_BALANCE_NOT_ENOUGH),  //用户的零钱余额不足
    NOTSUPORTCARD(HttpStatus.BAD_REQUEST, PaymentErrorCode.CARD_NOT_SUPPORTED),  //用户使用卡种不支持当前支付形式
    ORDERCLOSED(HttpStatus.BAD_REQUEST, PaymentErrorCode.ORDER_CLOSED),  //该订单已关
    ORDERREVERSED(HttpStatus.BAD_REQUEST, PaymentErrorCode.ORDER_CANCELLED),  //当前订单已经被撤销
    BANKERROR(true),  //银行端超时
    USERPAYING(HttpStatus.ACCEPTED, PaymentErrorCode.WAITING_FOR_USER_PAYMENT, true),  //该笔交易因为业务规则要求，需要用户输入支付密码。
    AUTH_CODE_ERROR(HttpStatus.BAD_REQUEST, PaymentErrorCode.AUTH_CODE_INVALID),  //请求参数未按指引进行填写
    AUTH_CODE_INVALID(HttpStatus.BAD_REQUEST, PaymentErrorCode.AUTH_CODE_INVALID),  //收银员扫描的不是微信支付的条码
    XML_FORMAT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, PaymentErrorCode.PARAMETER_ERROR),  //XML格式错误
    REQUIRE_POST_METHOD(HttpStatus.INTERNAL_SERVER_ERROR, PaymentErrorCode.PARAMETER_ERROR),  //未使用post传递参数
    SIGNERROR(HttpStatus.INTERNAL_SERVER_ERROR, PaymentErrorCode.PARAMETER_ERROR),  //参数签名结果不正确
    LACK_PARAMS(HttpStatus.INTERNAL_SERVER_ERROR, PaymentErrorCode.PARAMETER_ERROR),  //缺少必要的请求参数
    NOT_UTF8(HttpStatus.INTERNAL_SERVER_ERROR, PaymentErrorCode.PARAMETER_ERROR),  //未使用指定编码格式
    BUYER_MISMATCH(HttpStatus.BAD_REQUEST, PaymentErrorCode.BUYER_NOT_MATCH),  //暂不支持同一笔订单更换支付方
    APPID_NOT_EXIST(HttpStatus.INTERNAL_SERVER_ERROR, PaymentErrorCode.PARAMETER_ERROR),  //参数中缺少APPID
    MCHID_NOT_EXIST(HttpStatus.INTERNAL_SERVER_ERROR, PaymentErrorCode.PARAMETER_ERROR),  // 参数中缺少MCHID
    OUT_TRADE_NO_USED(HttpStatus.INTERNAL_SERVER_ERROR, PaymentErrorCode.EXISTING_TRANSACTION),  //同一笔交易不能多次提交
    APPID_MCHID_NOT_MATCH(HttpStatus.INTERNAL_SERVER_ERROR, PaymentErrorCode.PARAMETER_ERROR);  //appid和mch_id不匹配

    private HttpStatus status;

    private PaymentErrorCode errorCode;

    private boolean orderQueryRequired = false;

    private MicropayErrorCode() {
    }

    private MicropayErrorCode(boolean orderQueryRequired) {
        this.orderQueryRequired = orderQueryRequired;
    }

    private MicropayErrorCode(HttpStatus status, PaymentErrorCode errorCode) {
        this(status, errorCode, false);
    }

    private MicropayErrorCode(HttpStatus status, PaymentErrorCode errorCode, boolean orderQueryRequired) {
        this.status = status;
        this.errorCode = errorCode;
        this.orderQueryRequired = orderQueryRequired;
    }

    @Override
    public int getId() {
        return errorCode.getId();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public boolean isOrderQueryRequired() {
        return orderQueryRequired;
    }

    //是否包含枚举项
    public static boolean contains(String name){
        //所有的枚举值
        MicropayErrorCode[] micropayErrorCodes = values();
        //遍历查找
        for(MicropayErrorCode mec : micropayErrorCodes){
            if(mec.name().equals(name)){
                return true;
            }
        }
        return false;
    }
}
