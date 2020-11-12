package cc.tg.config.exception;

import lombok.Getter;

@Getter
public class MyException extends RuntimeException {

    /**
     * 异常对应的错误类型
     */
    private final String code;

    private final String message;

    /**
     * 默认是系统异常
     */
    public MyException() {
        this.code = SystemErrorType.SYSTEM_ERROR.getCode();
        this.message = SystemErrorType.SYSTEM_ERROR.getMsg();
    }

    public MyException(ErrorType errorType) {
        super(errorType.getMsg());
        this.code = errorType.getCode();
        this.message = errorType.getMsg();
    }

    public MyException(ErrorType errorType, String message) {
        super(message);
        this.code = errorType.getCode();
        this.message = errorType.getMsg();
    }

    public MyException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public MyException(ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.code = errorType.getCode();
        this.message = errorType.getMsg();
    }

    public MyException(String message) {
        super(message);
        this.code = SystemErrorType.SYSTEM_ERROR.getCode();
        this.message = message;
    }
}
