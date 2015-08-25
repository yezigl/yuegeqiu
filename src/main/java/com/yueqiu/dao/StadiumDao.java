/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.dao;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yueqiu.entity.Stadium;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@Repository
public class StadiumDao extends BaseDao<Stadium> {

    /**
     * @param datastore
     */
    @Autowired
    public StadiumDao(Datastore datastore) {
        super(datastore);
    }
}
