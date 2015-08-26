/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yueqiu.entity.Activity;
import com.yueqiu.model.ActivityType;
import com.yueqiu.model.DateType;
import com.yueqiu.model.OrderBy;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@Repository
public class ActivityDao extends BaseDao<Activity> {

    /**
     * @param datastore
     */
    @Autowired
    public ActivityDao(Datastore datastore) {
        super(datastore);
    }
    
    public List<Activity> list(DateType date, ActivityType ball, OrderBy orderby, int offset, int limit) {
        Query<Activity> query = createQuery();
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.HOUR_OF_DAY, 0);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        switch (date) {
        case TODAY:
            cale.add(Calendar.DAY_OF_MONTH, 1);
            query.filter("date <", cale.getTime());
            break;
        case TOMORROW:
            cale.add(Calendar.DAY_OF_MONTH, 1);
            Date begin = cale.getTime();
            cale.add(Calendar.DAY_OF_MONTH, 1);
            Date end = cale.getTime();
            query.filter("date >", begin);
            query.filter("date <", end);
            break;
        case TWODAYLATER:
            cale.add(Calendar.DAY_OF_MONTH, 2);
            query.filter("date >", cale.getTime());
            break;
        default:
            break;
        }
        switch (orderby) {
        case ASC:
            query.order("date");
            break;
        case DESC:
            query.order("-date");
            break;
        default:
            break;
        }
        query.offset(offset).limit(limit);
        return query.asList();
    }

}
