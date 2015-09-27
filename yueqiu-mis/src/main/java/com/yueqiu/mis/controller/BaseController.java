/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.mis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yueqiu.core.service.ActivityService;
import com.yueqiu.core.service.OrderService;
import com.yueqiu.core.service.PayLogService;
import com.yueqiu.core.service.StadiumService;
import com.yueqiu.core.service.UserService;

/**
 * description here
 *
 * @author yezi
 * @since 2015年3月22日
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected String SEP_SEMICOLON = ";";
    
    @Autowired
    protected ActivityService activityService;
    @Autowired
    protected OrderService orderService;
    @Autowired
    protected PayLogService payLogService;
    @Autowired
    protected StadiumService stadiumService;
    @Autowired
    protected UserService userService;

    protected String redirect(String url) {
        // try {
        // ((HttpServletResponse)
        // ResponseContextHolder.getResponse()).sendRedirect(url);
        // } catch (IOException e) {
        // logger.error("redirect to {} fail", url, e);
        // }
        return "redirect:" + url;
    }

    protected String vm(String view) {
        return project() + "/" + view;
    }

    @ModelAttribute("project")
    protected abstract String project();
}
