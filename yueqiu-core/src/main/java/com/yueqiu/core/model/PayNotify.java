/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月22日
 */
public class PayNotify {

    @XStreamAlias("xml")
    public static class Weixin {
        
        public String return_code; // 是
        public String return_msg; // 否

        public String appid; // 是
        public String mch_id; // 是
        public String device_info; // 否
        public String nonce_str; // 是
        public String sign; // 是
        
        public String result_code; // 是
        public String err_code; // 否
        public String err_code_des; // 否
        
        public String openid; // 是 用户在商户appid下的唯一标识
        public String is_subscribe; // 否
        public String trade_type; // 是
        public String bank_type; // 是
        public Integer total_fee; // 是 订单总金额，单位为分
        public String fee_type; // 否
        public Integer cash_fee; // 是 现金支付金额订单现金支付金额，详见支付金额
        public String cash_fee_type; // 否
        public Integer coupon_fee; // 否 代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额，详见支付金额
        public Integer coupon_count; // 否 代金券或立减优惠使用数量
        // String coupon_id_$n; // 否
        // int coupon_fee_$n; // 否 单个代金券或立减优惠支付金额,$n为下标，从0开始编号
        public String transaction_id; // 是 微信支付订单号
        public String out_trade_no; // 是
        public String attach; // 否
        public String time_end; // 是 支付完成时间，格式为yyyyMMddHHmmss
    }
    
    public static class Alipay {
        
    }
}
