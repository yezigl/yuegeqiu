/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.service;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueqiu.core.entity.Activity;
import com.yueqiu.core.entity.Order;
import com.yueqiu.core.entity.User;
import com.yueqiu.core.model.ActivityStatus;
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

    @Autowired
    OrderService orderService;
    
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
        List<Order> orders = orderService.listByActivity(activity, OrderStatus.PAYED);
        List<User> users = new ArrayList<User>();
        for (Order order : orders) {
            users.add(order.getUser());
        }
        return users;
    }

    /**
     * @param all
     * @param offset
     * @param limit
     */
    public List<Activity> listAll(ActivityStatus status, int offset, int limit) {
        Query<Activity> query = activityDao.createQuery();
        if (status != ActivityStatus.ALL) {
            query.field("status").equal(status.status);
        }
        query.order("-date");
        query.offset(offset).limit(limit);
        return query.asList();
    }

    /**
     * @param activity
     */
    public void upsert(Activity activity) {
        if (activity.getId() != null) {
            update(activity);
        } else {
            create(activity);
        }
    }
}
