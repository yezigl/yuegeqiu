/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Service;

import com.yueqiu.core.entity.Activity;
import com.yueqiu.core.entity.Order;
import com.yueqiu.core.entity.User;
import com.yueqiu.core.entity.UserCoupon;
import com.yueqiu.core.model.CouponStatus;
import com.yueqiu.core.model.OrderStatus;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@Service
public class OrderService extends BaseService {

    public Order get(String id) {
        return orderDao.get(id);
    }

    public String create(Order order, UserCoupon coupon) {
        order.setStatus(OrderStatus.CREATE);
        String id = orderDao.create(order);
        if (coupon != null) {
            coupon.setStatus(CouponStatus.UC_USED);
            coupon.setUsetime(new Date());
            userCouponDao.update(coupon);
        }
        return id;
    }

    public boolean update(Order order) {
        return orderDao.update(order);
    }

    public List<Order> listByUser(User user, OrderStatus status, int offset, int limit) {
        Query<Order> query = orderDao.createQuery();
        query.field("user").equal(user);
        if (status != OrderStatus.ALL) {
            query.field("status").equal(status.code);
        } else {
            query.field("status").in(OrderStatus.visible());
        }
        query.order("-ctime");
        query.offset(offset).limit(limit);
        return query.asList();
    }

    public List<Order> getByUserAndActivity(User user, Activity activity, OrderStatus... statuses) {
        Query<Order> query = orderDao.createQuery();
        query.field("activity").equal(activity);
        query.field("user").equal(user);
        List<Integer> sList = new ArrayList<>();
        for (OrderStatus status : statuses) {
            sList.add(status.code);
        }
        query.field("status").in(sList);
        query.order("-ctime");
        return query.asList();
    }

    /**
     * @return
     */
    public List<Order> listByActivity(Activity activity, OrderStatus status) {
        Query<Order> query = orderDao.createQuery();
        query.field("activity").equal(activity);
        if (status != OrderStatus.ALL) {
            query.field("status").equal(status.code);
        }
        query.order("-ctime");
        return query.asList();
    }

}
