/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.service;

import java.util.List;

import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Service;

import com.yueqiu.core.entity.Stadium;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月26日
 */
@Service
public class StadiumService extends BaseService {

    public List<Stadium> listAll() {
        Query<Stadium> query = stadiumDao.createQuery();
        query.order("-ctime");
        return query.asList();
    }
    
    public Stadium get(String id) {
        return stadiumDao.get(id);
    }

    /**
     * @param stadium
     */
    public String create(Stadium stadium) {
        return stadiumDao.create(stadium);
    }

    /**
     * @param stadium
     */
    public boolean update(Stadium stadium) {
        return stadiumDao.update(stadium);
    }
}
