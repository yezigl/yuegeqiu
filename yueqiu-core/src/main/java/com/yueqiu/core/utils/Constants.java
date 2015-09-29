/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.utils;

import org.bson.types.ObjectId;

import com.yueqiu.core.entity.User;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
public class Constants {

    public static final String DEFAULT_AVATAR = "https://wt-avatars.oss.aliyuncs.com/40/b09e1850-8936-4094-9cb1-c5b4534bcbc7.jpg";

    public static final int ORDER_EXPIRE_TIME = 30 * 60 * 1000;
    
    public static final String ACTIVITY_DATE_FORMAT = "MM月dd日 EEE HH:mm";
    
    public static final String ORDER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    public static final String MIS_ACT_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    
    public static final User USER_OFFICIAL = new User();
    
    static {
        USER_OFFICIAL.setId(new ObjectId());
        USER_OFFICIAL.setMobile("114");
        USER_OFFICIAL.setNickname("系统");
    };
}
