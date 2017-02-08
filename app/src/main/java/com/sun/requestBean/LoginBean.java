package com.sun.requestBean;

/**
 * Created by stephensun on 2017/2/8.
 */
public class LoginBean {

    /**
     * is_reviewing : 0
     * msg : 登陆成功
     * stat : 200
     * userinfo : {"age":27,"atten_total":8,"device_token":"6586fb0e0fa8acf7490f9ef2530b91eb15a85f4e","diamond":23,"face":"http://taohua-1253160199.image.myqcloud.com/face/209/179934251701220302.jpg!mface","fans_total":8,"geohash":"wtsk1hxvjy","goodid":0,"grade":5,"im_token":"nAI/cSze7iMU9TnB8CRgrqckDaXYeMDvmFz6f9EggkhUJbZgcwGMRQ75tGAbRSUUlI5RAHSCIj8PDhgys93+xQ==","msg_tip":"","nickname":"heh ","offical":0,"pf":"phone","private_chat_status":"尚未充值，无法发送私信","recv_diamond":70750,"send_diamond":132,"sex":1,"uid":"17993425","zuojia":0}
     */

    private int is_reviewing;
    private String msg;
    private int stat;
    private UserinfoBean userinfo;

    public int getIs_reviewing() {
        return is_reviewing;
    }

    public void setIs_reviewing(int is_reviewing) {
        this.is_reviewing = is_reviewing;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public static class UserinfoBean {
        /**
         * age : 27
         * atten_total : 8
         * device_token : 6586fb0e0fa8acf7490f9ef2530b91eb15a85f4e
         * diamond : 23
         * face : http://taohua-1253160199.image.myqcloud.com/face/209/179934251701220302.jpg!mface
         * fans_total : 8
         * geohash : wtsk1hxvjy
         * goodid : 0
         * grade : 5
         * im_token : nAI/cSze7iMU9TnB8CRgrqckDaXYeMDvmFz6f9EggkhUJbZgcwGMRQ75tGAbRSUUlI5RAHSCIj8PDhgys93+xQ==
         * msg_tip :
         * nickname : heh
         * offical : 0
         * pf : phone
         * private_chat_status : 尚未充值，无法发送私信
         * recv_diamond : 70750
         * send_diamond : 132
         * sex : 1
         * uid : 17993425
         * zuojia : 0
         */

        private int age;
        private int atten_total;
        private String device_token;
        private int diamond;
        private String face;
        private int fans_total;
        private String geohash;
        private int goodid;
        private int grade;
        private String im_token;
        private String msg_tip;
        private String nickname;
        private int offical;
        private String pf;
        private String private_chat_status;
        private int recv_diamond;
        private int send_diamond;
        private int sex;
        private String uid;
        private int zuojia;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getAtten_total() {
            return atten_total;
        }

        public void setAtten_total(int atten_total) {
            this.atten_total = atten_total;
        }

        public String getDevice_token() {
            return device_token;
        }

        public void setDevice_token(String device_token) {
            this.device_token = device_token;
        }

        public int getDiamond() {
            return diamond;
        }

        public void setDiamond(int diamond) {
            this.diamond = diamond;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public int getFans_total() {
            return fans_total;
        }

        public void setFans_total(int fans_total) {
            this.fans_total = fans_total;
        }

        public String getGeohash() {
            return geohash;
        }

        public void setGeohash(String geohash) {
            this.geohash = geohash;
        }

        public int getGoodid() {
            return goodid;
        }

        public void setGoodid(int goodid) {
            this.goodid = goodid;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getIm_token() {
            return im_token;
        }

        public void setIm_token(String im_token) {
            this.im_token = im_token;
        }

        public String getMsg_tip() {
            return msg_tip;
        }

        public void setMsg_tip(String msg_tip) {
            this.msg_tip = msg_tip;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getOffical() {
            return offical;
        }

        public void setOffical(int offical) {
            this.offical = offical;
        }

        public String getPf() {
            return pf;
        }

        public void setPf(String pf) {
            this.pf = pf;
        }

        public String getPrivate_chat_status() {
            return private_chat_status;
        }

        public void setPrivate_chat_status(String private_chat_status) {
            this.private_chat_status = private_chat_status;
        }

        public int getRecv_diamond() {
            return recv_diamond;
        }

        public void setRecv_diamond(int recv_diamond) {
            this.recv_diamond = recv_diamond;
        }

        public int getSend_diamond() {
            return send_diamond;
        }

        public void setSend_diamond(int send_diamond) {
            this.send_diamond = send_diamond;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public int getZuojia() {
            return zuojia;
        }

        public void setZuojia(int zuojia) {
            this.zuojia = zuojia;
        }
    }
}
