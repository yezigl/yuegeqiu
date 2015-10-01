/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yueqiu.core.dao.OrderDao;
import com.yueqiu.core.entity.Order;
import com.yueqiu.core.model.OrderStatus;
import com.yueqiu.core.model.PayType;
import com.yueqiu.web.TestConfig;

/**
 * description here
 *
 * @author yezi
 * @since 2015年8月22日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class OrderDaoTest {
    
    @Autowired
    OrderDao orderDao;

    @Test
    public void test() {
        Order order = orderDao.get("5585679219a2ab266dd442ed");
        order.setStatus(OrderStatus.PAYED.code);
        order.setPayTime(new Date());
        order.setPayType(PayType.ALIPAY);
        orderDao.update(order);
    }

}
