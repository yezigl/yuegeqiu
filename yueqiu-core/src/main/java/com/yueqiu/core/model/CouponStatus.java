/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.model;

/**
 * description here
 *
 * @author yezi
 * @since 2015年10月19日
 */
public enum CouponStatus {

    C_UNUSE("不使用"), C_USING("使用中"), UC_UNUSE("未使用"), UC_USED("已使用"), UC_EXPIRE("已过期");

    public String name;

    /**
     * 
     */
    private CouponStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CouponStatus[] coupons() {
        return new CouponStatus[] { C_UNUSE, C_USING };
    }

    public static CouponStatus[] userCoupons() {
        return new CouponStatus[] { UC_UNUSE, UC_USED, UC_EXPIRE };
    }
}
