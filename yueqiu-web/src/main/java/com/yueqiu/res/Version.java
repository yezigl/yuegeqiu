/**
 * Copyright 2014 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.res;

/**
 * description here
 *
 * @author yezi
 * @since 2014年11月23日
 */
public class Version extends Res {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private boolean update;
    private String changelog;
    private String latest;
    private String url;
    private boolean force;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public String getChangelog() {
        return changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

}
