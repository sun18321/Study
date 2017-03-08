package com.sun.requestBean;

import java.util.List;

/**
 * Created by sun on 2017/2/9.
 */
public class DiamondBean {

    /**
     * coins : [{"diamond":"60","memo":"","money":"6"},{"diamond":"300","memo":"","money":"30"},{"diamond":"980","memo":"赠送20钻石","money":"98"},{"diamond":"2980","memo":"赠送120钻石","money":"298"},{"diamond":"5880","memo":"赠送320钻石","money":"588"},{"diamond":"18880","memo":"赠送1120钻石","money":"1888"},{"diamond":"56000","memo":"赠送4000钻石","money":"5600"}]
     * diamond : 23
     * msg :
     * stat : 200
     */

    private String diamond;
    private String msg;
    private int stat;
    /**
     * diamond : 60
     * memo :
     * money : 6
     */

    private List<CoinsBean> coins;

    public String getDiamond() {
        return diamond;
    }

    public void setDiamond(String diamond) {
        this.diamond = diamond;
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

    public List<CoinsBean> getCoins() {
        return coins;
    }

    public void setCoins(List<CoinsBean> coins) {
        this.coins = coins;
    }

    public static class CoinsBean {
        private String diamond;
        private String memo;
        private String money;

        public String getDiamond() {
            return diamond;
        }

        public void setDiamond(String diamond) {
            this.diamond = diamond;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
