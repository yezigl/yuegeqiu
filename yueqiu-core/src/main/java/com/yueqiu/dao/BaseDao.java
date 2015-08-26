/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

import com.yueqiu.entity.BaseEntity;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月22日
 */
public abstract class BaseDao<E extends BaseEntity> extends AppEntityDaoMorphia<E, ObjectId> {

    /**
     * @param datastore
     */
    public BaseDao(Datastore datastore) {
        super(datastore);
    }

    public E get(String id) {
        return getEntityById(new ObjectId(id));
    }

    public String create(E e) {
        ObjectId id = saveEntity(e);
        return id == null ? null : id.toString();
    }

    public boolean update(E e) {
        return updateEntity(e) == 1;
    }

}
