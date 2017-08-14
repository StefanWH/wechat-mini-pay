package so.sao.wechat.mini.pay.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import so.sao.wechat.mini.pay.errorcode.ErrorCode;


public class ShopRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus status;

    private Integer code;

//    private MessageCode messageCode;
//
//    private Object[] args;

    public ShopRuntimeException(String message) {
        super(message);
        this.code = 0;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ShopRuntimeException(HttpStatus status) {
        this.status = status;
    }

    /**
     * 默认的 HttpStatus 是400
     * @param errorCode
     * @param message
     */
    public ShopRuntimeException(ErrorCode errorCode, String message) {
        this(HttpStatus.BAD_REQUEST, errorCode, message);
    }

    public ShopRuntimeException(HttpStatus status, ErrorCode errorCode, String message) {
        this(status, errorCode, message, null);
    }

    public ShopRuntimeException(HttpStatus status, Integer code, String message) {
        this(status, code, message, null);
    }

    public ShopRuntimeException(HttpStatus status, ErrorCode errorCode, String message, Throwable cause) {
        this(status, errorCode.getId(), message, cause);
    }

    public ShopRuntimeException(HttpStatus status, Integer code, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

//    @Override
//    public String getMessage() {
//        return getMessage(null);
//    }
//
//    private String getMessage(Locale locale) {
//        if (messageCode == null) {
//            return super.getMessage();
//        } else {
//            return messageCode.getMessage(locale, args);
//        }
//    }

    public void logException(Logger logger) {
//        String message = getMessage(Locale.ENGLISH);
        if (status.is5xxServerError()) {
            logger.error(getMessage(), this);
        } else if (StringUtils.isNotEmpty(getMessage())) {
            logger.warn(getMessage());
        }
    }
}

