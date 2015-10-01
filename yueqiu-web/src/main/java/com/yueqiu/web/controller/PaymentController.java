/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.controller;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.yueqiu.core.entity.Order;
import com.yueqiu.core.entity.PayLog;
import com.yueqiu.core.utils.Constants;
import com.yueqiu.web.res.OrderRes;
import com.yueqiu.web.res.PayRes;
import com.yueqiu.web.res.Representation;
import com.yueqiu.web.res.Status;

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

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public Representation checkOrder(@PathVariable String orderId) {

        Representation rep = new Representation();
        PayRes res = new PayRes();
        
        Order order = orderService.get(orderId);
        if (order == null) {
            rep.setError(Status.ERROR_400, "订单不存在");
            return rep;
        }

        PayLog payLog = payLogService.getByOrder(order);
        
        if (payLog != null) {
            res.setStatus(payLog.getStatus());;
            res.setType(payLog.getPayType().name());
            res.setPayTime(DateFormatUtils.format(order.getPayTime(), Constants.ORDER_DATE_FORMAT));
        } else {
            res.setStatus(0);
        }
        res.setOrder(new OrderRes(order));
        
        rep.setData(res);

        return rep;
    }
    
}
