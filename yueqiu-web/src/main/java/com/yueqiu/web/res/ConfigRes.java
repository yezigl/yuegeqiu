/**
 * Copyright 2015 meituan.com. All Rights Reserved.
 */
package com.yueqiu.web.res;

/**
 * description here
 *
 * @author lidehua
 * @since 2015年11月6日
 */
public class ConfigRes extends Res {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private boolean useHttps;

    public boolean isUseHttps() {
        return useHttps;
    }

    public void setUseHttps(boolean useHttps) {
        this.useHttps = useHttps;
    }

}
