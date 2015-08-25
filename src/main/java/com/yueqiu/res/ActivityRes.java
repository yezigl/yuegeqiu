/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.res;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yueqiu.entity.User;
import com.yueqiu.utils.Constants;

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
    private OrderRes order;

    public ActivityRes() {
        players = new ArrayList<Player>();
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

    public OrderRes getOrder() {
        return order;
    }

    public void setOrder(OrderRes order) {
        this.order = order;
    }

    public void addPlayer(User user) {
        Player player = new Player();
        player.setAvatar(StringUtils.defaultString(user.getAvatar(), Constants.DEFAULT_AVATAR));
        player.setId(user.getId().toString());
        player.setNickname(user.getNickname());
        players.add(player);
    }

}
