/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yueqiu.entity.Message;
import com.yueqiu.entity.User;

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

    public List<Message> listByUser(User user, int offset, int limit) {
        Query<Message> query = createQuery();
        query.field("user").equal(user);
        query.limit(limit).offset(offset);
        query.order("-ctime");
        return query.asList();
    }
}
