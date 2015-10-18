/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        Query<Activity> query = activityDao.createQuery();
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.HOUR_OF_DAY, 0);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        switch (date) {
        case TODAY:
            query.filter("date >", cale.getTime());
            cale.add(Calendar.DAY_OF_MONTH, 1);
            query.filter("date <", cale.getTime());
            break;
        case TOMORROW:
            cale.add(Calendar.DAY_OF_MONTH, 1);
            Date begin = cale.getTime();
            cale.add(Calendar.DAY_OF_MONTH, 1);
            Date end = cale.getTime();
            query.filter("date >", begin);
            query.filter("date <", end);
            break;
        case TWODAYLATER:
            cale.add(Calendar.DAY_OF_MONTH, 2);
            query.filter("date >", cale.getTime());
            break;
        default:
            query.filter("date >", cale.getTime());
            break;
        }
        switch (orderby) {
        case ASC:
            query.order("date");
            break;
        case DESC:
            query.order("-date");
            break;
        default:
            break;
        }
        query.offset(offset).limit(limit);
        return query.asList();
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

    /**
     * @param activity
     */
    public int incrAttend(Activity activity, int incr) {
        if (activity.getAttend() < activity.getTotal()) {
            activity.setAttend(activity.getAttend() + incr);
            activityDao.update(activity);
        }
        return activity.getAttend();
    }
}
