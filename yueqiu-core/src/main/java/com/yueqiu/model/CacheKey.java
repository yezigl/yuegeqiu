/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.model;

/**
 * description here
 *
 * @author yezi
 * @since 2015年2月26日
 */
public class CacheKey {

    public static String getMobileCaptchaKey(String mobile) {
        return "mobile_captcha_" + mobile;
    }
}
