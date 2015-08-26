/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.dao;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yueqiu.entity.Coupon;
import com.yueqiu.entity.User;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@Repository
public class CouponDao extends BaseDao<Coupon> {

    @Autowired
    public CouponDao(Datastore datastore) {
        super(datastore);
    }

    public Coupon get(String id, User user) {
        Query<Coupon> query = createQuery();
        query.field("id").equal(new ObjectId(id)).field("user").equal(user).field("endtime").greaterThan(new Date());
        return query.get();
    }

    public List<Coupon> listCoupons(User user) {
        Query<Coupon> query = createQuery();
        query.field("user").equal(user).field("status").equal(1);
        return query.asList();
    }

}
