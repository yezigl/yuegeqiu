/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yueqiu.entity.Activity;
import com.yueqiu.entity.Order;
import com.yueqiu.entity.User;
import com.yueqiu.model.ActivityStatus;
import com.yueqiu.model.ActivityType;
import com.yueqiu.model.DateType;
import com.yueqiu.model.OrderBy;
import com.yueqiu.model.OrderStatus;
import com.yueqiu.res.ActivityRes;
import com.yueqiu.res.OrderRes;
import com.yueqiu.res.Representation;
import com.yueqiu.res.StadiumRes;
import com.yueqiu.res.Status;
import com.yueqiu.res.UserRes;
import com.yueqiu.utils.UserContext;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@RestController
@RequestMapping("/1")
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
            resList.add(fromActivity(activity));
        }
        rep.setData(resList);

        return rep;
    }

    @RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
    public Representation get(@PathVariable String id) {
        Representation rep = new Representation();

        Activity activity = activityService.get(id);
        if (activity == null) {
            rep.setError(Status.NOT_EXIST);
            return rep;
        }

        ActivityRes res = fromActivity(activity);
        if (UserContext.isAuth()) {
            List<Order> orders = orderService.getByUserAndActivity(UserContext.getUser(), activity, OrderStatus.ALL);
            if (CollectionUtils.isNotEmpty(orders)) {
                Order order = orders.get(0);
                res.setOrder(new OrderRes(order));
            }
        }
        rep.setData(res);

        return rep;
    }

    private ActivityRes fromActivity(Activity activity) {
        ActivityRes res = new ActivityRes();
        List<User> users = activityService.getAttend(activity);
        res.setId(activity.getId().toString());
        res.setTitle(activity.getTitle());
        res.setType(activity.getType());
        res.setDate(DateFormatUtils.format(activity.getDate(), activityPattern, Locale.CHINA));
        res.setPrice(activity.getPrice());
        res.setValue(activity.getPrice());
        res.setTotal(activity.getTotal());
        res.setAttend(activity.getAttend());
        UserRes ru = new UserRes();
        ru.setNickname("官方");
        ru.setMobile("17011110000");
        res.setOrganizer(ru);
        res.setStatus(activity.getStatus());
        res.setStatusStr(ActivityStatus.valueOfStatus(activity.getStatus()).text);
        StadiumRes stadium = new StadiumRes(activity.getStadium());
        res.setStadium(stadium);
        for (User user : users) {
            res.addPlayer(user);
        }
        return res;
    }
}
