/**
 * Copyright 2014 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.controller;

import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yueqiu.entity.Order;
import com.yueqiu.res.ActivityRes;
import com.yueqiu.res.CouponRes;
import com.yueqiu.res.OrderRes;
import com.yueqiu.res.StadiumRes;
import com.yueqiu.service.CacheService;
import com.yueqiu.service.ActivityService;
import com.yueqiu.service.OrderService;
import com.yueqiu.service.UserService;

/**
 * description here
 *
 * @author yezi
 * @since 2014年11月26日
 */
public class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    protected int LIMIT = 10;
    
    protected String activityPattern = "MM月dd日 EEE HH:mm";
    
    protected String orderPattern = "yyyy-MM-dd HH:mm:ss";
    
    @Resource
    protected CacheService cacheService;
    @Resource
    protected ActivityService activityService;
    @Resource
    protected OrderService orderService;
    @Resource
    protected UserService userService;
    
    protected OrderRes fromOrder(Order order) {
        OrderRes res = new OrderRes();
        res.setId(order.getId().toString());
        res.setAmount(order.getAmount());
        res.setDiscount(order.getDiscount());
        res.setStatus(order.getStatus());
        res.setCreateTime(DateFormatUtils.format(order.getCreateTime(), orderPattern, Locale.CHINA));
        res.setPaytime(order.getPaytime());
        res.setPaytype(order.getPaytype());
        res.setPaysn(order.getPaysn());
        ActivityRes ares = new ActivityRes();
        ares.setId(order.getActivity().getId().toString());
        ares.setTitle(order.getActivity().getTitle());
        ares.setDate(DateFormatUtils.format(order.getActivity().getDate(), activityPattern, Locale.CHINA));
        ares.setStadium(new StadiumRes(order.getActivity().getStadium()));
        res.setActivity(ares);
        res.setCoupon(new CouponRes());
        return res;
    }
}
