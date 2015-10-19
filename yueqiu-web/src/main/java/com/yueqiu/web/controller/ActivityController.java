/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yueqiu.core.entity.Activity;
import com.yueqiu.core.entity.Order;
import com.yueqiu.core.entity.User;
import com.yueqiu.core.model.ActivityStatus;
import com.yueqiu.core.model.ActivityType;
import com.yueqiu.core.model.DateType;
import com.yueqiu.core.model.OrderBy;
import com.yueqiu.core.model.OrderStatus;
import com.yueqiu.core.utils.Constants;
import com.yueqiu.core.utils.UserContext;
import com.yueqiu.web.res.ActivityRes;
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
public class ActivityController extends AbstractController {

    @RequestMapping(value = "/activities", method = RequestMethod.GET)
    public Representation list(@RequestParam(defaultValue = "0") int dateType,
            @RequestParam(defaultValue = "0") int activityType, @RequestParam(defaultValue = "desc") String orderBy,
            @RequestParam(required = false) String cityId, @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        Representation rep = new Representation();

        DateType dt = DateType.valueOfType(dateType);
        ActivityType gt = ActivityType.valueOfType(activityType);
        OrderBy ob = OrderBy.valueOfOrder(orderBy);

        List<Activity> list = activityService.list(dt, gt, ob, offset, limit);
        List<ActivityRes> resList = new ArrayList<ActivityRes>();
        for (Activity activity : list) {
            List<User> attends = activityService.getAttend(activity);
            resList.add(new ActivityRes(activity, Constants.USER_OFFICIAL, attends));
        }
        rep.setData(resList);

        return rep;
    }

    @RequestMapping(value = { "/activities/{id}", "/activity/{id}" }, method = RequestMethod.GET)
    public Representation get(@PathVariable String id) {
        Representation rep = new Representation();

        Activity activity = activityService.get(id);
        if (activity == null) {
            rep.setError(Status.NOT_EXIST);
            return rep;
        }

        List<User> attends = activityService.getAttend(activity);
        ActivityRes res = new ActivityRes(activity, Constants.USER_OFFICIAL, attends);
        // 下单信息
        Map<String, String> orderInfo = new HashMap<>();
        orderInfo.put("canOrder", String.valueOf(activity.getStatus() == ActivityStatus.INPROGRESS));
        if (UserContext.isAuth()) {
            List<Order> orders = orderService.listByUserAndActivity(UserContext.getUser(), activity, OrderStatus.CREATE,
                    OrderStatus.PAYED);
            orderInfo.put("hasOrder", String.valueOf(CollectionUtils.isNotEmpty(orders)));
            orderInfo.put("isPayed", "false");
            orderInfo.put("orderId", "");
            orderInfo.put("reason", "活动已结束");
            if (CollectionUtils.isNotEmpty(orders)) {
                Order order = orders.get(0);
                orderInfo.put("isPayed", String.valueOf(order.isPayed()));
                orderInfo.put("orderId", order.stringifyId());
            }
        }
        res.setOrderInfo(orderInfo);
        rep.setData(res);

        return rep;
    }

}
