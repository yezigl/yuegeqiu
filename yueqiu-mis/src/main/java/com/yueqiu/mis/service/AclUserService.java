/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.mis.service;

import org.springframework.stereotype.Service;

import com.yueqiu.mis.model.AclUser;

/**
 * description here
 *
 * @author yezi
 * @since 2015年3月22日
 */
@Service
public class AclUserService {

    public AclUser get(int id) {
        return new AclUser();
    }
}
