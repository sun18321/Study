package com.sun.bean;

/**
 * Created by sun on 2017/6/15.
 */

public class CountdownBean {

    /**
     * type : poll
     * data : {"roomId":"13423256","timecount":2}
     */

    private String type;
    private DataBean data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * roomId : 13423256
         * timecount : 2
         */

        private String roomId;
        private int timecount;

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public int getTimecount() {
            return timecount;
        }

        public void setTimecount(int timecount) {
            this.timecount = timecount;
        }
    }
}
