/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.dao;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yueqiu.core.entity.Message;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月22日
 */
@Repository
public class MessageDao extends BaseDao<Message> {

    /**
     * @param datastore
     */
    @Autowired
    public MessageDao(Datastore datastore) {
        super(datastore);
    }
}
