/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.service;

import org.springframework.stereotype.Service;

import com.yueqiu.model.SimpleInnerCache;

/**
 * description here
 *
 * @author yezi
 * @since 2015年2月26日
 */
@Service
public class CacheService {
    
    private SimpleInnerCache<String, Object> innerCache = new SimpleInnerCache<>();

    public boolean set(String key, Object value, int expire) {
        innerCache.put(key, value, expire);
        return true;
    }
    
    public String get(String key) {
        return String.valueOf(innerCache.get(key));
    }
}
