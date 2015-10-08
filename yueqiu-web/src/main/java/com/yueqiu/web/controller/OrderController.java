/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.controller;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orion.core.utils.Utils;
import com.yueqiu.core.entity.Activity;
import com.yueqiu.core.entity.Coupon;
import com.yueqiu.core.entity.Order;
import com.yueqiu.core.entity.User;
import com.yueqiu.core.model.CheckType;
import com.yueqiu.core.model.OrderStatus;
import com.yueqiu.core.utils.UserContext;
import com.yueqiu.web.annotation.Auth;
import com.yueqiu.web.res.OrderRes;
import com.yueqiu.web.res.Representation;
import com.yueqiu.web.res.Status;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@RestController
@RequestMapping(value = { "/1", "/v1" })
public class OrderController extends AbstractController {

    @Auth
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public Representation list(@RequestParam String activityId, @RequestParam(defaultValue = "") String couponId,
            @RequestParam(defaultValue = "1") int quantity,
            @RequestHeader(value = "X-Forwarded-For", required = false) String forwardIp,
            @RequestHeader(value = "X-Real-IP", required = false) String realIp) {
        Representation rep = new Representation();

        User user = UserContext.getUser();
        Activity activity = activityService.get(activityId);
        if (activity == null) {
            rep.setError(Status.NOT_EXIST, "活动不存在");
            return rep;
        }

        if (quantity < 0 || quantity > (activity.getTotal() - activity.getAttend())) {
            rep.setError(Status.PARAM_ERROR, "参与人数");
            return rep;
        }

        List<Order> orders = orderService.getByUserAndActivity(user, activity, OrderStatus.CREATE, OrderStatus.PAYED);
        Order order = null;
        if (CollectionUtils.isNotEmpty(orders)) {
            for (Order o : orders) {
                if (o.isPayed()) {
                    rep.setError(Status.ERROR_400, "已购买过，不能重复购买");
                    return rep;
                } else if (o.isNew()) {
                    order = o;
                }
            }
        }
        Coupon coupon = userService.getCoupon(couponId, user);
        if (order == null) {
            order = new Order();
            order.setActivity(activity);
            order.setUser(user);
            order.setAmount(activity.getPrice() * quantity);
            order.setQuantity(quantity);
            if (coupon != null) {
                order.setDiscount(coupon.getPrice());
            }
            order.setIp(Utils.getClientIP(forwardIp, realIp));
            String id = orderService.create(order, coupon);
            if (id == null) {
                rep.setError(Status.ERROR_400, "生成订单失败");
                return rep;
            }
            checkTaskService.submit(CheckType.ORDER_PAY, order.getId());
            logger.info("create order {}", order);
        } else {
            logger.info("exist order {}", order);
        }

        OrderRes res = new OrderRes(order);
        rep.setData(res);

        return rep;
    }

    @RequestMapping(value = { "/orders/{id}", "/order/{id}" }, method = RequestMethod.GET)
    public Representation pay(@PathVariable String id) {
        Representation rep = new Representation();

        Order order = orderService.get(id);
        if (order == null) {
            rep.setError(Status.ERROR_400, "订单不存在");
            return rep;
        }

        OrderRes res = new OrderRes(order);
        rep.setData(res);

        return rep;
    }
}
