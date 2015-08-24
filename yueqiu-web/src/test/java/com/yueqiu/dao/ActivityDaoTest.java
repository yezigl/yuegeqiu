/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.dao;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yueqiu.TestConfig;
import com.yueqiu.entity.Activity;
import com.yueqiu.entity.Stadium;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ActivityDaoTest {

    @Autowired
    ActivityDao activityDao;
    
    @Autowired
    StadiumDao stadiumDao;
    
    /**
     * Test method for {@link com.yueqiu.dao.ActivityDao#get(java.lang.String)}.
     */
    @Test
    public void testGetString() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.yueqiu.dao.ActivityDao#create(com.yueqiu.entity.Activity)}.
     */
    @Test
    public void testCreate() {
        Activity activity = new Activity();
        activity.setTitle("快来踢球啊Test");
        Stadium stadium = stadiumDao.get("557d2b05bee8a3b662b56e07");
        activity.setStadium(stadium);
        activity.setAttend(0);
        activity.setDate(new Date());
        activity.setPrice(40);
        activity.setTotal(22);
        
        activityDao.create(activity);
    }
    
    @Test
    public void testCreateRemote() {
        Activity activity = new Activity();
        activity.setTitle("快来踢球啊1");
        Stadium stadium = stadiumDao.get("557d3a3a87b6a706a5f8fb71");
        activity.setStadium(stadium);
        activity.setAttend(0);
        activity.setDate(new Date());
        activity.setPrice(40);
        activity.setTotal(22);
        
        activityDao.create(activity);
        
        activity = new Activity();
        activity.setTitle("快来踢球啊2");
        stadium = stadiumDao.get("557d3a6487b6a706a63dd428");
        activity.setStadium(stadium);
        activity.setAttend(0);
        activity.setDate(new Date());
        activity.setPrice(40);
        activity.setTotal(10);
        
        activityDao.create(activity);
    }

    /**
     * Test method for {@link com.yueqiu.dao.ActivityDao#update(com.yueqiu.entity.Activity)}.
     */
    @Test
    public void testUpdateActivity() {
        String id = "557d888187b6a70804d787ba";
        Activity activity = activityDao.get(id);
        Stadium stadium = stadiumDao.get("557d2b05bee8a3b662b56e07");
        activity.setStadium(stadium);
        activityDao.update(activity);
    }

    /**
     * Test method for {@link com.yueqiu.dao.ActivityDao#list(com.yueqiu.model.DateType, com.yueqiu.model.GameType, com.yueqiu.model.OrderBy, int, int)}.
     */
    @Test
    public void testList() {
        fail("Not yet implemented");
    }

}
