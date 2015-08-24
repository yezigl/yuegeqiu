/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.model;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
public enum ActivityType {

    ALL(0),
    FOOTBALL(1);
    
    int type;
    
    /**
     * 
     */
    private ActivityType(int type) {
        this.type = type;
    }
    
    public static ActivityType valueOfType(int type) {
        for (ActivityType activityType : values()) {
            if (activityType.type == type) {
                return activityType;
            }
        }
        return ALL;
    }
}
