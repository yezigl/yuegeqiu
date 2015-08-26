/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yueqiu.dao.ActivityDao;
import com.yueqiu.dao.CouponDao;
import com.yueqiu.dao.MessageDao;
import com.yueqiu.dao.OrderDao;
import com.yueqiu.dao.StadiumDao;
import com.yueqiu.dao.UserDao;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
public abstract class BaseService {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    protected CouponDao couponDao;
    @Autowired
    protected ActivityDao activityDao;
    @Autowired
    protected OrderDao orderDao;
    @Autowired
    protected StadiumDao stadiumDao;
    @Autowired
    protected UserDao userDao;
    @Autowired
    protected MessageDao messageDao;
    
}
