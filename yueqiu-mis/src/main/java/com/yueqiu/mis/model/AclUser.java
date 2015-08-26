package com.yueqiu.mis.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * 
 * cookie保存的认证用户信息
 * 
 * @author wangxu
 *
 */
public class AclUser {

    private static final long LIFE = 30 * 60 * 1000; // 30分钟过期
    private static final long REFRESH_THRESHOLD = 15 * 60 * 1000; // 15分钟通知刷新

    private int id;
    private String name;
    private List<Integer> grant;
    private long time;
    
    /**
     * 
     */
    public AclUser() {
        time = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    /**
     * 返回是否需要刷新登陆信息
     * 
     * @return
     */
    public boolean refresh() {
        long cur = System.currentTimeMillis();
        if (cur - time > REFRESH_THRESHOLD) {
            time = cur;
            return true;
        } else {
            return false;
        }
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - time > LIFE;
    }

    public List<Integer> getGrant() {
        return grant;
    }

    public void setGrant(List<Integer> grant) {
        if (grant == null)
            this.grant = grant;
        // 排序，>=0的正序排，<0的排在后面，正序排
        Integer[] ids = grant.toArray(new Integer[grant.size()]);
        Arrays.sort(ids, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > 0 && o2 > 0) {
                    return o1 - o2;
                } else if (o1 <= 0 && o2 <= 0) {
                    return o1 - o2;
                } else if (o1 > 0 && o2 <= 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        this.grant = Arrays.asList(ids);
    }

    public boolean isGranted(int id) {
        if (grant == null) {
            return false;
        }
        for (int g : grant) {
            if (g == id) {
                return true;
            }
        }
        return false;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public static AclUser parse(String json) {
        try {
            AclUser user = JSON.parseObject(json, new TypeReference<AclUser>() {
            });
//            if (user.isExpired()) {
//                return null;
//            } else {
//                return user;
//            }
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
