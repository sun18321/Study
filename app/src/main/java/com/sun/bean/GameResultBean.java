package com.sun.bean;

import java.util.List;

/**
 * Created by sun on 2017/6/15.
 */

public class GameResultBean {
    /**
     * type : result
     * data : {"roomId":"13423256","result":{"win":1,"detail":[{"cards":["A7","B7","A3","D7","D3"],"result":0},{"cards":["D10","D13","C11","C12","B11"],"result":10},{"cards":["B4","C4","C8","D2","D8"],"result":6}]}}
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
         * result : {"win":1,"detail":[{"cards":["A7","B7","A3","D7","D3"],"result":0},{"cards":["D10","D13","C11","C12","B11"],"result":10},{"cards":["B4","C4","C8","D2","D8"],"result":6}]}
         */

        private String roomId;
        private ResultBean result;

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * win : 1
             * detail : [{"cards":["A7","B7","A3","D7","D3"],"result":0},{"cards":["D10","D13","C11","C12","B11"],"result":10},{"cards":["B4","C4","C8","D2","D8"],"result":6}]
             */

            private int win;
            private List<DetailBean> detail;

            public int getWin() {
                return win;
            }

            public void setWin(int win) {
                this.win = win;
            }

            public List<DetailBean> getDetail() {
                return detail;
            }

            public void setDetail(List<DetailBean> detail) {
                this.detail = detail;
            }

            public static class DetailBean {
                /**
                 * cards : ["A7","B7","A3","D7","D3"]
                 * result : 0
                 */

                private int result;
                private List<String> cards;

                public int getResult() {
                    return result;
                }

                public void setResult(int result) {
                    this.result = result;
                }

                public List<String> getCards() {
                    return cards;
                }

                public void setCards(List<String> cards) {
                    this.cards = cards;
                }
            }
        }
    }
}
