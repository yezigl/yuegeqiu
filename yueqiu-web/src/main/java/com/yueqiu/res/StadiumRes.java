/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.res;

import com.yueqiu.entity.Stadium;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
public class StadiumRes extends Res {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String name;
    private String thumbnail;
    private float longitude;
    private float latitude;
    private int size;
    private String[] gallery;

    public StadiumRes() {
    }

    public StadiumRes(Stadium stadium) {
        this.name = stadium.getName();
        this.thumbnail = stadium.getGallery()[0];
        this.longitude = stadium.getLongitude();
        this.latitude = stadium.getLatitude();
        this.size = stadium.getSize();
        this.gallery = stadium.getGallery();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String[] getGallery() {
        return gallery;
    }

    public void setGallery(String[] gallery) {
        this.gallery = gallery;
    }

}
