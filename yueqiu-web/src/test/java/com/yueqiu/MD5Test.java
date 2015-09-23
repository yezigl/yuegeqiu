/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月23日
 */
public class MD5Test {

    @Test
    public void testMd5() {
        String s = "appid=wxf2f565574a968187&err_code=OUT_TRADE_NO_USED&err_code_des=商户订单号重复&mch_id=1233848001&nonce_str=EFEb08edW6iOB3yA&result_code=FAIL&return_code=SUCCESS&return_msg=OK&key=412fde4e9c2e2bb619514ecea142e449";
        System.out.println(DigestUtils.md5Hex(s).toUpperCase());
    }
}
