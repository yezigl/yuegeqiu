/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.mis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView errorResponse(Exception exception) {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", exception);
        logger.error(exception.getMessage(), exception);
        return mv;
    }

}