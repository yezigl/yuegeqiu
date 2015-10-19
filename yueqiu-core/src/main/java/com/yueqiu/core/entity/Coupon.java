/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.mongodb.morphia.annotations.Entity;

import com.yueqiu.core.model.CouponStatus;
import com.yueqiu.core.model.CouponType;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月13日
 */
@Entity("coupon")
public class Coupon extends BaseEntity {

    private String title;
    private String description;
    private float price;
    private CouponStatus status;
    private Date endDate;
    private int period;
    private CouponType type;

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

    public CouponStatus getStatus() {
        return status;
    }

    public void setStatus(CouponStatus status) {
        this.status = status;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public CouponType getType() {
        return type;
    }

    public void setType(CouponType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.append("title", title);
        builder.append("description", description);
        builder.append("price", price);
        builder.append("status", status);
        builder.append("endDate", endDate);
        builder.append("period", period);
        builder.append("type", type);
        return builder.toString();
    }

}
