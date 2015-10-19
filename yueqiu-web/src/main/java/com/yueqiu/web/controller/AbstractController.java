/**
 * Copyright 2014 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yueqiu.core.entity.Coupon;
import com.yueqiu.core.entity.User;
import com.yueqiu.core.model.CouponStatus;
import com.yueqiu.core.model.CouponType;
import com.yueqiu.core.service.ActivityService;
import com.yueqiu.core.service.CacheService;
import com.yueqiu.core.service.CheckTaskService;
import com.yueqiu.core.service.CouponService;
import com.yueqiu.core.service.OrderService;
import com.yueqiu.core.service.PayLogService;
import com.yueqiu.core.service.UserService;

/**
 * description here
 *
 * @author yezi
 * @since 2014年11月26日
 */
public class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    protected int LIMIT = 10;
    
    @Resource
    protected CacheService cacheService;
    @Resource
    protected ActivityService activityService;
    @Resource
    protected OrderService orderService;
    @Resource
    protected UserService userService;
    @Resource
    protected PayLogService payLogService;
    @Resource
    protected CheckTaskService checkTaskService;
    @Resource
    protected CouponService couponService;
    
    protected void afterRegister(User user) {
        try {
            // 发送首次下单优惠码
            List<Coupon> coupons = couponService.listByTypeAndStatus(CouponType.FIRST, CouponStatus.C_USING);
            if (CollectionUtils.isNotEmpty(coupons)) {
                Coupon coupon = coupons.get(0);
                String id = couponService.addUserCoupon(user, coupon);
                logger.info("add user {} first coupon {}", user.stringifyId(), id);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
