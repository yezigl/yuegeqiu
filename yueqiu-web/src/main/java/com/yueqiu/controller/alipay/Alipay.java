/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.controller.alipay;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月22日
 */
class Alipay {

    public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
    
    public static final String TRADE_FINISHED = "TRADE_FINISHED";
    
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static final String PARNTER = "";
    
    // 商户的私钥
    public static final String PRIVATE_KEY = "";
    
    // 支付宝的公钥，无需修改该值
    public static final String ALI_PUBLIC_KEY  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    

    // 调试用，创建TXT日志文件夹路径
    public static final String LOG_PATH = "/tmp";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static final String INPUT_CHARSET = "utf-8";
    
    // 签名方式 不需修改
    public static final String SIGN_TYPE = "RSA";
}
