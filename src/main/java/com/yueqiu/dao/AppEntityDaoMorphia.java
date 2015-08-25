package com.yueqiu.dao;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.WriteResult;
import com.yueqiu.entity.BaseEntity;

/**
 * 
 * Author: Neil Date: Apr 26, 2012
 */
public class AppEntityDaoMorphia<E extends BaseEntity, ID> extends BasicDAO<E, ID> {

    @Autowired
    public AppEntityDaoMorphia(Datastore datastore) {
        super(datastore);
    }

    public List<E> getAllEntities() {
        return this.createQuery().asList();
    }

    public int updateEntity(E entity) {
        entity.setUpdateTime(new Date());
        Key<E> key = super.save(entity);

        if (null != key && null != key.getId()) {
            return 1;
        } else {
            return 0;
        }
    }

    @SuppressWarnings("unchecked")
    public ID saveEntity(E entity) {
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        Key<E> key = super.save(entity);

        return (ID) key.getId();
    }

    public E getEntityById(ID id) {
        return super.findOne("id", id);
    }

    public int deleteEntityById(ID id) {
        WriteResult result = super.deleteById(id);
        return result.getN();
    }

    public void saveOrUpdateEntity(E entity) {
        if (entity.getId() != null) {
            updateEntity(entity);
        } else {
            saveEntity(entity);
        }
    }

    public List<E> paginationQueryEntities(Query<E> query, int offset, int limit) {
        query.offset(offset).limit(limit);
        return query.asList();
    }

    public boolean uniqueField(E appEntity, String uniquefield, String fieldValue) {
        Query<E> query = this.createQuery();
        query.field(uniquefield).equal(fieldValue);
        if (appEntity.getId() != null) {
            query.field("id").notEqual(appEntity.getId());
        }
        return query.get() == null;
    }

    public E get(Query<E> query) {
        return query.get();
    }

    public List<E> getList(Query<E> query) {
        return query.asList();
    }

    public long countEntities(Query<E> query) {
        return query.countAll();
    }

}
