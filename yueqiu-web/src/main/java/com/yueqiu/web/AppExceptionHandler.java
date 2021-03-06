/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yueqiu.web.exception.AppException;
import com.yueqiu.web.res.Representation;
import com.yueqiu.web.res.Status;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@ControllerAdvice
public class AppExceptionHandler {
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Representation errorResponse(Exception exception) {
        Representation rep = new Representation();
        if (exception instanceof AppException) {
            rep.setError(((AppException) exception).getStatus());
        } else {
            rep.setError(Status.SERVER_ERROR, exception.getMessage());
            logger.error(exception.getMessage(), exception);
        }
        return rep;
    }

}