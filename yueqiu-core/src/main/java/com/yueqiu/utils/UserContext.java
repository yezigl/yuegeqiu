/**
 * Copyright 2014 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.yueqiu.entity.User;

/**
 * description here
 *
 * @author yezi
 * @since 2014年11月5日
 */
public class UserContext {

    private static final String CONTEXT_UID = "context_uid";

    private static final String CONTEXT_USER = "context_user";

    public static String getUid() {
        return (String) RequestContextHolder.getRequestAttributes().getAttribute(CONTEXT_UID,
                RequestAttributes.SCOPE_REQUEST);
    }

    public static void setUid(String uid) {
        RequestContextHolder.getRequestAttributes().setAttribute(CONTEXT_UID, uid, RequestAttributes.SCOPE_REQUEST);
    }

    public static User getUser() {
        return (User) RequestContextHolder.getRequestAttributes().getAttribute(CONTEXT_USER,
                RequestAttributes.SCOPE_REQUEST);
    }

    public static void setUser(User user) {
        RequestContextHolder.getRequestAttributes().setAttribute(CONTEXT_USER, user, RequestAttributes.SCOPE_REQUEST);
    }

    /**
     * @return
     */
    public static boolean isAuth() {
        return RequestContextHolder.getRequestAttributes().getAttribute(CONTEXT_USER, RequestAttributes.SCOPE_REQUEST) != null;
    }
}
