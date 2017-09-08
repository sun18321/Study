package com.sun.bean;

import java.util.List;

/**
 * Created by sun on 2017/6/15.
 */

public class StartGameBean {

    /**
     * status : 0
     * data : []
     */

    private int status;
    private List<?> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
