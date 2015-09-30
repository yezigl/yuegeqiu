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
import org.slf4j.LoggerFactory;

import com.orion.core.utils.CryptUtils;
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
class UnifiedOrder {
    
    @XStreamAlias(value = "appid")
    private String appId; // 是
    @XStreamAlias(value = "mch_id")
    private String mchId; // 是
    @XStreamAlias(value = "device_info")
    private String deviceInfo; // 否
    @XStreamAlias(value = "nonce_str")
    private String nonceStr; // 是
    private String sign; // 是
    private String body; // 是
    private String detail; // 否
    private String attach; // 否
    @XStreamAlias(value = "out_trade_no")
    private String outTradeNo; // 是
    @XStreamAlias(value = "fee_type")
    private String feeType; // 否
    @XStreamAlias(value = "total_fee")
    private int totalFee; // 是
    @XStreamAlias(value = "spbill_create_ip")
    private String spbillCreateIp; // 是
    @XStreamAlias(value = "time_start")
    private String timeStart; // 否
    @XStreamAlias(value = "time_expire")
    private String timeExpire; // 否
    @XStreamAlias(value = "goods_tag")
    private String goodsTag; // 否
    @XStreamAlias(value = "notify_url")
    private String notifyUrl; // 是
    @XStreamAlias(value = "trade_type")
    private String tradeType; // 是
    
    public UnifiedOrder(String appId, String mchId) {
        this.appId = appId;
        this.mchId = mchId;
        this.nonceStr = CryptUtils.nonce();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public UnifiedOrder sign() {
        Map<String, String> map =  new TreeMap<>();
        map.put("appid", appId);
        map.put("mch_id", mchId);
        map.put("device_info", deviceInfo);
        map.put("nonce_str", nonceStr);
        map.put("body", body);
        map.put("detail", detail);
        map.put("attach", attach);
        map.put("out_trade_no", outTradeNo);
        map.put("fee_type", feeType);
        map.put("total_fee", String.valueOf(totalFee));
        map.put("spbill_create_ip", spbillCreateIp);
        map.put("time_start", timeStart);
        map.put("time_expire", timeExpire);
        map.put("goods_tag", goodsTag);
        map.put("notify_url", notifyUrl);
        map.put("trade_type", tradeType);
        List<String> signList = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                signList.add(entry.getKey() + "=" + entry.getValue());
            }
        }
        String signStr = StringUtils.join(signList, "&") + "&key=" + Weixin.API_KEY;
        LoggerFactory.getLogger(getClass()).info(signStr);
        this.sign = DigestUtils.md5Hex(signStr).toUpperCase();
        return this;
    }
}
