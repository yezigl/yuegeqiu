/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.res;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.yueqiu.core.entity.UserCoupon;
import com.yueqiu.core.utils.Constants;

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
    private String title;
    private String description;
    private float price;
    private String endDate;

    public CouponRes() {

    }

    public CouponRes(UserCoupon coupon) {
        this.id = coupon.stringifyId();
        this.title = coupon.getCoupon().getTitle();
        this.description = coupon.getCoupon().getDescription();
        this.price = coupon.getCoupon().getPrice();
        this.endDate = DateFormatUtils.format(coupon.getEndDate(), Constants.COUPON_DATE_FORMAT);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
