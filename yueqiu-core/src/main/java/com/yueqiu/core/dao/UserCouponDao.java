/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.dao;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yueqiu.core.entity.UserCoupon;

/**
 * description here
 *
 * @author yezi
 * @since 2015年10月18日
 */
@Repository
public class UserCouponDao extends BaseDao<UserCoupon> {

    /**
     * @param datastore
     */
    @Autowired
    public UserCouponDao(Datastore datastore) {
        super(datastore);
    }

}
