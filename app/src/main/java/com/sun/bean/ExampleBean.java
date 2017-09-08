package com.sun.bean;

/**
 * Created by sun on 2017/6/20.
 */

public class ExampleBean {

    /**
     * type : start
     * content : {"status":0,"data":{"roomId":"13423256"}}
     */

    private String type;
    private ContentBean content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * status : 0
         * data : {"roomId":"13423256"}
         */

        private int status;
        private DataBean data;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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
             */

            private String roomId;

            public String getRoomId() {
                return roomId;
            }

            public void setRoomId(String roomId) {
                this.roomId = roomId;
            }
        }
    }
}
