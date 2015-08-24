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
public enum OrderStatus {

    ALL("all", 0),
    CREATE("create", 1),
    PAYED("payed", 2),
    REFUND("refund", 4),
    CLOSE("close", 8);
    
    public String identifier;
    public int code;
    
    private OrderStatus(String identifier, int code) {
        this.identifier = identifier;
        this.code = code;
    }
    
    public static OrderStatus valueOfStatus(int str) {
        for (OrderStatus os : values()) {
            if (os.code == str) {
                return os;
            }
        }
        return ALL;
    }
}
