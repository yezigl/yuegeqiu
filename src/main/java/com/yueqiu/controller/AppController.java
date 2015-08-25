/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yueqiu.entity.User;
import com.yueqiu.model.CacheKey;
import com.yueqiu.res.CaptchaRes;
import com.yueqiu.res.ErrorRes;
import com.yueqiu.res.LoginRes;
import com.yueqiu.res.Representation;
import com.yueqiu.res.Status;
import com.yueqiu.res.UploadRes;
import com.yueqiu.utils.Token;
import com.yueqiu.utils.Utils;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@RestController
public class AppController extends AbstractController implements ErrorController {
    
    @Value("${upload.path}")
    String uploadPath;

    @RequestMapping(value = "/1/upload", method = RequestMethod.POST)
    public Representation upload(@RequestParam MultipartFile file, @RequestParam String bucket) {

        Representation rep = new Representation();

        String cdnServer = "http://static.yueqiua.com/" + bucket + "/";
        String tempPath = uploadPath + bucket;
        File path = new File(tempPath);
        if (!path.exists()) {
            path.mkdirs();
        }

        String url = null;
        String name = StringUtils.substringBeforeLast(file.getOriginalFilename(), ".");
        String suffix = StringUtils.substringAfterLast(file.getOriginalFilename(), ".").toLowerCase();
        String filename = DigestUtils.md5Hex(name + System.currentTimeMillis()) + "_original." + suffix;
        File dest = new File(path, filename);
        try {
            file.transferTo(dest);
            url = cdnServer + filename;
        } catch (IllegalStateException | IOException e) {
            rep.setError(Status.ERROR_400, "上传文件失败");
            return rep;
        }

        UploadRes res = new UploadRes();
        res.setUrl(url);
        rep.setData(res);

        return rep;
    }
    
    @RequestMapping(value = "/1/upload/{bucket}", method = RequestMethod.POST)
    public Representation uploadStream(HttpServletRequest request, @PathVariable String bucket) {
        Representation rep = new Representation();

        String cdnServer = "http://static.yueqiua.com/" + bucket + "/";
        String tempPath = uploadPath + bucket;
        File path = new File(tempPath);
        if (!path.exists()) {
            path.mkdirs();
        }

        String url = null;
        String filename = DigestUtils.md5Hex(System.currentTimeMillis() + "png") + ".png";
        File dest = new File(path, filename);
        try {
            FileOutputStream out = new FileOutputStream(dest);
            out.write(IOUtils.toByteArray(request.getInputStream()));
            out.close();
            url = cdnServer + filename;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            rep.setError(Status.ERROR_400, "上传文件失败");
            return rep;
        }
        
        UploadRes res = new UploadRes();
        res.setUrl(url);
        rep.setData(res);
        
        return rep;
    }

    @RequestMapping(value = "/1/register", method = RequestMethod.POST)
    public Representation register(@ModelAttribute("user") User user, @RequestParam String captcha,
            @RequestHeader(value = "X-Forwarded-For", required = false) String forwardIp,
            @RequestHeader(value = "X-Real-IP", required = false) String realIp) {
        Representation rep = new Representation();

        if (StringUtils.isBlank(user.getMobile()) || user.getMobile().length() != 11) {
            rep.setError(Status.PARAM_ERROR, "mobile");
            return rep;
        }

        String captchaOrigin = cacheService.get(CacheKey.getMobileCaptchaKey(user.getMobile()));
        if (!StringUtils.equals(captchaOrigin, captcha)) {
            rep.setError(Status.CAPTCHA_ERROR);
            return rep;
        }

        if (StringUtils.isBlank(user.getPassword())) {
            rep.setError(Status.PARAM_ERROR, "password");
            return rep;
        }

        User tempUser = userService.getByMobile(user.getMobile());
        if (tempUser != null) {
            rep.setError(Status.USER_REGISTERED, user.getMobile());
            return rep;
        }

        LoginRes res = new LoginRes();
        user.setIp(Utils.getClientIP(forwardIp, realIp));
        userService.create(user);
        res.setId(user.getId().toString());
        res.setMobile(user.getMobile());
        res.setNickname(user.getNickname());
        Token token = new Token(user.getId().toString());
        res.setToken(token.encrypt());

        rep.setData(res);

        return rep;
    }

    @RequestMapping(value = "/1/login", method = RequestMethod.POST)
    public Representation login(@RequestParam String mobile, @RequestParam String password) {
        Representation rep = new Representation();

        User tempUser = userService.getByMobile(mobile);
        if (tempUser == null) {
            rep.setError(Status.USER_NOT_EXIST);
            return rep;
        }

        String tempPassword = DigestUtils.sha1Hex(tempUser.getSalt() + password);
        if (!tempPassword.equals(tempUser.getPassword())) {
            rep.setError(Status.USERNAME_OR_PASSWORD_ERROR);
            return rep;
        }

        LoginRes res = new LoginRes();
        Token token = new Token(tempUser.getId().toString());
        res.setToken(token.encrypt());
        res.setId(tempUser.getId().toString());
        res.setMobile(tempUser.getMobile());
        res.setNickname(tempUser.getNickname());
        res.setAvatar(tempUser.getAvatar());

        rep.setData(res);

        return rep;
    }

    @RequestMapping(value = "/1/captcha/mobile", method = RequestMethod.POST)
    public Representation captchaMobile(@RequestParam String mobile) {

        Representation rep = new Representation();

        Random random = new Random();
        int captcha = random.nextInt(90000) + 100000;

        cacheService.set(CacheKey.getMobileCaptchaKey(mobile), captcha, 600);

        CaptchaRes res = new CaptchaRes();
        res.setCaptcha(captcha);
        rep.setData(res);

        return rep;
    }

    @RequestMapping(value = "/1/forgetpwd", method = RequestMethod.POST)
    public Representation forgetpwd(@RequestParam String mobile, @RequestParam String captcha,
            @RequestParam String password) {
        Representation rep = new Representation();

        if (StringUtils.isBlank(mobile) || mobile.length() != 11) {
            rep.setError(Status.PARAM_ERROR, "mobile");
            return rep;
        }

        String captchaOrigin = cacheService.get(CacheKey.getMobileCaptchaKey(mobile));
        if (!StringUtils.equals(captchaOrigin, captcha)) {
            rep.setError(Status.CAPTCHA_ERROR);
            return rep;
        }

        User tempUser = userService.getByMobile(mobile);
        if (tempUser == null) {
            rep.setError(Status.USER_NOT_EXIST, mobile);
            return rep;
        }

        tempUser.setPassword(DigestUtils.sha1Hex(tempUser.getSalt() + password));
        userService.update(tempUser);

        Map<String, String> res = new HashMap<>();
        rep.setData(res);

        return rep;
    }

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
