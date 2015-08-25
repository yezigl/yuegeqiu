/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.dao;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yueqiu.TestConfig;
import com.yueqiu.entity.Stadium;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class StadiumDaoTest {

    @Autowired
    StadiumDao stadiumDao;

    /**
     * Test method for {@link com.yueqiu.dao.StadiumDao#get(java.lang.String)}.
     */
    @Test
    public void testGetString() {
        String id = "557d2a49bee8c95104d86eb8";
        Stadium stadium = stadiumDao.get(id);
        System.out.println(stadium);
    }

    /**
     * Test method for
     * {@link com.yueqiu.dao.StadiumDao#create(com.yueqiu.entity.Stadium)}.
     * 557d2a49bee8c95104d86eb8
     * 557d2b05bee8a3b662b56e07
     */
    @Test
    public void testCreate() {
        Stadium stadium = new Stadium();
        stadium.setName("奥林匹克森林公园北园");
        stadium.setAddress("奥林匹克森林公园");
        stadium.setSize(10);
        stadium.setGallery(new String[] {
                "http://www.fieldturf.com/media/W1siZiIsIjIwMTIvMTEvMDgvMTcvMTMvMDMvNzUvZmllbGR0dXJmX2JvaXNlX3N0YXRlX2hlYWRlci5qcGciXSxbInAiLCJ0aHVtYiIsIjcyMHgzMDAjIl1d",
                "http://www.fieldturf.com/media/W1siZiIsIjIwMTMvMDkvMTEvMTYvMTQvMTkvNjU4L1lvcmtfVW5pdmVyc2l0eS5qcGciXSxbInAiLCJ0aHVtYiIsIjcyMHgzMDAjIl1d" });

        String id = stadiumDao.create(stadium);
        System.out.println(id);
    }

    /**
     * Test method for
     * {@link com.yueqiu.dao.StadiumDao#update(com.yueqiu.entity.Stadium)}.
     */
    @Test
    public void testUpdateStadium() {
        fail("Not yet implemented");
    }

}
