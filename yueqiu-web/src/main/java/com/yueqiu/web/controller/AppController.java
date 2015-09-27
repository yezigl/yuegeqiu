/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yueqiu.web.res.ErrorRes;
import com.yueqiu.web.res.Representation;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@RestController
public class AppController extends AbstractController implements ErrorController {
    
    @RequestMapping(value = "/error")
    public Representation error(HttpServletRequest request) {
        Representation rep = new Representation();
        HttpStatus status = getStatus(request);
        ErrorRes error = new ErrorRes();
        error.setCode(status.value());
        error.setMessage(status.getReasonPhrase());
        rep.setError(error);
        return rep;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception ex) {
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
