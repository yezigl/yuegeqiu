/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.dao;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yueqiu.core.entity.CheckTask;

/**
 * description here
 *
 * @author yezi
 * @since 2015年10月1日
 */
@Repository
public class CheckTaskDao extends BaseDao<CheckTask> {

    /**
     * @param datastore
     */
    @Autowired
    public CheckTaskDao(Datastore datastore) {
        super(datastore);
    }

}
