/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.utils;

import java.text.DecimalFormat;

/**
 * description here
 *
 * @author yezi
 * @since 2015年10月18日
 */
public class PriceUtils {
    
    private static DecimalFormat format = new DecimalFormat("#.00");

    /**
     * 数据库存储时使用分来存
     * @param price
     * @return
     */
    public static int parse(float price) {
        return (int) (price * Constants.PRICE_UNIT);
    }
    
    /**
     * 展示的时候使用元
     * @param price
     * @return
     */
    public static String format(int price) {
        return format.format(price / Constants.PRICE_UNIT);
    }
}
