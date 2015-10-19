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
public enum DateType {

    ALL(0),
    TODAY(1),
    TOMORROW(2),
    TWODAYLATER(3);
    
    int code;
    
    DateType(int code) {
        this.code = code;
    }
    
    public static DateType valueOfType(int code) {
        for (DateType dateType : values()) {
            if (dateType.code == code) {
                return dateType;
            }
        }
        return ALL;
    }
}
