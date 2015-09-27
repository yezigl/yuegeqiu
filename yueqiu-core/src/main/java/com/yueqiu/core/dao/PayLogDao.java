/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yueqiu.core.entity.Order;
import com.yueqiu.core.entity.PayLog;

/**
 * description here
 *
 * @author lidehua
 * @since 2015年6月17日
 */
@Repository
public class PayLogDao extends BaseDao<PayLog> {

    /**
     * @param datastore
     */
    @Autowired
    public PayLogDao(Datastore datastore) {
        super(datastore);
    }

    public List<PayLog> listPayLogs(Order order) {
        Query<PayLog> query = createQuery();
        query.field("order").equal(order);
        return query.asList();
    }

}
