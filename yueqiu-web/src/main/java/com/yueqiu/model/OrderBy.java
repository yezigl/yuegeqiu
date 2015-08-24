/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.model;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
public enum OrderBy {

    ASC("asc"), DESC("desc");

    String order;

    /**
     * 
     */
    private OrderBy(String order) {
        this.order = order;
    }

    public static OrderBy valueOfOrder(String order) {
        for (OrderBy orderBy : values()) {
            if (orderBy.order.equals(order)) {
                return orderBy;
            }
        }
        return DESC;
    }
}
