/**
 * Copyright 2013 Sohu.com Inc. All Rights Reserved.
 */
package com.yueqiu.res;

/**
 * description here
 *
 * @author lukeli
 * @version 1.0 2013-11-22
 */
public enum Status {

    ERROR(-1, "undefined error"),
    
    // 参数类错误
    PARAM_ERROR(400, "参数(%s)错误"),
    USER_REGISTERED(400, "用户已被注册"),
    USER_NOT_EXIST(400, "用户不存在"),
    USERNAME_OR_PASSWORD_ERROR(400, "用户名或密码错误"),
    CAPTCHA_ERROR(400, "验证码错误"),
    ERROR_400(400, "%s"),
    // 未认证
    USER_NOT_LOGIN(401, "用户未登录"),
    // 禁止访问
    FORBIDEEN(403, "APP不存在"),
    // 不存在
    NOT_EXIST(404, "%s"),
    SERVER_ERROR(500, "%s");

    private int code;
    private String msg;

    Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }
}
