/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.entity;

import org.mongodb.morphia.annotations.Entity;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月28日
 */
@Entity
public class City extends BaseEntity {

    private String name;
    private String cid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

}
