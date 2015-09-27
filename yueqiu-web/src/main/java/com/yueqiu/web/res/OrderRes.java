/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.res;

import java.util.Date;
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
    private float discount;
    private int quantity;
    private int status;
    private String createTime;
    @JsonInclude(Include.NON_NULL)
    private Date paytime;
    @JsonInclude(Include.NON_NULL)
    private PayType payType;
    @JsonInclude(Include.NON_NULL)
    private String paysn;
    @JsonInclude(Include.NON_NULL)
    private ActivityRes activity;
    @JsonInclude(Include.NON_NULL)
    private CouponRes coupon;

    public OrderRes() {

    }

    public OrderRes(Order order) {
        this.setId(order.getId().toString());
        this.setAmount(order.getAmount());
        this.setDiscount(order.getDiscount());
        this.setQuantity(order.getQuantity());
        this.setStatus(order.getStatus());
        this.setCreateTime(DateFormatUtils.format(order.getCreateTime(), Constants.ORDER_DATE_FORMAT, Locale.CHINA));
        this.setPaytime(order.getPaytime());
        this.setPayType(order.getPayType());
        this.setActivity(new ActivityRes(order.getActivity(), Constants.USER_OFFICIAL, null));
        if (order.getCoupon() != null) {
            this.setCoupon(new CouponRes(order.getCoupon()));
        }
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
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
