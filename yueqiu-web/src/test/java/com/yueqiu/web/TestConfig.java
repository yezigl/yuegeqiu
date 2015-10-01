/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@Import(AppConfig.class)
@ImportResource("classpath:applicationContext-test.xml")
@ComponentScan(basePackages = "com.yueqiu.core.dao")
public class TestConfig {

}
