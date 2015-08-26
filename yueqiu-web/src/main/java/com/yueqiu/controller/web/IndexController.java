/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.controller.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yueqiu.controller.AbstractController;

/**
 * description here
 *
 * @author yezi
 * @since 2015年7月11日
 */
@Controller
public class IndexController extends AbstractController {

    String ANDROID = "android";
    String IPHONE = "iphone";

    String UA_ANDROID = "Android";
    String UA_IPHONE = "iPhone";

    String apkUrl = "http://www.yueqiua.com/attachment/yuegeqiu.apk";

    String appstoreUrl = "";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        return "index";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String download() {

        return "download";
    }

    @RequestMapping(value = "/download/mobile/{device}", method = RequestMethod.GET)
    public String download(@PathVariable String device, @RequestParam(required = false) String from,
            @RequestHeader(value = "User-Agent", required = false) String ua) {

        if (device.equalsIgnoreCase(ANDROID)) {
            return "redirect:" + apkUrl;
        } else if (device.equalsIgnoreCase(IPHONE)) {
            return "redirect:" + appstoreUrl;
        } else {
            logger.info("User-Agent = {}", ua);
            if (StringUtils.isNotBlank(ua)) {
                if (ua.contains(UA_ANDROID)) {
                    return "redirect:" + apkUrl;
                } else if (ua.contains(UA_IPHONE)) {
                    return "redirect:" + appstoreUrl;
                }
            }
        }

        return "redirect:" + apkUrl;
    }
}
