/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.controller.alipay;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.yueqiu.core.entity.Order;
import com.yueqiu.core.entity.PayLog;
import com.yueqiu.core.model.OrderStatus;
import com.yueqiu.core.model.PayType;
import com.yueqiu.web.controller.AbstractController;

/**
 * description here
 *
 * @author lidehua
 * @since 2015年6月17日
 */
@RestController
@RequestMapping(value = "/v1/payment")
public class AlipayController extends AbstractController {

    static XStream xstream = new XStream(new XppDriver(new NoNameCoder()));

    static {
        xstream.processAnnotations(new Class<?>[] { PayNotify.class });
    }

    @RequestMapping(value = "/alipay/callback")
    public String alipay(HttpServletRequest request, @ModelAttribute PayNotify payNotify) {

        logger.info("receive alipay callback response {}", payNotify.toString());
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }

        if (AlipayNotify.verify(params)) {// 验证成功
            // 请在这里加上商户的业务逻辑程序代码

            // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            PayLog payLog = new PayLog();
            Order order = orderService.get(payNotify.out_trade_no);
            if (order == null) {
                logger.warn("can not find order {}", payNotify.out_trade_no);
                return "fail";
            } else if (order.isPayed()) {
                logger.info("order has payed {}", payNotify.out_trade_no);
                return "success";
            }
            if (Alipay.TRADE_FINISHED.equals(payNotify.trade_status)) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序
                // 注意：
                // 该种交易状态只在两种情况下出现
                // 1、开通了普通即时到账，买家付款成功后。
                // 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
                // 更新订单信息
                order.setPayType(PayType.ALIPAY);
                order.setPayTime(new Date());
                order.setStatus(OrderStatus.PAYED);
                orderService.update(order);
                activityService.incrAttend(order.getActivity(), order.getQuantity()); // 参与人数+1
                payLog.setStatus(1);
                checkTaskService.cancel(order.getId());
                logger.info("order pay success {}", payNotify.out_trade_no);
            } else if (Alipay.TRADE_SUCCESS.equals(payNotify.trade_status)) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序
                // 注意：
                // 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
                // 更新订单信息
                order.setPayType(PayType.ALIPAY);
                order.setPayTime(new Date());
                order.setStatus(OrderStatus.PAYED);
                orderService.update(order);
                activityService.incrAttend(order.getActivity(), order.getQuantity()); // 参与人数+1
                payLog.setStatus(1);
                checkTaskService.cancel(order.getId());
                logger.info("order pay success {}", payNotify.out_trade_no);
            } else {
                logger.warn("tradeStatus maybe not right [{}]", payNotify.trade_status);
            }

            // 记录更新流水
            payLog.setDetail(xstream.toXML(payNotify));
            payLog.setOrder(order);
            payLog.setActivity(order.getActivity());
            payLog.setUser(order.getUser());
            payLog.setPayType(PayType.ALIPAY);
            payLogService.create(payLog);
            // ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            return "success"; // 请不要修改或删除
        } else {// 验证失败
            logger.warn("check verify fail, params = {}", params);
            return "fail";
        }
    }

    @RequestMapping(value = "/alipay/sign", method = RequestMethod.POST)
    public String sign(@RequestParam String orderId) {
        Order order = orderService.get(orderId);
        Map<String, Object> values = new TreeMap<>();
        values.put("service", Alipay.PAY_SERVICE);
        values.put("partner", Alipay.PARNTER);
        values.put("_input_charset", Alipay.CHARSET);
        //values.put("sign_type", Constants.ALIPAY_SIGN_TYPE);
        values.put("app_id", Alipay.APP_ID);
        values.put("notify_url", Alipay.NOTIFY_URL);
        values.put("out_trade_no", order.getId());
        values.put("subject", order.getActivity().getTitle());
        values.put("payment_type", Alipay.PAYMENT_TYPE);
        values.put("seller_id", Alipay.SELLER_ID);
        values.put("total_fee", order.getAmount() - order.getDiscount());
        values.put("body", order.getActivity().getTitle());
        values.put("it_b_pay", Alipay.EXPIRE_TIME);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            list.add(entry.getKey() + "=" + entry.getValue());
        }
        String orderInfo = StringUtils.join(list, "&");
        // 对订单做RSA 签名
        String sign = SignUtils.encodeURL(SignUtils.sign(orderInfo, Alipay.PRIVATE_KEY));
        
        return sign;
    }
}
