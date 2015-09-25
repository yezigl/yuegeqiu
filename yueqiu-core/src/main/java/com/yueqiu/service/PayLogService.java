/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.service;

import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Service;

import com.yueqiu.entity.Order;
import com.yueqiu.entity.PayLog;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月20日
 */
@Service
public class PayLogService extends BaseService {

    public String create(PayLog payLog) {
        return payLogDao.create(payLog);
    }

    /**
     * @param order
     */
    public PayLog getByOrder(Order order) {
        Query<PayLog> query = payLogDao.createQuery();
        query.field("order").equal(order);
        query.field("status").equal(1);
        return query.get();
    }
}
