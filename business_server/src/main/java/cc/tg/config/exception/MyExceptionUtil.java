package cc.tg.config.exception;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * create by zhangxinguo on 2020/9/2 17:57
 */
@UtilityClass
public class MyExceptionUtil {


    public  void isNull(String str, SystemErrorType errorType) {
        if (StringUtils.isEmpty(str)) {
            throwMsj(errorType);
        }
    }

    public  void isNullObj(Object obj, SystemErrorType errorType) {
        if (Objects.isNull(obj)) {
            throwMsj(errorType);
        }
    }
    public  void isNotNull(List obj, SystemErrorType errorType) {
        if (obj.size()>0) {
            throwMsj(errorType);
        }
    }
    public void throwMsj(SystemErrorType s) {

        throw new MyException(s);
    }

    public  void isFalse(boolean isFlag, SystemErrorType errorType ) {
        if (!isFlag) {
            throwMsj(errorType);
        }
    }

    public void isTrue(boolean isFlag, SystemErrorType errorType) {
        if (isFlag) {
            throwMsj(errorType);
        }
    }
}
