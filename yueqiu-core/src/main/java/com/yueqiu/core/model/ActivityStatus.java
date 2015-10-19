/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.model;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月16日
 */
public enum ActivityStatus {

    ALL(0, "未知"),
    UNSTART(1, "未开始"),
    INPROGRESS(2, "报名中"),
    FILLED(3, "已报满"),
    FINISHED(4, "已结束");
    
    public int code;
    public String name;
    
    private ActivityStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public static ActivityStatus valueOfStatus(int code) {
        for (ActivityStatus as : values()) {
            if (as.code == code) {
                return as;
            }
        }
        return UNSTART;
    }
}
