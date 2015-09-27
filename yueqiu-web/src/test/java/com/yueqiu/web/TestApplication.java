/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import com.yueqiu.web.AppConfig;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@ComponentScan
@ContextConfiguration(classes = AppConfig.class)
public class TestApplication {

}
