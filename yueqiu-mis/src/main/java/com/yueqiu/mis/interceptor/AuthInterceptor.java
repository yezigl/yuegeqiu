/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.mis.interceptor;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yueqiu.mis.holder.AclUserContext;
import com.yueqiu.mis.model.AclUser;
import com.yueqiu.mis.service.AclUserService;
import com.yueqiu.mis.utils.CookieUtils;

/**
 * description here
 *
 * @author yezi
 * @since 2015年3月21日
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final String NO_LOGIN_REDIRECT_URL = "/login";
    private static final String INDEX_URI = "/";

    @Resource
    AclUserService aclUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = ((HttpServletRequest) request).getRequestURI();
        String ru = null;
        if (!uri.equals(INDEX_URI)) {
            ru = uri + (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        }

        AclUser aclUser = CookieUtils.getLoginUser(request);
        if (aclUser != null) {
            aclUser = aclUserService.get(aclUser.getId());
        }
        if (aclUser == null) {
            if (ru == null) {
                response.sendRedirect(NO_LOGIN_REDIRECT_URL);
            } else {
                response.sendRedirect(NO_LOGIN_REDIRECT_URL + "?ru=" + URLEncoder.encode(ru, "utf-8"));
            }
            return true;
        }
        AclUserContext.setUser(aclUser);

        return true;
    }

}
