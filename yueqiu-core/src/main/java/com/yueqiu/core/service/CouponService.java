/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Service;

import com.yueqiu.core.entity.Coupon;
import com.yueqiu.core.entity.User;
import com.yueqiu.core.entity.UserCoupon;
import com.yueqiu.core.model.CouponStatus;
import com.yueqiu.core.model.CouponType;

/**
 * description here
 *
 * @author yezi
 * @since 2015年10月19日
 */
@Service
public class CouponService extends BaseService {

    public String create(Coupon coupon) {
        return couponDao.create(coupon);
    }
    
    public boolean update(Coupon coupon) {
        return couponDao.update(coupon);
    }

    public Coupon get(String id) {
        return couponDao.get(id);
    }

    /**
     * @param offset
     * @param limit
     * @return
     */
    public List<Coupon> listAll(int offset, int limit) {
        Query<Coupon> query = couponDao.createQuery();
        query.offset(offset).limit(limit);
        return query.asList();
    }

    /**
     * @param coupon
     */
    public void upsert(Coupon coupon) {
        if (coupon.getId() != null) {
            update(coupon);
        } else {
            create(coupon);
        }
    }

    /**
     * @param first
     * @param cUsing
     * @return
     */
    public List<Coupon> listByTypeAndStatus(CouponType type, CouponStatus status) {
        Query<Coupon> query = couponDao.createQuery();
        query.field("type").equal(type).field("status").equal(status);
        query.order("-utime");
        return query.asList();
    }

    /**
     * @param user
     * @param coupon
     */
    public String addUserCoupon(User user, Coupon coupon) {
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setUser(user);
        userCoupon.setCoupon(coupon);
        userCoupon.setStatus(CouponStatus.UC_UNUSE);
        if (coupon.getPeriod() > 0) {
            userCoupon.setEndDate(DateUtils.addDays(new Date(), coupon.getPeriod()));
        } else {
            userCoupon.setEndDate(coupon.getEndDate());
        }
        return userCouponDao.create(userCoupon);
    }
    
    public UserCoupon getUserCoupon(User user, String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        Query<UserCoupon> query = userCouponDao.createQuery();
        query.filter("user", user);
        query.filter("id", new ObjectId(id));
        query.field("endtime").greaterThan(new Date());
        return query.get();
    }

    /**
     * @param user
     * @param status
     * @return
     */
    public List<UserCoupon> listByUser(User user, CouponStatus status) {
        Query<UserCoupon> query = userCouponDao.createQuery();
        query.field("user").equal(user).field("status").equal(status);
        query.order("utime");
        return query.asList();
    }
}
