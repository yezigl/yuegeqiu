/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.controller.weixin;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月20日
 */
class Weixin {
    
    public static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static String APP_ID = "wx6e7743c505a4881b";
    
    public static String MCH_ID = "1272650001";
    
    public static String API_KEY = "33fb1cfab5c90860cf9f854ddbcb61f4";
    
    public static String NOTIFY_URL = "http://api.yueqiua.com/v1/payment/weixin/callback";
    
    public static String FEE_TYPE = "CNY";
    
    public static String TRADE_TYPE = "APP";
    
    public static String PACKAGE = "Sign=WXPay";
    
    public static String RETURN_SUCCESS = "SUCCESS";
    
    public static String RETURN_FAIL = "FAIL";
}
