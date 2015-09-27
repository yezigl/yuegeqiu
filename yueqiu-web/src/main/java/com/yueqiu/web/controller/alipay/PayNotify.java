/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.controller.alipay;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月22日
 */
@XStreamAlias("xml")
class PayNotify {

    String notify_time; // 通知时间 Date 通知的发送时间。格式为yyyy-MM-dd HH:mm:ss。 不可空
    String notify_type; // 通知类型 String 通知的类型。 不可空
    String notify_id; // 通知校验ID String 通知校验ID。 不可空
    String sign_type; // 签名方式 String 固定取值为RSA。 不可空
    String sign; // 签名 String 请参见安全接入与验证。 不可空
    String out_trade_no; // 商户网站唯一订单号 String(64)
                         // 对应商户网站的订单系统中的唯一订单号，非支付宝交易号。需保证在商户网站中的唯一性。是请求时对应的参数，原样返回。
                         // 可空
    String subject; // 商品名称 String(128)
                    // 商品的标题/交易标题/订单标题/订单关键字等。它在支付宝的交易明细中排在第一列，对于财务对账尤为重要。是请求时对应的参数，原样通知回来。
                    // 可空
    String payment_type; // 支付类型 String(4) 支付类型。默认值为：1（商品购买）。 可空
    String trade_no; // 支付宝交易号 String(64) 该交易在支付宝系统中的交易流水号。最短16位，最长64位。 可空
    String trade_status; // 交易状态 String 交易状态，取值范围请参见“11.3 交易状态”。 可空
    String seller_id; // 卖家支付宝用户号 String(30) 卖家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。
                      // 可空
    String seller_email; // 卖家支付宝账号 String(100) 卖家支付宝账号，可以是email和手机号码。 可空
                         // xxx@alipay.com
    String buyer_id; // 买家支付宝用户号 String(30) 买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。
                     // 可空
    String buyer_email; // 买家支付宝账号 String(100) 买家支付宝账号，可以是Email或手机号码。 可空
    float total_fee; // 交易金额 Number 该笔订单的总金额。请求时对应的参数，原样通知回来。 可空
    int quantity; // 购买数量 Number 购买数量，固定取值为1（请求时使用的是total_fee）。 可空
    float price; // 商品单价 Number price等于total_fee（请求时使用的是total_fee）。 可空
    String body; // 商品描述 String(512) 该笔订单的备注、描述、明细等。对应请求时的body参数，原样通知回来。 可空
    String gmt_create; // 交易创建时间 Date 该笔交易创建的时间。格式为yyyy-MM-dd HH:mm:ss。 可空
    String gmt_payment; // 交易付款时间 Date 该笔交易的买家付款时间。格式为yyyy-MM-dd HH:mm:ss。 可空
    String is_total_fee_adjust; // 是否调整总价 String(1) 该交易是否调整过价格。 可空 N
    String use_coupon; // 是否使用红包买家 String(1) 是否在交易过程中使用了红包。 可空 N
    float discount; // 折扣 String 支付宝系统会把discount的值加到交易金额上，如果有折扣，本参数为负数，单位为元。 可空
                    // 0.00
    String refund_status; // 退款状态 String 取值范围请参见SDK参数说明->退款状态。 可空
    String gmt_refund; // 退款时间 Date 卖家退款的时间，退款通知时会发送。格式为yyyy-MM-dd HH:mm:ss。 可空
    
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("notify_time", notify_time);
        builder.append("notify_type", notify_type);
        builder.append("notify_id", notify_id);
        builder.append("sign_type", sign_type);
        builder.append("sign", sign);
        builder.append("out_trade_no", out_trade_no);
        builder.append("subject", subject);
        builder.append("payment_type", payment_type);
        builder.append("trade_no", trade_no);
        builder.append("trade_status", trade_status);
        builder.append("seller_id", seller_id);
        builder.append("seller_email", seller_email);
        builder.append("buyer_id", buyer_id);
        builder.append("buyer_email", buyer_email);
        builder.append("total_fee", total_fee);
        builder.append("quantity", quantity);
        builder.append("price", price);
        builder.append("body", body);
        builder.append("gmt_create", gmt_create);
        builder.append("gmt_payment", gmt_payment);
        builder.append("is_total_fee_adjust", is_total_fee_adjust);
        builder.append("use_coupon", use_coupon);
        builder.append("discount", discount);
        builder.append("refund_status", refund_status);
        builder.append("gmt_refund", gmt_refund);
        return builder.toString();
    }
    
}
