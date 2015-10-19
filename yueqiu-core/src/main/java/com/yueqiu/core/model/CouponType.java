/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.model;

/**
 * description here
 *
 * @author yezi
 * @since 2015年10月18日
 */
public enum CouponType {
    /**
     * 首次下单
     */
    FIRST("首次下单"),
    /**
     * 减免
     */
    REDUCE("立减"),
    /**
     * 代金券
     */
    VOUCHER("代金券");
    
    public String name;
    
    /**
     * 
     */
    private CouponType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
