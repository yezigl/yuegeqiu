/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.mis.holder;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.yueqiu.mis.model.AclUser;

/**
 * description here
 *
 * @author yezi
 * @since 2015年3月22日
 */
public class AclUserContext {

    private static final String CONTEXT_USER = "context_user";

    public static AclUser getUser() {
        return (AclUser) RequestContextHolder.getRequestAttributes().getAttribute(CONTEXT_USER,
                RequestAttributes.SCOPE_REQUEST);
    }

    public static void setUser(AclUser user) {
        RequestContextHolder.getRequestAttributes().setAttribute(CONTEXT_USER, user, RequestAttributes.SCOPE_REQUEST);
    }
}
