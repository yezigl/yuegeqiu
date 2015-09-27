/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.res;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月21日
 */
public class PayRes extends Res {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int status;
    private String type;
    private String payTime;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

}
