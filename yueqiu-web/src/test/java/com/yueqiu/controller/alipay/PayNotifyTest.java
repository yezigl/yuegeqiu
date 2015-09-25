/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.controller.alipay;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月26日
 */
public class PayNotifyTest {
    
    XStream xstream = new XStream(new XppDriver(new NoNameCoder()));

    @Test
    public void test() {
        PayNotify payNotify = new PayNotify();
        payNotify.body = "test body";
        System.out.println(xstream.toXML(payNotify));
    }

}
