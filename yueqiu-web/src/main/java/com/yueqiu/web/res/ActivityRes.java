/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.res;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yueqiu.core.entity.Activity;
import com.yueqiu.core.entity.User;
import com.yueqiu.core.model.ActivityStatus;
import com.yueqiu.core.utils.Constants;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
public class ActivityRes extends Res {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    @JsonInclude(Include.NON_NULL)
    private Integer type;
    @JsonInclude(Include.NON_NULL)
    private StadiumRes stadium;
    @JsonInclude(Include.NON_NULL)
    private String date;
    @JsonInclude(Include.NON_NULL)
    private Float price;
    @JsonInclude(Include.NON_NULL)
    private Float value;
    @JsonInclude(Include.NON_NULL)
    private Integer total;
    @JsonInclude(Include.NON_NULL)
    private Integer attend;
    @JsonInclude(Include.NON_NULL)
    private UserRes organizer;
    @JsonInclude(Include.NON_EMPTY)
    private List<Player> players;
    @JsonInclude(Include.NON_NULL)
    private Integer status;
    @JsonInclude(Include.NON_NULL)
    private String statusStr;
    @JsonInclude(Include.NON_NULL)
    private String description;
    @JsonInclude(Include.NON_NULL)
    private Map<String, String> orderInfo;

    public ActivityRes() {
        players = new ArrayList<Player>();
    }

    public ActivityRes(Activity activity, User organizer, List<User> users) {
        players = new ArrayList<Player>();
        this.setId(activity.getId().toString());
        this.setTitle(activity.getTitle());
        this.setType(activity.getType());
        this.setDate(DateFormatUtils.format(activity.getDate(), Constants.ACTIVITY_DATE_FORMAT, Locale.CHINA));
        this.setPrice(activity.getPrice());
        this.setValue(activity.getPrice());
        this.setTotal(activity.getTotal());
        this.setAttend(activity.getAttend());
        this.setOrganizer(new UserRes(organizer));
        this.setStatus(activity.getStatus());
        this.setStatusStr(ActivityStatus.valueOfStatus(activity.getStatus()).text);
        this.setDescription(activity.getDescription());
        this.setStadium(new StadiumRes(activity.getStadium()));
        if (users != null) {
            for (User user : users) {
                this.addPlayer(user);
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public StadiumRes getStadium() {
        return stadium;
    }

    public void setStadium(StadiumRes stadium) {
        this.stadium = stadium;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Integer getAttend() {
        return attend;
    }

    public void setAttend(int attend) {
        this.attend = attend;
    }

    public UserRes getOrganizer() {
        return organizer;
    }

    public void setOrganizer(UserRes organizer) {
        this.organizer = organizer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Integer getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setAttend(Integer attend) {
        this.attend = attend;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Map<String, String> orderInfo) {
        this.orderInfo = orderInfo;
    }

    public void addPlayer(User user) {
        Player player = new Player();
        player.setAvatar(StringUtils.defaultString(user.getAvatar(), Constants.DEFAULT_AVATAR));
        player.setId(user.stringifyId());
        player.setNickname(user.getNickname());
        players.add(player);
    }

}
