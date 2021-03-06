/**
 * Copyright 2014 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yueqiu.core.entity.Message;
import com.yueqiu.core.entity.Order;
import com.yueqiu.core.entity.User;
import com.yueqiu.core.entity.UserCoupon;
import com.yueqiu.core.model.CouponStatus;
import com.yueqiu.core.model.OrderStatus;
import com.yueqiu.core.utils.Constants;
import com.yueqiu.core.utils.UserContext;
import com.yueqiu.web.annotation.Auth;
import com.yueqiu.web.res.CouponRes;
import com.yueqiu.web.res.MessageRes;
import com.yueqiu.web.res.OrderRes;
import com.yueqiu.web.res.Representation;
import com.yueqiu.web.res.Status;
import com.yueqiu.web.res.UserRes;

/**
 * description here
 *
 * @author yezi
 * @since 2014年11月6日
 */
@RestController
@RequestMapping(value = { "/1", "/v1" })
public class UserController extends AbstractController {

    @Auth
    @RequestMapping(value = "/user/me", method = RequestMethod.POST)
    public Representation update(@ModelAttribute User user) {
        Representation rep = new Representation();

        User current = UserContext.getUser();
        if (StringUtils.isNotBlank(user.getMobile())) {
            current.setMobile(user.getMobile());
        }
        if (StringUtils.isNotBlank(user.getNickname())) {
            current.setNickname(user.getNickname());
        }
        if (StringUtils.isNotBlank(user.getAvatar())) {
            current.setAvatar(user.getAvatar());
        }

        userService.update(current);

        UserRes res = new UserRes();
        res.setId(current.getId().toString());
        res.setMobile(current.getMobile());
        res.setNickname(current.getNickname());
        res.setAvatar(StringUtils.defaultString(current.getAvatar(), Constants.DEFAULT_AVATAR));

        rep.setData(res);

        return rep;
    }

    @Auth
    @RequestMapping(value = "/user/me", method = RequestMethod.GET)
    public Representation me() {
        Representation rep = new Representation();

        User user = UserContext.getUser();
        UserRes res = new UserRes();
        res.setId(user.getId().toString());
        res.setMobile(user.getMobile());
        res.setNickname(user.getNickname());
        res.setAvatar(StringUtils.defaultString(user.getAvatar(), Constants.DEFAULT_AVATAR));

        rep.setData(res);

        return rep;
    }

    @Auth
    @RequestMapping(value = "/user/orders", method = RequestMethod.GET)
    public Representation orders(@RequestParam(defaultValue = "0") int status,
            @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit) {
        Representation rep = new Representation();

        OrderStatus os = OrderStatus.valueOfStatus(status);
        List<Order> orders = orderService.listByUser(UserContext.getUser(), os, offset, limit);
        List<OrderRes> list = new ArrayList<OrderRes>();
        for (Order order : orders) {
            OrderRes res = new OrderRes(order);
            list.add(res);
        }
        rep.setData(list);

        return rep;
    }

    @Auth
    @RequestMapping(value = "/user/resetpwd", method = RequestMethod.POST)
    public Representation update(@RequestParam String originPassword, @RequestParam String newPassword,
            @RequestParam String confirmPassword) {
        Representation rep = new Representation();

        User user = UserContext.getUser();
        String tempPassword = DigestUtils.sha1Hex(user.getSalt() + originPassword);
        if (!tempPassword.equals(user.getPassword())) {
            rep.setError(Status.ERROR_400, "原密码不正确");
            return rep;
        }
        if (!newPassword.equals(confirmPassword)) {
            rep.setError(Status.ERROR_400, "新密码和确认密码不一致");
            return rep;
        }
        user.setPassword(DigestUtils.sha1Hex(user.getSalt() + newPassword));
        userService.update(user);

        return rep;
    }

    @Auth
    @RequestMapping(value = "/user/coupons", method = RequestMethod.GET)
    public Representation coupons() {
        Representation rep = new Representation();

        List<UserCoupon> coupons = couponService.listByUser(UserContext.getUser(), CouponStatus.UC_UNUSE);
        List<CouponRes> list = new ArrayList<>();
        for (UserCoupon coupon : coupons) {
            list.add(new CouponRes(coupon));
        }

        rep.setData(list);

        return rep;
    }

    @Auth
    @RequestMapping(value = "/user/messages", method = RequestMethod.GET)
    public Representation messages(@RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        Representation rep = new Representation();

        List<Message> messages = userService.listMessages(UserContext.getUser(), offset, limit);
        List<MessageRes> list = new ArrayList<>();
        for (Message message : messages) {
            list.add(new MessageRes(message));
        }

        rep.setData(list);

        return rep;
    }

}
