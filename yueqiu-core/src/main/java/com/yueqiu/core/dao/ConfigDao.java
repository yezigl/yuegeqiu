/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.dao;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yueqiu.core.entity.Config;

/**
 * description here
 *
 * @author yezi
 * @since 2015年8月22日
 */
@Repository
public class ConfigDao extends BaseDao<Config> {

    /**
     * @param datastore
     */
    @Autowired
    public ConfigDao(Datastore datastore) {
        super(datastore);
    }
}
