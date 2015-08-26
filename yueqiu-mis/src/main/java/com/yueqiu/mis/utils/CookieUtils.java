package com.yueqiu.mis.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yueqiu.mis.model.AclUser;

import orion.core.utils.AES;

/**
 * 
 * cookie相关
 * 
 * @author wangxu
 * 
 */
public class CookieUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieUtils.class);

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, String domain,
            String path, boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
    }

    public static void addSessionCookie(HttpServletResponse response, String name, String value, String domain,
            String path) {
        addCookie(response, name, value, -1, domain, path, true);
    }

    public static void removeCookie(HttpServletResponse response, String name, String domain, String path) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setDomain(domain);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static final String LOGIN_COOKIE_ENCRYPT_KEY = "ruOu$x*JS*^hxV6jFA25";

    private static final String LOGIN_COOKIE_NAME = "mmu";
    private static final String DOMAIN = "localhost";
    private static final String PATH = "/";
    private static final int EXPIRE_TIME = 24 * 60 * 60 * 30;

    public static void setLoginCookie(HttpServletResponse response, AclUser user) {
        try {
            String encValue = AES.encrypt(user.toJson(), LOGIN_COOKIE_ENCRYPT_KEY);
            addCookie(response, LOGIN_COOKIE_NAME, encValue, EXPIRE_TIME, DOMAIN, PATH, true);
        } catch (Exception e) {
            LOGGER.error("set login cookie failed", e);
        }
    }

    public static void removeLoginCookie(HttpServletResponse response) {
        removeCookie(response, LOGIN_COOKIE_NAME, DOMAIN, PATH);
    }

    public static AclUser getLoginUser(HttpServletRequest request) {
        try {
            String v = getCookieValue(request, LOGIN_COOKIE_NAME);
            if (v == null) {
                return null;
            }
            return AclUser.parse(AES.decrypt(v, LOGIN_COOKIE_ENCRYPT_KEY));
        } catch (Exception e) {
            return null;
        }
    }

}
