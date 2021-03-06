/**
 * Copyright 2014 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yueqiu.core.entity.User;

/**
 * description here
 *
 * @author yezi
 * @since 2014年11月7日
 */
public class UserRes extends Res {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonInclude(Include.NON_NULL)
    private String id;
    @JsonInclude(Include.NON_NULL)
    private String mobile;
    @JsonInclude(Include.NON_NULL)
    private String nickname;
    @JsonInclude(Include.NON_NULL)
    private String avatar;

    public UserRes() {

    }

    public UserRes(User user) {
        this.id = user.getId().toString();
        this.mobile = user.getMobile();
        this.nickname = user.getNickname();
        this.avatar = user.getAvatar();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? "" : nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? "" : avatar;
    }

}
