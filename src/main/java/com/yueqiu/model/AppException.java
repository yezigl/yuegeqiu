/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.model;

import com.yueqiu.res.Status;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
public class AppException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Status status;

    /**
     * 
     */
    public AppException() {
    }

    public AppException(Status status) {
        this.setStatus(status);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
