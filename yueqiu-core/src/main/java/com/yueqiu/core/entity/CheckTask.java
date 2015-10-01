/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;

import com.yueqiu.core.model.CheckType;

/**
 * description here
 *
 * @author yezi
 * @since 2015年10月1日
 */
@Entity("checktask")
public class CheckTask extends BaseEntity {

    private CheckType checkType;
    private ObjectId checkId;

    public CheckType getCheckType() {
        return checkType;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }

    public ObjectId getCheckId() {
        return checkId;
    }

    public void setCheckId(ObjectId checkId) {
        this.checkId = checkId;
    }

}
