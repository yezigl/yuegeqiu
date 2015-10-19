/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.model;

import java.util.Arrays;
import java.util.List;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
public enum OrderStatus {

    ALL("all", 0), // 这个状态没有作用
    CREATE("create", 1),
    PAYED("payed", 2),
    REFUND("refund", 4),
    CLOSE("close", 8);
    
    public String name;
    public int code;
    
    private OrderStatus(String name, int code) {
        this.name = name;
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
    
    public static List<Integer> visible() {
        return Arrays.asList(CREATE.code, PAYED.code);
    }
}
