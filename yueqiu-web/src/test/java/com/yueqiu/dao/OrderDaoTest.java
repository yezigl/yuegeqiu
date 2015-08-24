/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yueqiu.TestConfig;
import com.yueqiu.entity.Order;
import com.yueqiu.model.OrderStatus;

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
        orderDao.update(order);
    }

}
