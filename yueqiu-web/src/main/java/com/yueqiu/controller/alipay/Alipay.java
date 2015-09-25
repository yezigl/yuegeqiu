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

    public static final String APP_ID = "2015092300320037";
    
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static final String PARNTER = "2088021786178833";

    // 商户的私钥
    public static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANYbRevrUtiS7ikO"
            + "YlQKhMykOdczP/JJ7TZ86ZdRjwCUzN801ctn0MSUwLltyvn/JXNcdH6kRoqWH2SI"
            + "Vt6XJEQ/xzHNCYUtQrvAobCU+pOB/6kWUP5L0xegKmrgZoOt2r2UHH5NghsezakF"
            + "IVyRtFeW0mGEG2FtP8w7aMEuSeMHAgMBAAECgYBNroOF/1GFpnmmh8OBPnfET+l0"
            + "fPG8lLnuRQ/ziPHbgiF68o6HSFlkmZv7Kyyz27DY1DioIi495g3pydXK2Qec2ZYu"
            + "KRDQ7AB9MUEBhpwnUeTI2HiMMdds1Y1/0x4u5wQJXGfMzU6Gyv+eLE8+0XiZUtGM"
            + "kKu8cleNCCdyV8yqwQJBAPGkw66aL9NfJCGWT6Se4PX+V6ZCNfI5G2ulvzColrKH"
            + "n64lJs/zzD/+ZOqX9javXyJXJR5+WCNirMEBDmd9mxECQQDi07BA2thpI/CpNKXp"
            + "Ypau7gRb/vh4kcCivVRSjJKu2kW1CCVJSD1/rLGY5U9Z4mx//exhN7E4QAzQCOKm"
            + "xKyXAkEAlL//cYEaxbxAiqhz2HuxMdzi6cbCXJyI46hBXFu7gKT6SAI4tTR33EDm"
            + "sH7hIuKr5oDMqcvOx+GbRKg4zL5GcQJAAckw5hHTCmDiEDL962x/SzlMh3zmb+yV"
            + "27Ip68x3izDkw5wgzwbmlQ7DBzUm/sotmIRjTBCoMI0wjKLFRhLeWQJBAO49qy7x"
            + "0FM0D7sbAwyeVdlFq0NYnpE+Uoi5RwhyXo2Daq/m+LFxXNsYHLtU6Tx/CU+NWn9N" 
            + "M6VXa1gT5zZZaoM=";

    // 支付宝的公钥，无需修改该值
    public static final String ALI_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    // 调试用，创建TXT日志文件夹路径
    public static final String LOG_PATH = "/tmp";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static final String INPUT_CHARSET = "utf-8";

    // 签名方式 不需修改
    public static final String SIGN_TYPE = "RSA";
}
