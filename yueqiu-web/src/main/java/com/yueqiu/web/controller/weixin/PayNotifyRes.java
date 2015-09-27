/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.controller.weixin;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月20日
 */
@XStreamAlias("xml")
class PayNotifyRes {

    String return_code;
    String return_msg;
}
