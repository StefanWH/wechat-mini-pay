package so.sao.wechat.mini.pay.exception;

import org.springframework.http.HttpStatus;
import so.sao.wechat.mini.pay.errorcode.ErrorCode;


public class WechatException extends ShopRuntimeException {

    private static final long serialVersionUID = 1L;

    public WechatException(HttpStatus status, ErrorCode code, String message) {
        super(status, code, message);
    }

    public WechatException(HttpStatus status, int code, String message) {
        super(status, code, message);
    }
}
