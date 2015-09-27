/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yueqiu.core.entity.Activity;
import com.yueqiu.core.entity.Order;
import com.yueqiu.core.entity.User;
import com.yueqiu.core.model.ActivityType;
import com.yueqiu.core.model.DateType;
import com.yueqiu.core.model.OrderBy;
import com.yueqiu.core.model.OrderStatus;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@Service
public class ActivityService extends BaseService {

    public Activity get(String id) {
        return activityDao.get(id);
    }
    
    public String create(Activity activity) {
        return activityDao.create(activity);
    }
    
    public boolean update(Activity activity) {
        return activityDao.update(activity);
    }
    
    public List<Activity> list(DateType date, ActivityType ball, OrderBy orderby, int offset, int limit) {
        return activityDao.list(date, ball, orderby, offset, limit);
    }

    public List<User> getAttend(Activity activity) {
        List<Order> orders = orderDao.listByActivity(activity, OrderStatus.PAYED);
        List<User> users = new ArrayList<User>();
        for (Order order : orders) {
            users.add(order.getUser());
        }
        return users;
    }
}