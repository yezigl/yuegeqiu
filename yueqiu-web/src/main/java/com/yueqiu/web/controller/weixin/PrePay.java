/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.controller.weixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * description here
 * 
 * https://pay.weixin.qq.com/wiki/doc/api/app.php?chapter=9_1
 *
 * @author yezi
 * @since 2015年9月20日
 */
@XStreamAlias("xml")
class PrePay {

    String return_code;
    String return_msg;

    String appid; // 是
    String mch_id; // 是
    String device_info; // 否
    String nonce_str; // 是
    String sign; // 是

    String result_code;
    String err_code;
    String err_code_des;

    String trade_type;
    String prepay_id;
    String code_url;


    public boolean checkSign() {
        Map<String, String> map = new TreeMap<>();
        map.put("return_code", return_code);
        map.put("return_msg", return_msg);
        map.put("appid", appid);
        map.put("mch_id", mch_id);
        map.put("device_info", device_info);
        map.put("nonce_str", nonce_str);
        map.put("result_code", result_code);
        map.put("err_code", err_code);
        map.put("err_code_des", err_code_des);
        map.put("trade_type", trade_type);
        map.put("prepay_id", prepay_id);
        map.put("code_url", code_url);
        List<String> signList = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                signList.add(entry.getKey() + "=" + entry.getValue());
            }
        }
        String signStr = StringUtils.join(signList, "&") + "&key=" + Weixin.API_KEY;
        String checkSign = DigestUtils.md5Hex(signStr).toUpperCase();
        return checkSign.equals(sign);
    }
    
    public String sign(long timestamp) {
        Map<String, String> map = new TreeMap<>();
        map.put("appid", appid);
        map.put("partnerid", mch_id);
        map.put("prepayid", prepay_id);
        map.put("noncestr", nonce_str);
        map.put("timestamp", String.valueOf(timestamp));
        map.put("package", Weixin.PACKAGE);
        List<String> signList = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                signList.add(entry.getKey() + "=" + entry.getValue());
            }
        }
        String signStr = StringUtils.join(signList, "&") + "&key=" + Weixin.API_KEY;
        return DigestUtils.md5Hex(signStr).toUpperCase();
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.append("returnCode", return_code);
        builder.append("returnMsg", return_msg);
        builder.append("appId", appid);
        builder.append("mchId", mch_id);
        builder.append("deviceInfo", device_info);
        builder.append("nonceStr", nonce_str);
        builder.append("sign", sign);
        builder.append("resultCode", result_code);
        builder.append("errCode", err_code);
        builder.append("errCodeDes", err_code_des);
        builder.append("tradeType", trade_type);
        builder.append("prepayId", prepay_id);
        builder.append("codeUrl", code_url);
        return builder.toString();
    }
    
    
}
