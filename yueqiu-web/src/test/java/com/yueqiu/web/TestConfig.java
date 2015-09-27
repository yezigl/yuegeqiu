/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.yueqiu.web.AppConfig;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@Import(AppConfig.class)
@ComponentScan(basePackages = "com.yueqiu.dao")
public class TestConfig {

}
