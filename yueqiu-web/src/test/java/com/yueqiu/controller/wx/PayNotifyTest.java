/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.controller.wx;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月20日
 */
public class PayNotifyTest {
    
    static XStream xstream = new XStream(new XppDriver(new NoNameCoder()));

    static {
        xstream.processAnnotations(new Class<?>[] { UnifiedOrder.class, PrePay.class });
    }
    
    @Test
    public void test() {
        PayNotify payNotify = new PayNotify();
        payNotify.appid = Weixin.APP_ID;
        payNotify.mch_id = Weixin.MCH_ID;
        
        System.out.println(xstream.toXML(payNotify));
    }

}
