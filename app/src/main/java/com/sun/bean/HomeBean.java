package com.sun.bean;

import java.util.List;

/**
 * Created by sun on 2017/10/31.
 */

public class HomeBean {

    /**
     * stat : 200
     * msg :
     * userinfo : {"face":"http://yinghua-1253248688.image.myqcloud.com/face/10/183787621702160116.jpg!mface","sex":"2","nickname":"不要face","diamond":"1377","send_diamond":"234","recv_diamond":"126115","exchange_diamond":"860","fans_total":"161","atten_total":"8","guard":"14321521","exp_value":"12561","city":"上海市","signature":"啊啊啊","tag":"","is_live":"0","goodid":"0","zuojia":"0","type":"1","onetone":"0","lat":"31.20533","lng":"121.6719","wx_bindid":0,"uid":"18378762","grade":8,"grade_percent":61,"next_exp":20480,"url":"rtmp://180.97.72.166/youmei/18378762?sign=5280231408a411eb0fce9ea6a8022a33&t=59f7eaac&domain=pili-live-rtmp.hainantaohua.com","offical":1,"distance":0,"tops":[{"uid":14321521,"grade":57,"send_diamond":113868,"nickname":"我是谁","face":"http://yinghua-1253248688.image.myqcloud.com/face/113/143215211710250336.jpg!mface"},{"uid":15965042,"grade":18,"send_diamond":10893,"nickname":"青哥","face":"http://yinghua-1253248688.image.myqcloud.com/face/114/159650421710180756.jpg!mface"},{"uid":19211557,"grade":5,"send_diamond":150,"nickname":"甜美","face":"http://yinghua-1253248688.image.myqcloud.com/face/37/192115571705071144.jpg!mface"}],"room_manager":0}
     */

    private int stat;
    private String msg;
    private UserinfoBean userinfo;

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public static class UserinfoBean {
        /**
         * face : http://yinghua-1253248688.image.myqcloud.com/face/10/183787621702160116.jpg!mface
         * sex : 2
         * nickname : 不要face
         * diamond : 1377
         * send_diamond : 234
         * recv_diamond : 126115
         * exchange_diamond : 860
         * fans_total : 161
         * atten_total : 8
         * guard : 14321521
         * exp_value : 12561
         * city : 上海市
         * signature : 啊啊啊
         * tag :
         * is_live : 0
         * goodid : 0
         * zuojia : 0
         * type : 1
         * onetone : 0
         * lat : 31.20533
         * lng : 121.6719
         * wx_bindid : 0
         * uid : 18378762
         * grade : 8
         * grade_percent : 61
         * next_exp : 20480
         * url : rtmp://180.97.72.166/youmei/18378762?sign=5280231408a411eb0fce9ea6a8022a33&t=59f7eaac&domain=pili-live-rtmp.hainantaohua.com
         * offical : 1
         * distance : 0
         * tops : [{"uid":14321521,"grade":57,"send_diamond":113868,"nickname":"我是谁","face":"http://yinghua-1253248688.image.myqcloud.com/face/113/143215211710250336.jpg!mface"},{"uid":15965042,"grade":18,"send_diamond":10893,"nickname":"青哥","face":"http://yinghua-1253248688.image.myqcloud.com/face/114/159650421710180756.jpg!mface"},{"uid":19211557,"grade":5,"send_diamond":150,"nickname":"甜美","face":"http://yinghua-1253248688.image.myqcloud.com/face/37/192115571705071144.jpg!mface"}]
         * room_manager : 0
         */

        private String face;
        private String sex;
        private String nickname;
        private String diamond;
        private String send_diamond;
        private String recv_diamond;
        private String exchange_diamond;
        private String fans_total;
        private String atten_total;
        private String guard;
        private String exp_value;
        private String city;
        private String signature;
        private String tag;
        private String is_live;
        private String goodid;
        private String zuojia;
        private String type;
        private String onetone;
        private String lat;
        private String lng;
        private int wx_bindid;
        private String uid;
        private int grade;
        private int grade_percent;
        private int next_exp;
        private String url;
        private int offical;
        private int distance;
        private int room_manager;
        private List<TopsBean> tops;

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getDiamond() {
            return diamond;
        }

        public void setDiamond(String diamond) {
            this.diamond = diamond;
        }

        public String getSend_diamond() {
            return send_diamond;
        }

        public void setSend_diamond(String send_diamond) {
            this.send_diamond = send_diamond;
        }

        public String getRecv_diamond() {
            return recv_diamond;
        }

        public void setRecv_diamond(String recv_diamond) {
            this.recv_diamond = recv_diamond;
        }

        public String getExchange_diamond() {
            return exchange_diamond;
        }

        public void setExchange_diamond(String exchange_diamond) {
            this.exchange_diamond = exchange_diamond;
        }

        public String getFans_total() {
            return fans_total;
        }

        public void setFans_total(String fans_total) {
            this.fans_total = fans_total;
        }

        public String getAtten_total() {
            return atten_total;
        }

        public void setAtten_total(String atten_total) {
            this.atten_total = atten_total;
        }

        public String getGuard() {
            return guard;
        }

        public void setGuard(String guard) {
            this.guard = guard;
        }

        public String getExp_value() {
            return exp_value;
        }

        public void setExp_value(String exp_value) {
            this.exp_value = exp_value;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getIs_live() {
            return is_live;
        }

        public void setIs_live(String is_live) {
            this.is_live = is_live;
        }

        public String getGoodid() {
            return goodid;
        }

        public void setGoodid(String goodid) {
            this.goodid = goodid;
        }

        public String getZuojia() {
            return zuojia;
        }

        public void setZuojia(String zuojia) {
            this.zuojia = zuojia;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOnetone() {
            return onetone;
        }

        public void setOnetone(String onetone) {
            this.onetone = onetone;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public int getWx_bindid() {
            return wx_bindid;
        }

        public void setWx_bindid(int wx_bindid) {
            this.wx_bindid = wx_bindid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public int getGrade_percent() {
            return grade_percent;
        }

        public void setGrade_percent(int grade_percent) {
            this.grade_percent = grade_percent;
        }

        public int getNext_exp() {
            return next_exp;
        }

        public void setNext_exp(int next_exp) {
            this.next_exp = next_exp;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getOffical() {
            return offical;
        }

        public void setOffical(int offical) {
            this.offical = offical;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getRoom_manager() {
            return room_manager;
        }

        public void setRoom_manager(int room_manager) {
            this.room_manager = room_manager;
        }

        public List<TopsBean> getTops() {
            return tops;
        }

        public void setTops(List<TopsBean> tops) {
            this.tops = tops;
        }

        public static class TopsBean {
            /**
             * uid : 14321521
             * grade : 57
             * send_diamond : 113868
             * nickname : 我是谁
             * face : http://yinghua-1253248688.image.myqcloud.com/face/113/143215211710250336.jpg!mface
             */

            private int uid;
            private int grade;
            private int send_diamond;
            private String nickname;
            private String face;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public int getSend_diamond() {
                return send_diamond;
            }

            public void setSend_diamond(int send_diamond) {
                this.send_diamond = send_diamond;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }
        }
    }
}
