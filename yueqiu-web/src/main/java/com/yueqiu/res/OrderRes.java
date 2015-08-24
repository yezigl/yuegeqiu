/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.res;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yueqiu.entity.Order;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
public class OrderRes extends Res {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private float amount;
    private float discount;
    private int status;
    private String createTime;
    @JsonInclude(Include.NON_NULL)
    private Date paytime;
    @JsonInclude(Include.NON_NULL)
    private Integer paytype;
    @JsonInclude(Include.NON_NULL)
    private String paysn;
    @JsonInclude(Include.NON_NULL)
    private ActivityRes activity;
    @JsonInclude(Include.NON_NULL)
    private CouponRes coupon;

    public OrderRes() {

    }

    public OrderRes(Order order) {
        this.id = order.getId().toString();
        this.amount = order.getAmount();
        this.status = order.getStatus();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ActivityRes getActivity() {
        return activity;
    }

    public void setActivity(ActivityRes activity) {
        this.activity = activity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public CouponRes getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponRes coupon) {
        this.coupon = coupon;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public String getPaysn() {
        return paysn;
    }

    public void setPaysn(String paysn) {
        this.paysn = paysn;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
