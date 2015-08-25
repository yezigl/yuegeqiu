/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.yueqiu.model.AppException;
import com.yueqiu.res.Status;
import com.yueqiu.utils.UserContext;

/**
 * description here
 *
 * @author yezi
 * @since 2015年3月18日
 */
@Aspect
@Component
public class AuthAspect {

    @Around("@annotation(com.yueqiu.annotation.Auth)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        if (UserContext.isAuth()) {
            return pjp.proceed();
        } else {
            throw new AppException(Status.USER_NOT_LOGIN);
        }

    }
}
