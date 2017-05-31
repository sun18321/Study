package com.sun.bean;

/**
 * Created by sun on 2017/5/27.
 */

public class UpdateBean {

    /**
     * NewestVerson : 2
     * desc : 更新1,更新2
     * url : http://192.168.39.91:8080/safenet.apk
     */

    private String NewestVerson;
    private String desc;
    private String url;

    public String getNewestVerson() {
        return NewestVerson;
    }

    public void setNewestVerson(String NewestVerson) {
        this.NewestVerson = NewestVerson;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
