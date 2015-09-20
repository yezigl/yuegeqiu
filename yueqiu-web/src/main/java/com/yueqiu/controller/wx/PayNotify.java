/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.controller.wx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月20日
 */
@XStreamAlias("xml")
public class PayNotify {

    String return_code; // 是
    String return_msg; // 否

    String appid; // 是
    String mch_id; // 是
    String device_info; // 否
    String nonce_str; // 是
    String sign; // 是
    
    String result_code; // 是
    String err_code; // 否
    String err_code_des; // 否
    
    String openid; // 是 用户在商户appid下的唯一标识
    String is_subscribe; // 否
    String trade_type; // 是
    String bank_type; // 是
    Integer total_fee; // 是 订单总金额，单位为分
    String fee_type; // 否
    Integer cash_fee; // 是 现金支付金额订单现金支付金额，详见支付金额
    String cash_fee_type; // 否
    Integer coupon_fee; // 否 代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额，详见支付金额
    Integer coupon_count; // 否 代金券或立减优惠使用数量
    // String coupon_id_$n; // 否
    // int coupon_fee_$n; // 否 单个代金券或立减优惠支付金额,$n为下标，从0开始编号
    String transaction_id; // 是 微信支付订单号
    String out_trade_no; // 是
    String attach; // 否
    String time_end; // 是 支付完成时间，格式为yyyyMMddHHmmss
    
    public boolean checkSign() {
        String signActual = sign();
        return signActual.equals(this.sign);
    }
    
    public String sign() {
        Map<String, String> map =  new TreeMap<>();
        map.put("return_code", return_code);
        map.put("return_msg", return_msg);
        map.put("appid", appid);
        map.put("mch_id", mch_id);
        map.put("device_info", device_info);
        map.put("nonce_str", nonce_str);
        
        map.put("result_code", result_code);
        map.put("err_code", err_code);
        map.put("err_code_des", err_code_des);
        map.put("openid", openid);
        map.put("is_subscribe", is_subscribe);
        map.put("trade_type", trade_type);
        map.put("bank_type", bank_type);
        map.put("total_fee", String.valueOf(total_fee));
        map.put("fee_type", fee_type);
        map.put("cash_fee", String.valueOf(cash_fee));
        map.put("cash_fee_type", cash_fee_type);
        map.put("coupon_fee", String.valueOf(coupon_fee));
        map.put("coupon_count", String.valueOf(coupon_count));
        map.put("transaction_id", transaction_id);
        map.put("out_trade_no", out_trade_no);
        map.put("attach", attach);
        map.put("time_end", time_end);
        List<String> signList = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                signList.add(entry.getKey() + "=" + entry.getValue());
            }
        }
        String signStr = StringUtils.join(signList, "&") + "&key=" + Weixin.API_KEY;
        String sign = DigestUtils.md5Hex(signStr).toUpperCase();
        System.out.println(signStr);
        System.out.println(sign);
        return sign;
    }
}
