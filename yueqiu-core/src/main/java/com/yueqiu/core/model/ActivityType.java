/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.model;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
public enum ActivityType {

    ALL(0),
    FOOTBALL(1);
    
    public int code;
    
    /**
     * 
     */
    private ActivityType(int code) {
        this.code = code;
    }
    
    public static ActivityType valueOfType(int code) {
        for (ActivityType activityType : values()) {
            if (activityType.code == code) {
                return activityType;
            }
        }
        return ALL;
    }
}
