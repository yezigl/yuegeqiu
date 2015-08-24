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
public enum DateType {

    ALL(0),
    TODAY(1),
    TOMORROW(2),
    TWODAYLATER(3);
    
    int type;
    
    DateType(int type) {
        this.type = type;
    }
    
    public static DateType valueOfType(int type) {
        for (DateType dateType : values()) {
            if (dateType.type == type) {
                return dateType;
            }
        }
        return ALL;
    }
}
