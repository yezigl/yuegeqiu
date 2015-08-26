/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.mis.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * description here
 *
 * @author yezi
 * @since 2015年3月22日
 */
public abstract class BaseController {
    
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    protected String SEP_SEMICOLON = ";";

    protected String redirect(String url) {
//        try {
//            ((HttpServletResponse) ResponseContextHolder.getResponse()).sendRedirect(url);
//        } catch (IOException e) {
//            logger.error("redirect to {} fail", url, e);
//        }
        return "redirect:" + url;
    }
    
    protected String vm(String view) {
        if (StringUtils.isNotBlank(vmtpl())) {
            return vmtpl() + "/" + view;
        }
        return view;
    }
    
    protected abstract String vmtpl();
    
    @ModelAttribute("category")
    protected abstract String category();
}
