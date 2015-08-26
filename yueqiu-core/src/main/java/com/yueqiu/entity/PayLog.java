/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;

/**
 * description here
 *
 * @author lidehua
 * @since 2015年6月17日
 */
@Entity("paylog")
@Indexes({ @Index(fields = @Field("order")) })
public class PayLog extends BaseEntity {

    @Reference
    private Order order;
    
}
