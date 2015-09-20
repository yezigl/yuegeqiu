/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.controller.wx;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月20日
 */
public class Weixin {
    
    public static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static String APP_ID = "wxf2f565574a968187";
    
    public static String MCH_ID = "1233848001";
    
    public static String API_KEY = "412fde4e9c2e2bb619514ecea142e449";
    
    public static String NOTIFY_URL = "http://api.yueqiua.com/v1/weixin/pay/callback";
    
    public static String FEE_TYPE = "CNY";
    
    public static String TRADE_TYPE = "APP";
    
    public static String PACKAGE = "Sign=WXPay";
    
    public static String RETURN_SUCCESS = "SUCCESS";
    
    public static String RETURN_FAIL = "FAIL";
}
