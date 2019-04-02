package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\5\15 0015.
 */

public class WithdrawRecordBean {

    /**
     * status : 0
     * msg : 获取列表成功
     * total : 4
     * money : 1852.74
     * list : [{"time":"2018-05-12 19:29:10","cash_num":"1.00","cash_info":"18838112402","cash_type":"待处理"},{"time":"2018-05-12 19:30:07","cash_num":"35.00","cash_info":"18838112402","cash_type":"待处理"},{"time":"2018-05-12 19:31:18","cash_num":"1.00","cash_info":"18838112402","cash_type":"待处理"},{"time":"2018-05-14 09:42:23","cash_num":"1.00","cash_info":"18838112402@163com","cash_type":"待处理"}]
     */

    private int status;
    private String msg;
    private int total;
    private String money;
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * time : 2018-05-12 19:29:10
         * cash_num : 1.00
         * cash_info : 18838112402
         * cash_type : 待处理
         */

        private String time;
        private String cash_num;
        private String cash_info;
        private String cash_type;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCash_num() {
            return cash_num;
        }

        public void setCash_num(String cash_num) {
            this.cash_num = cash_num;
        }

        public String getCash_info() {
            return cash_info;
        }

        public void setCash_info(String cash_info) {
            this.cash_info = cash_info;
        }

        public String getCash_type() {
            return cash_type;
        }

        public void setCash_type(String cash_type) {
            this.cash_type = cash_type;
        }
    }
}
