/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.mis.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yueqiu.mis.holder.AclUserContext;
import com.yueqiu.mis.model.AclUser;
import com.yueqiu.mis.utils.CookieUtils;

/**
 * description here
 *
 * @author yezi
 * @since 2015年3月22日
 */
@Controller
public class IndexController extends BaseController {

    @Override
    protected String project() {
        return null;
    }
    
    @RequestMapping("/")
    public String index() {

        return "index";
    }

    @RequestMapping("/{project}")
    public String project(@PathVariable String project, Model model) {
        model.addAttribute("project", project);
        return project + "/" + project;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String preLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@RequestParam String username, @RequestParam String password,
            @RequestParam(defaultValue = "") String ru, Model model, HttpServletResponse response) {

        if (username.equals("mm") && password.equals("123456")) {
            AclUser aclUser = new AclUser();
            aclUser.setId(1);
            AclUserContext.setUser(aclUser);
            CookieUtils.setLoginCookie(response, aclUser);
            return redirect(StringUtils.defaultIfBlank(ru, "/"));
        } else {
            model.addAttribute("error", "用户名或密码不正确");
        }

        return "login";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(@RequestParam MultipartFile[] files, @RequestParam String bucket) {

        ModelAndView mv = new ModelAndView();

        if (files == null || files.length == 0) {
            mv.addObject("code", "-1");
            return mv.getModel();
        }

        String cdnServer = "http://static.yueqiua.com/" + bucket + "/";
        String uploadPath = "/opt/data/upload/" + bucket;
        File path = new File(uploadPath);
        if (!path.exists()) {
            path.mkdirs();
        }

        List<String> urls = new ArrayList<String>();
        for (MultipartFile file : files) {
            String name = StringUtils.substringBeforeLast(file.getOriginalFilename(), ".");
            String suffix = StringUtils.substringAfterLast(file.getOriginalFilename(), ".").toLowerCase();
            String filename = DigestUtils.md5Hex(name + System.currentTimeMillis()) + "_original." + suffix;
            File dest = new File(path, filename);
            try {
                file.transferTo(dest);
                urls.add(cdnServer + filename);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }

        if (urls.isEmpty()) {
            mv.addObject("code", "-1");
        } else {
            mv.addObject("code", "200");
            mv.addObject("urls", urls);
        }

        return mv.getModel();
    }

}
