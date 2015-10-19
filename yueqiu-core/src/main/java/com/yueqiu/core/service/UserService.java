/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Service;

import com.orion.core.utils.CryptUtils;
import com.yueqiu.core.entity.Message;
import com.yueqiu.core.entity.User;

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
    
    public List<Message> listMessages(User user, int offset, int limit) {
        Query<Message> query = messageDao.createQuery();
        query.field("user").equal(user);
        query.limit(limit).offset(offset);
        query.order("-ctime");
        return query.asList();
    }
}
