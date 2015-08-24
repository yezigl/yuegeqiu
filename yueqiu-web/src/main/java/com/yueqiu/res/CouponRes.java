/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.res;

import com.yueqiu.entity.Coupon;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
public class CouponRes extends Res {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String desc;
    private float price;

    public CouponRes() {

    }

    public CouponRes(Coupon coupon) {
        this.name = coupon.getName();
        this.desc = coupon.getDesc();
        this.price = coupon.getPrice();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
