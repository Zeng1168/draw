package utils;

/**
 * 规定:
 * #0表示成功
 * #1表示失败
 * #1001～1999 区间表示参数错误
 * #2001～2999 区间表示用户错误
 * #3001～3999 区间表示接口异常
 */

public enum ResultCode {
    /* 成功 */
    SUCCESS(0, "成功"),

    /* 默认失败 */
    ERROR(1, "失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(101, "参数无效"),
    PARAM_IS_BLANK(102, "参数为空"),
    PARAM_TYPE_ERROR(103, "参数类型错误"),
    PARAM_NOT_COMPLETE(104, "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN(201, "用户未登录！"),
    USER_ACCOUNT_EXPIRED(202, "登录过期！"),

    /* 业务错误 */
    NO_PERMISSION(301, "没有权限！");
    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 根据code获取message
     *
     * @param code
     * @return
     */
    public static String getMsgByCode(Integer code) {
        for (ResultCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMsg();
            }
        }
        return null;
    }
}
