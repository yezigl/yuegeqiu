/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@Configuration
@ImportResource("classpath:applicationContext.xml")
@PropertySource(value = "classpath:application.properties")
public class AppConfig {

}
