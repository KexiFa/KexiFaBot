package com.kexifa.entity;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/6/29 17:12
 */
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(200, "操作成功。"),

    /* 失败状态码 */
    FAILED(400, "操作失败。"),

    /* 参数错误：1001~1999 */
    PARAM_IS_INVALID(1001, "参数无效。"),
    PARAM_IS_BLANK(1002, "参数为空。"),
    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误。"),
    PARAM_NOT_COMPLETE(1004, "参数缺失。"),

    /* 用户错误：2001~2999 */
    USER_NOT_LOGGED_IN(2001, "用户未登录，请登录。"),
    USER_LOGIN_ERROR(2002, "登录密码错误次数超过%d次，请在%d分钟后继续尝试！"),
    USER_ACCOUNT_EXISED(2003, "账套编号不存在。"),
    USER_ACCOUNT_FORBINDDEN(2004, "账套已失效，请联系管理员。"),
    USER_NOT_EXIST(2005, "该账套下无此账号，请核实。"),
    USER_IS_DEACTIVATE(2014, "该账套下此账号已被停用"),
    USER_HAS_EXISTED(2006, "用户已存在。"),
    USER_PASSWORD_ERROR(2007, "密码错误。"),
    USER_CAPTCHA_CHECK_ERROR(2008, "行为验证码校验失败。"),
    USER_TOKEN_FORBIDDEN_CODE(2009, "用户令牌错误或已过期！"),
    USER_IS_NOT_EXIST(2010, "用户不存在"),
    USER_SMS_VERIFICATION_CODE_EXPIRED(2011, "短信验证码已过期。"),
    USER_SMS_VERIFICATION_CODE_ERROR(2012, "短信验证码输入错误。"),
    USER_SMS_PERVENT_ERROR(2013, "短信验证码获取频繁，请稍后重试。"),

    /* File错误：4001~4999 */
    FILE_UPLOAD_ERROR(4001, "文件上传异常"),

    /*数据问题*/
    CODE_OVERDUE(5001, "二维码过期"),
    NOT_IN_WHILELIST(5002, "用户不在白名单内"),

    ROMOTE_SERVER_EXCEPTION(6001, "远程服务器链接异常");


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
}
