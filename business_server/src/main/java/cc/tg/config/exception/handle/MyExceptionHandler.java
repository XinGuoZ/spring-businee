package cc.tg.config.exception.handle;



import cc.tg.config.exception.SystemErrorType;
import cc.tg.model.vo.ResultVO;
import cc.tg.tools.ResultUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

/**
 * @Invoicequeryor lgt
 * Date: Created in 2019-11-08 14:42
 * Utils: Intellij Idea
 * Description: 全局异常捕获
 */

@Slf4j

@RestControllerAdvice
public class MyExceptionHandler {

    /**
     * 用于处理通用异常(主要用于参数校验)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultVO bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        log.error("【参数校验异常】{}", e.getMessage());
        return ResultUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * 参数格式错误
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVO httpMessageNotReadable(HttpMessageNotReadableException e) {
        log.error("【jackson反序列化异常】{}", e.getMessage());
        return ResultUtil.error(SystemErrorType.FORMAT_ERROR);
    }

    /**
     * NonNull注解的参数空错误
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVO httpMessageNotReadable(IllegalArgumentException e) {
        log.error("【jackson反序列化异常】{}", e.getMessage());
        return ResultUtil.error(SystemErrorType.REQUEST_FULL);
    }
/*

    */
/**
     * rpc服务异常
     *//*

    @ExceptionHandler(RpcException.class)
    public ResultVO httpMessageNotReadable(RpcException e) {
        log.error("【rpc服务异常】{}", e.getMessage());
        return ResultUtil.error(SystemErrorType.RPC_SERVICE_VALID);
    }
*/

    /**
     * 参数格式错误
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultVO methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("【参数格式错误】{}", e.getMessage());
        return ResultUtil.error(SystemErrorType.ARGUMENT_TYPE_MISMATCH);
    }


    /*@ExceptionHandler(value = {ConnectTimeoutException.class})
    public ResultVO handle(ConnectTimeoutException ex) {
        log.error("connect timeout exception, {}", ex.getMessage());
        return ResultUtil.error(SystemErrorType.CONNECT_TIMEOUT_ERROR);
    }


    @ExceptionHandler(value = {MsjException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResultVO handle(MsjException ex) {
        log.error("业务异常:", ex);
        return ResultUtil.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResultVO handle(RuntimeException ex) {
        String message = ex.getMessage();
        String[] messages = message.split(":");
        if (BlankUtil.isNotBlank(messages) && messages.length != 3 && "com.netflix.client.ClientException".equals(messages[0])) {
            log.error("{}服务未连接", messages[2]);
            return ResultUtil.error(SystemErrorType.SERVER_FEIGN_ERROR);
        }
        log.error("未知异常:", ex);
        return ResultUtil.error(SystemErrorType.SYSTEM_ERROR);

    }*/

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO handle(Exception ex) {
        log.error("系统异常:", ex);
        return ResultUtil.error(ex.getMessage());
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO throwable() {
        log.error("throwable异常");
        return ResultUtil.error();
    }
}
