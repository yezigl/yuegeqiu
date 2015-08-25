/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.entity;

import org.mongodb.morphia.annotations.Entity;

/**
 * description here
 *
 * @author yezi
 * @since 2015年8月22日
 */
@Entity
public class Config extends BaseEntity {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
