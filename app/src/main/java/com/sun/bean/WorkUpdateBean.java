package com.sun.bean;

/**
 * Created by sun on 2017/5/27.
 */

public class WorkUpdateBean {

    /**
     * stat : 200
     * msg :
     * gift_version : 52
     * userinfo : {"info":"","uid":"13423256","account":"18555556688","sex":1,"nickname":" 名字忘带了","face":"http://bz-cdn.lishuwh.com//face/152/134232561704260508mface.jpg","recv_diamond":40000,"send_diamond":4,"diamond":1876,"fans_total":35,"atten_total":3,"live_total":0,"grade":5,"im_token":"409cG7sJ/AaoIAeavdb1Oz6c2TB4cO4EIIcM2NM0IrMXdFKRK1yOf1QNhHlK8kBM+mS1BBefQfN5QY2qWOixuA==","goodid":0,"zuojia":0,"offical":1,"onetone":0,"msg_tip":"","private_chat_status":0,"private_chat":0,"show_manager":1}
     * step : 3
     * uplive_url : rtmp://pili-live-rtmp.lishuwh.com/baozi
     * upnew : {"updateUrl":"http://oq0yaa7bo.bkt.clouddn.com/tangyuan_v4.apk","versonCode":4,"updateMessage":"功能改进，如不更新将无法观看直播","upType":1}
     * filterurl : http://yinghua-1253248688.cosgz.myqcloud.com/down/filtermsg.txt
     * filter_version : 22
     */

    private int stat;
    private String msg;
    private int gift_version;
    private UserinfoBean userinfo;
    private int step;
    private String uplive_url;
    private UpnewBean upnew;
    private String filterurl;
    private int filter_version;

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

    public int getGift_version() {
        return gift_version;
    }

    public void setGift_version(int gift_version) {
        this.gift_version = gift_version;
    }

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getUplive_url() {
        return uplive_url;
    }

    public void setUplive_url(String uplive_url) {
        this.uplive_url = uplive_url;
    }

    public UpnewBean getUpnew() {
        return upnew;
    }

    public void setUpnew(UpnewBean upnew) {
        this.upnew = upnew;
    }

    public String getFilterurl() {
        return filterurl;
    }

    public void setFilterurl(String filterurl) {
        this.filterurl = filterurl;
    }

    public int getFilter_version() {
        return filter_version;
    }

    public void setFilter_version(int filter_version) {
        this.filter_version = filter_version;
    }

    public static class UserinfoBean {
        /**
         * info :
         * uid : 13423256
         * account : 18555556688
         * sex : 1
         * nickname :  名字忘带了
         * face : http://bz-cdn.lishuwh.com//face/152/134232561704260508mface.jpg
         * recv_diamond : 40000
         * send_diamond : 4
         * diamond : 1876
         * fans_total : 35
         * atten_total : 3
         * live_total : 0
         * grade : 5
         * im_token : 409cG7sJ/AaoIAeavdb1Oz6c2TB4cO4EIIcM2NM0IrMXdFKRK1yOf1QNhHlK8kBM+mS1BBefQfN5QY2qWOixuA==
         * goodid : 0
         * zuojia : 0
         * offical : 1
         * onetone : 0
         * msg_tip :
         * private_chat_status : 0
         * private_chat : 0
         * show_manager : 1
         */

        private String info;
        private String uid;
        private String account;
        private int sex;
        private String nickname;
        private String face;
        private int recv_diamond;
        private int send_diamond;
        private int diamond;
        private int fans_total;
        private int atten_total;
        private int live_total;
        private int grade;
        private String im_token;
        private int goodid;
        private int zuojia;
        private int offical;
        private int onetone;
        private String msg_tip;
        private int private_chat_status;
        private int private_chat;
        private int show_manager;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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

        public int getDiamond() {
            return diamond;
        }

        public void setDiamond(int diamond) {
            this.diamond = diamond;
        }

        public int getFans_total() {
            return fans_total;
        }

        public void setFans_total(int fans_total) {
            this.fans_total = fans_total;
        }

        public int getAtten_total() {
            return atten_total;
        }

        public void setAtten_total(int atten_total) {
            this.atten_total = atten_total;
        }

        public int getLive_total() {
            return live_total;
        }

        public void setLive_total(int live_total) {
            this.live_total = live_total;
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

        public int getGoodid() {
            return goodid;
        }

        public void setGoodid(int goodid) {
            this.goodid = goodid;
        }

        public int getZuojia() {
            return zuojia;
        }

        public void setZuojia(int zuojia) {
            this.zuojia = zuojia;
        }

        public int getOffical() {
            return offical;
        }

        public void setOffical(int offical) {
            this.offical = offical;
        }

        public int getOnetone() {
            return onetone;
        }

        public void setOnetone(int onetone) {
            this.onetone = onetone;
        }

        public String getMsg_tip() {
            return msg_tip;
        }

        public void setMsg_tip(String msg_tip) {
            this.msg_tip = msg_tip;
        }

        public int getPrivate_chat_status() {
            return private_chat_status;
        }

        public void setPrivate_chat_status(int private_chat_status) {
            this.private_chat_status = private_chat_status;
        }

        public int getPrivate_chat() {
            return private_chat;
        }

        public void setPrivate_chat(int private_chat) {
            this.private_chat = private_chat;
        }

        public int getShow_manager() {
            return show_manager;
        }

        public void setShow_manager(int show_manager) {
            this.show_manager = show_manager;
        }
    }

    public static class UpnewBean {
        /**
         * updateUrl : http://oq0yaa7bo.bkt.clouddn.com/tangyuan_v4.apk
         * versonCode : 4
         * updateMessage : 功能改进，如不更新将无法观看直播
         * upType : 1
         */

        private String updateUrl;
        private int versonCode;
        private String updateMessage;
        private int upType;

        public String getUpdateUrl() {
            return updateUrl;
        }

        public void setUpdateUrl(String updateUrl) {
            this.updateUrl = updateUrl;
        }

        public int getVersonCode() {
            return versonCode;
        }

        public void setVersonCode(int versonCode) {
            this.versonCode = versonCode;
        }

        public String getUpdateMessage() {
            return updateMessage;
        }

        public void setUpdateMessage(String updateMessage) {
            this.updateMessage = updateMessage;
        }

        public int getUpType() {
            return upType;
        }

        public void setUpType(int upType) {
            this.upType = upType;
        }
    }
}
