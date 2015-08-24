/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yueqiu.entity.Activity;
import com.yueqiu.entity.Order;
import com.yueqiu.entity.User;
import com.yueqiu.model.OrderStatus;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@Repository
public class OrderDao extends BaseDao<Order> {

    /**
     * @param datastore
     */
    @Autowired
    public OrderDao(Datastore datastore) {
        super(datastore);
    }

    public List<Order> listByUser(User user, OrderStatus status, int offset, int limit) {
        Query<Order> query = createQuery();
        query.field("user").equal(user);
        if (status != OrderStatus.ALL) {
            query.field("status").equal(status.code);
        }
        query.offset(offset).limit(limit);
        return query.asList();
    }

    public List<Order> listByActivity(Activity activity, OrderStatus status) {
        Query<Order> query = createQuery();
        query.field("activity").equal(activity);
        if (status != OrderStatus.ALL) {
            query.field("status").equal(status.code);
        }
        return query.asList();
    }

    public List<Order> getByUserAndActivity(User user, Activity activity, OrderStatus status) {
        Query<Order> query = createQuery();
        query.field("activity").equal(activity);
        query.field("user").equal(user);
        if (status != OrderStatus.ALL) {
            query.field("status").equal(status.code);
        }
        return query.asList();
    }
}
