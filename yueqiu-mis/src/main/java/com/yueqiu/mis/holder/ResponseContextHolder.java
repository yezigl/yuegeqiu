/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.mis.holder;

import javax.servlet.ServletResponse;

/**
 * description here
 *
 * @author yezi
 * @since 2015年3月20日
 */
public class ResponseContextHolder {

    static ThreadLocal<ServletResponse> responseLocal = new ThreadLocal<>();

    public static void set(ServletResponse response) {
        responseLocal.set(response);
    }

    public static ServletResponse getResponse() {
        return responseLocal.get();
    }
}
