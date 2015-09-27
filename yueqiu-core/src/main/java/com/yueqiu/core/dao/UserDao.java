/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.dao;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yueqiu.core.entity.User;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@Repository
public class UserDao extends BaseDao<User> {

    /**
     * @param datastore
     */
    @Autowired
    public UserDao(Datastore datastore) {
        super(datastore);
    }
    
    public User getByField(String field, String mobile) {
        Query<User> query = createQuery();
        query.field(field).equal(mobile);
        return query.get();
    }
    
}
