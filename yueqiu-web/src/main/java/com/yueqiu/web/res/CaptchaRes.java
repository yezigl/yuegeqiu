/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.res;

/**
 * description here
 *
 * @author yezi
 * @since 2015年2月26日
 */
public class CaptchaRes extends Res {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
