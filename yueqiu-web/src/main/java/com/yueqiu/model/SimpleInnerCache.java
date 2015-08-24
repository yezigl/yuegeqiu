package com.yueqiu.model;

import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleInnerCache<K, V> {

    private static final Logger logger = LoggerFactory.getLogger(SimpleInnerCache.class);
    private static final long DEFAULT_EXPIRE = 1000 * 60 * 10;
    private static final long UN_LIMIT_EXPIRE = -1;
    private static final int MAX_SIZE = 5000;

    private static Timer timer = new Timer("CacheTimer", true);

    @SuppressWarnings("rawtypes")
    private final ConcurrentMap<Object, CacheItem> cache = new ConcurrentHashMap<Object, CacheItem>(MAX_SIZE);
    private long expire;

    public SimpleInnerCache() {
        this(DEFAULT_EXPIRE);
    }

    public SimpleInnerCache(final long expire) {
        this.expire = expire;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (cache == null)
                    return;

                for (@SuppressWarnings("rawtypes")
                Entry<Object, CacheItem> entry : cache.entrySet()) {
                    @SuppressWarnings("rawtypes")
                    CacheItem item = entry.getValue();
                    if (isExpire(item, expire)) {
                        cache.remove(entry.getKey());
                    }
                }
            }
        }, 5 * 60 * 1000, 5 * 60 * 1000);
    }

    public void put(K key, V value) {
        put(key, value, this.expire);
    }

    public void put(K key, V value, long expire) {
        cache.put(key, new CacheItem<V>(value, System.currentTimeMillis(), expire));
        logger.debug("key {} value {} expire {}", new Object[] { key, value, expire });
    }

    public V get(K key, long expire) {
        @SuppressWarnings({ "unchecked" })
        CacheItem<V> item = cache.get(key);
        if (item != null) {
            logger.debug("key {} expire {}", key, item.expireTime);
            return item.value;
        }
        return null;
    }

    public V get(K key) {
        return get(key, this.expire);
    }

    public void delete(K key) {
        cache.remove(key);
        logger.debug("key {}", key);
    }

    private static boolean isExpire(@SuppressWarnings("rawtypes") CacheItem item, long expire) {
        if (item != null) {
            long usedExpire = Math.min(expire, item.expireTime);
            if (usedExpire != UN_LIMIT_EXPIRE && usedExpire + item.createTime <= System.currentTimeMillis()) {
                return true;
            }
        }
        return false;
    }

    static class CacheItem<V> {
        V value;
        long createTime;
        long expireTime;

        CacheItem(V value, long createTime, long expireTime) {
            this.value = value;
            this.createTime = createTime;
            this.expireTime = expireTime;
        }

        public void refresh() {
            this.createTime = System.currentTimeMillis();
        }
    }
}