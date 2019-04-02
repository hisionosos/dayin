package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\4\28 0028.
 */

public class IntegrationBean {

    /**
     * status : 0
     * msg : 获取积分记录成功
     * total : 39
     * allinteral : 297
     * creditnum : 297
     * use : 0
     * list : [{"type":"1","num":2,"time":"2018-04-21 14:52:23"},{"type":"1","num":2,"time":"2018-04-19 19:38:43"},{"type":"1","num":2,"time":"2018-04-18 09:37:15"},{"type":"1","num":2,"time":"2018-04-17 13:41:53"},{"type":"5","num":5,"time":"2018-04-14 23:28:01"},{"type":"5","num":5,"time":"2018-04-14 23:27:01"},{"type":"1","num":2,"time":"2018-04-14 09:13:23"},{"type":"3","num":10,"time":"2018-04-13 18:13:17"},{"type":"5","num":5,"time":"2018-04-13 17:20:25"},{"type":"5","num":5,"time":"2018-04-13 16:14:05"},{"type":"5","num":5,"time":"2018-04-13 16:13:11"},{"type":"5","num":5,"time":"2018-04-13 16:05:15"},{"type":"1","num":2,"time":"2018-04-13 09:26:59"},{"type":"3","num":10,"time":"2018-04-12 10:43:51"},{"type":"1","num":2,"time":"2018-04-12 09:11:41"},{"type":"1","num":2,"time":"2018-04-10 09:40:35"},{"type":"3","num":10,"time":"2018-04-09 14:49:01"},{"type":"3","num":10,"time":"2018-04-09 14:47:39"},{"type":"3","num":10,"time":"2018-04-09 14:43:05"},{"type":"3","num":10,"time":"2018-04-09 14:13:30"}]
     */

    private int status;
    private String msg;
    private int total;
    private String allinteral;
    private String creditnum;
    private String use;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getAllinteral() {
        return allinteral;
    }

    public void setAllinteral(String allinteral) {
        this.allinteral = allinteral;
    }

    public String getCreditnum() {
        return creditnum;
    }

    public void setCreditnum(String creditnum) {
        this.creditnum = creditnum;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * type : 1
         * num : 2
         * time : 2018-04-21 14:52:23
         */

        private String type;
        private String num;
        private String time;

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        private String txt;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
