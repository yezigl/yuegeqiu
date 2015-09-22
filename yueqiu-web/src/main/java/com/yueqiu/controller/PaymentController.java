/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.yueqiu.entity.Order;
import com.yueqiu.entity.PayLog;
import com.yueqiu.model.PayNotify;
import com.yueqiu.model.PayType;
import com.yueqiu.res.PayRes;
import com.yueqiu.res.Representation;
import com.yueqiu.res.Status;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月22日
 */
@RestController
@RequestMapping("/v1/payment")
public class PaymentController extends AbstractController {
    
    XStream xstream = new XStream(new XppDriver(new NoNameCoder()));

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    @ResponseBody
    public Representation checkOrder(@RequestParam(value = "orderId") String orderId) {

        Representation rep = new Representation();
        PayRes res = new PayRes();
        
        Order order = orderService.get(orderId);
        if (order == null) {
            rep.setError(Status.ERROR_400, "订单不存在");
            return rep;
        }

        PayLog payLog = payLogService.getByOrder(order);
        if (payLog.getPayType() == PayType.WEIXIN) {
            PayNotify.Weixin payNotify = (PayNotify.Weixin) xstream.fromXML(payLog.getDetail());
        } else if (payLog.getPayType() == PayType.ALIPAY) {
            PayNotify.Alipay payNotify = JSON.parseObject(payLog.getDetail(), PayNotify.Alipay.class);
        }
        
        res.setResult("");
        res.setMessage("");
        
        rep.setData(res);

        return rep;
    }
    
}
