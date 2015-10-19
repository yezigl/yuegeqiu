/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;

import com.yueqiu.core.model.OrderStatus;
import com.yueqiu.core.model.PayType;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月13日
 */
@Entity("order")
@Indexes({ @Index(fields = @Field("user") ), @Index(fields = { @Field("activity"), @Field("status") }), })
public class Order extends BaseEntity {

    @Reference
    private Activity activity;
    @Reference
    private User user;
    private float amount;
    private float payAmount;
    private float discount;
    private int quantity;
    @Reference
    private UserCoupon coupon;
    private OrderStatus status;
    private Date payTime;
    private PayType payType;
    private String ip;

    public boolean isPayed() {
        return this.status == OrderStatus.PAYED;
    }

    public boolean isNew() {
        return this.status == OrderStatus.CREATE;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public UserCoupon getCoupon() {
        return coupon;
    }

    public void setCoupon(UserCoupon coupon) {
        this.coupon = coupon;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.append("activity", activity.stringifyId());
        builder.append("user", user.stringifyId());
        builder.append("amount", amount);
        builder.append("discount", discount);
        builder.append("quantity", quantity);
        builder.append("coupon", coupon);
        builder.append("status", status);
        builder.append("payTime", payTime);
        builder.append("payType", payType);
        builder.append("ip", ip);
        return builder.toString();
    }

}
