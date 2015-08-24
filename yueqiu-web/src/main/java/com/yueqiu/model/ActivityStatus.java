/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.model;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月16日
 */
public enum ActivityStatus {

    UNSTART(1, "未开始"),
    INPROGRESS(2, "报名中"),
    FILLED(3, "已报满"),
    FINISH(4, "已结束");
    
    public int status;
    public String text;
    
    private ActivityStatus(int status, String text) {
        this.status = status;
        this.text = text;
    }
    
    public static ActivityStatus valueOfStatus(int status) {
        for (ActivityStatus as : values()) {
            if (as.status == status) {
                return as;
            }
        }
        return UNSTART;
    }
}
