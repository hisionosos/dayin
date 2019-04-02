package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\4\28 0028.
 */

public class AccountsBean {

    /**
     * status : 0
     * msg : 获取成功
     * pay : 160.00
     * recharge : 0.04
     * total : 3
     * list : [{"type":"2","time":"2018-04-14 20:35:01","num":"80.00"},{"type":"2","time":"2018-04-14 20:34:34","num":"80.00"},{"type":"3","time":"2018-04-08 17:02:37","num":"0.04"}]
     */

    private int status;
    private String msg;
    private String pay;
    private String recharge;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    private String money;
    private int total;
    private List<ListBean> list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getRecharge() {
        return recharge;
    }

    public void setRecharge(String recharge) {
        this.recharge = recharge;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * type : 2
         * time : 2018-04-14 20:35:01
         * num : 80.00
         */

        private String type;
        private String time;
        private String num;

        public String getTxt() {
            return txt;
        }

        public void setTxt(String text) {
            this.txt = text;
        }

        private String txt;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
