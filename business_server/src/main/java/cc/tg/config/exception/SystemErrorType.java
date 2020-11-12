package cc.tg.config.exception;


import lombok.Getter;

@Getter
public enum SystemErrorType implements ErrorType{

    SYSTEM_ERROR("-0001", "系统异常"),
    SYSTEM_BUSY("0001", "操作繁忙,请稍候再试"),
    ACCOUNT_PASSWORD_ERROR("0002", "账号不存在或者密码错误"),
    SERVER_FEIGN_ERROR("0003", "服务未连接"),
    CONNECT_TIMEOUT_ERROR("0004", "服务连接超时未连接"),
    NOT_LOGIN("0005", "未登入"),
    LOGIN_ERROR("0006", "登入失败"),
    INSUFFICIENT_PERMISSIONS("000007","权限不足"),
    LOGOUT_SUCCESS("0008","退出成功" ),
    REQUEST_FULL("0101", "参数为空"),
    ADD_ERROR("0102", "添加失败"),
    UPDATE_ERROR("0103", "更新失败"),
    DELETE_ERROR("0104", "删除失败"),
    STATUS_ERROR("0105", "token过期或者不存在"),
    REQUEST_ERROR("0106", "参数格式错误"),
    UPLOAD_FILE_FULL("0107", "上传文件不存在"),
    ARGUMENT_TYPE_MISMATCH("0108", "参数类型不匹配"),
    FORMAT_ERROR("0109", "参数格式有误"),
    NOT_EXIST_USERNAME("0105","用户名不存在,或者密码错误" ),
    USERNAME_DISABLE("0108","用户名禁用" ), ;

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    SystemErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static SystemErrorType getType(String code) {
        for (SystemErrorType e : SystemErrorType.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
