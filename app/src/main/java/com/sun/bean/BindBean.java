package com.sun.bean;

import java.util.List;

/**
 * Created by sun on 2017/6/22.
 */

public class BindBean {

    /**
     * status : 0
     * data : [""]
     */

    private int status;
    private List<String> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
