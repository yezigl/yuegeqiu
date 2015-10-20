/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.res;

import java.util.Locale;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yueqiu.core.entity.Order;
import com.yueqiu.core.model.PayType;
import com.yueqiu.core.utils.Constants;

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
    private float payAmount;
    private float discount;
    private int quantity;
    private int status;
    private String createTime;
    @JsonInclude(Include.NON_NULL)
    private String payTime;
    @JsonInclude(Include.NON_NULL)
    private PayType payType;
    @JsonInclude(Include.NON_NULL)
    private ActivityRes activity;
    @JsonInclude(Include.NON_NULL)
    private CouponRes coupon;

    public OrderRes() {

    }

    public OrderRes(Order order) {
        this.id = order.getId().toString();
        this.amount = order.getAmount();
        this.payAmount = order.getPayAmount();
        this.discount = order.getDiscount();
        this.quantity = order.getQuantity();
        this.status = order.getStatus().code;
        this.createTime = DateFormatUtils.format(order.getCreateTime(), Constants.ORDER_DATE_FORMAT, Locale.CHINA);
        if (order.isPayed()) {
            this.payTime = DateFormatUtils.format(order.getPayTime(), Constants.ORDER_DATE_FORMAT, Locale.CHINA);
            this.payType = order.getPayType();
        }
        this.activity  = new ActivityRes(order.getActivity(), Constants.USER_OFFICIAL, null);
        if (order.getCoupon() != null) {
            this.coupon = new CouponRes(order.getCoupon());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(float payAmount) {
        this.payAmount = payAmount;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public ActivityRes getActivity() {
        return activity;
    }

    public void setActivity(ActivityRes activity) {
        this.activity = activity;
    }

    public CouponRes getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponRes coupon) {
        this.coupon = coupon;
    }

}
