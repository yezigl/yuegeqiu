/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.res;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.yueqiu.entity.Message;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月22日
 */
public class MessageRes extends Res {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String title;
    private String content;
    private String status;
    private String createTime;

    public MessageRes() {

    }

    public MessageRes(Message message) {
        this.title = message.getTitle();
        this.content = message.getContent();
        this.status = message.getStatus();
        this.createTime = DateFormatUtils.format(message.getCreateTime(), "yyyyMMdd HH:mm");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
