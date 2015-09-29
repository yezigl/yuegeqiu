/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.controller.weixin;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orion.core.utils.HttpUtils;
import com.orion.core.utils.Utils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.yueqiu.core.entity.Order;
import com.yueqiu.core.entity.PayLog;
import com.yueqiu.core.model.OrderStatus;
import com.yueqiu.core.model.PayType;
import com.yueqiu.core.utils.Constants;
import com.yueqiu.web.controller.AbstractController;
import com.yueqiu.web.res.Representation;
import com.yueqiu.web.res.Status;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月20日
 */
@Controller
@RequestMapping("/v1/payment")
public class WxPayController extends AbstractController {

    String ORDER_TIME_FORMAT = "yyyyMMddHHmmss";

    static XStream xstream = new XStream(new XppDriver(new NoNameCoder()));

    static {
        xstream.processAnnotations(new Class<?>[] { UnifiedOrder.class, PrePay.class, PayNotify.class });
    }

    @RequestMapping(value = "/weixin/unifiedorder", method = RequestMethod.POST)
    @ResponseBody
    public Representation unifiedorder(@RequestParam(value = "orderId") String orderId,
            @RequestHeader(value = "X-Forwarded-For", required = false) String forwardIp,
            @RequestHeader(value = "X-Real-IP", required = false) String realIp) {

        Representation rep = new Representation();
        Order order = orderService.get(orderId);
        if (order == null) {
            rep.setError(Status.ERROR_400, "订单不存在");
            return rep;
        }
        orderService.update(order);

        UnifiedOrder unifiedOrder = new UnifiedOrder(Weixin.APP_ID, Weixin.MCH_ID);
        unifiedOrder.setBody(order.getActivity().getTitle());
        unifiedOrder.setOutTradeNo(order.getId().toString());
        unifiedOrder.setFeeType(Weixin.FEE_TYPE);
        unifiedOrder.setTotalFee((int) (order.getAmount() * 100));
        unifiedOrder.setSpbillCreateIp(Utils.getClientIP(forwardIp, realIp));
        unifiedOrder.setTimeStart(DateFormatUtils.format(order.getCreateTime(), ORDER_TIME_FORMAT));
        unifiedOrder.setTimeExpire(DateFormatUtils.format(
                DateUtils.addMilliseconds(order.getUpdateTime(), Constants.ORDER_EXPIRE_TIME), ORDER_TIME_FORMAT));
        unifiedOrder.setNotifyUrl(Weixin.NOTIFY_URL);
        unifiedOrder.setTradeType(Weixin.TRADE_TYPE);

        unifiedOrder.sign();

        // 转为xml，并发送
        String xml = xstream.toXML(unifiedOrder);
        String ret = HttpUtils.post(Weixin.UNIFIED_ORDER_URL, xml);
        logger.info("receive weixin prepay response {}", ret);
        // 返回结果转为pojo
        PrePay prePay = (PrePay) xstream.fromXML(ret);
        if (!prePay.checkSign()) {
            rep.setError(Status.ERROR_400, "签名错误");
            return rep;
        }

        PrePayRes res = new PrePayRes();
        if (prePay.result_code.equals(Weixin.RETURN_SUCCESS)) {
            res.setAppId(Weixin.APP_ID);
            res.setPartnerId(Weixin.MCH_ID);
            res.setPrepayId(prePay.prepay_id);
            res.setNonceStr(prePay.nonce_str);
            res.setTimestamp(System.currentTimeMillis() / 1000);
            res.setPackageValue(Weixin.PACKAGE);
            res.setSign(prePay.sign(res.getTimestamp()));
        } else {
            res.setStatus(Status.ERROR_400, prePay.err_code_des);
        }
        
        rep.setData(res);

        return rep;
    }
    
    @RequestMapping(value = "/weixin/callback")
    public String payCallback(@RequestBody String xml) {
        
        logger.info("receive weixin callback response {}", xml);
        
        PayNotifyRes res = new PayNotifyRes();
        res.return_code = Weixin.RETURN_SUCCESS;
        
        PayNotify payNotify = (PayNotify) xstream.fromXML(xml);
        
        if (!payNotify.checkSign()) {
            res.return_code = Weixin.RETURN_FAIL;
            res.return_msg = "签名错误";
        }
        
        if (payNotify.isSuccess()) {
            PayLog payLog = new PayLog();
            Order order = orderService.get(payNotify.out_trade_no);
            if (order == null) {
                res.return_code = Weixin.RETURN_FAIL;
                res.return_msg = "找不到订单";
            } else {
                if (order.isPayed()) {
                    // 如果已经收到过callback
                } else {
                    if (payNotify.isPaySuccess()) {
                        // 更新订单信息
                        order.setPayType(PayType.WEIXIN);
                        order.setPayTime(new Date());
                        order.setStatus(OrderStatus.PAYED.code);
                        orderService.update(order);
                        payLog.setStatus(1);
                    } else {
                        payLog.setStatus(0);
                    }
                    // 记录更新流水
                    payLog.setDetail(xml);
                    payLog.setOrder(order);
                    payLog.setActivity(order.getActivity());
                    payLog.setUser(order.getUser());
                    payLog.setPayType(PayType.WEIXIN);
                    payLogService.create(payLog);
                }
            }
        } else {
            res.return_code = Weixin.RETURN_FAIL;
            res.return_msg = payNotify.return_msg;
        }
        
        return xstream.toXML(res);
    }
}
