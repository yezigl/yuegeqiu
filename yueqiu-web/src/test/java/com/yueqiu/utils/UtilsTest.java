/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.utils;

import static org.junit.Assert.*;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

/**
 * description here
 *
 * @author yezi
 * @since 2015年8月22日
 */
public class UtilsTest {

    @Test
    public void test() {
        fail("Not yet implemented");
    }

    @Test
    public void testIp2long() {
        String ip = "112.112.112.112";
        long l = Utils.ip2long(ip);
        System.out.println((NumberUtils.toLong("112") << 24));
        System.out.println((NumberUtils.toLong("112") << 16));
        System.out.println((NumberUtils.toLong("112") << 8));
        System.out.println(l);
    }
    
    @Test
    public void testLong2ip() {
        long l = 1886417008L;
        String ip = Utils.long2ip(l);
        System.out.println(ip);
    }
}
