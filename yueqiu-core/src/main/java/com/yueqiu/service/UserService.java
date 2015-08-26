/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.yueqiu.entity.Coupon;
import com.yueqiu.entity.Message;
import com.yueqiu.entity.User;
import com.yueqiu.utils.CryptUtils;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@Service
public class UserService extends BaseService {

    public User get(String id) {
        return userDao.get(id);
    }

    public boolean create(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setSalt(CryptUtils.salt());
        user.setPassword(DigestUtils.sha1Hex(user.getSalt() + user.getPassword()));
        return userDao.update(user);
    }

    public boolean update(User user) {
        user.setUpdateTime(new Date());
        return userDao.update(user);
    }

    public User getByMobile(String mobile) {
        return userDao.getByField("mobile", mobile);
    }

    public Coupon getCoupon(String id, User user) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return couponDao.get(id, user);
    }
    
    public List<Coupon> listCoupons(User user) {
        return couponDao.listCoupons(user);
    }

    public List<Message> listMessages(User user, int offset, int limit) {
        return messageDao.listByUser(user, offset, limit);
    }
}
