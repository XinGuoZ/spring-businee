package cc.tg.tools;

import cc.tg.config.exception.SystemErrorType;
import cc.tg.model.vo.ResultVO;
import lombok.experimental.UtilityClass;


@UtilityClass
public class ResultUtil {

    public ResultVO success(Object object) {
        ResultVO result = new ResultVO();
        result.setCode("0");
        result.setMessage("ok");
        result.setData(object);
        return result;
    }
    public ResultVO success(String msg) {
        ResultVO result = new ResultVO();
        result.setCode("0");
        result.setMessage("msg");
        return result;
    }
    public ResultVO success(String msg, Object object) {
        ResultVO result = new ResultVO();
        result.setCode("0");
        result.setMessage(msg);
        result.setData(object);
        return result;
    }


    public ResultVO success() {
        return success(null);
    }


    public ResultVO error(SystemErrorType error) {
        ResultVO result = new ResultVO();
        result.setCode(error.getCode());
        result.setMessage(error.getMsg());
        return result;
    }

    public ResultVO error(String code, String msg) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public ResultVO error(String msg) {
        return error("-1", msg);
    }

    public ResultVO error() {
        return error(SystemErrorType.SYSTEM_ERROR);
    }
}
