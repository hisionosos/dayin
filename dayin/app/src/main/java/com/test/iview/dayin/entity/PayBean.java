package com.test.iview.dayin.entity;

/**
 * Created by Administrator on 2018\5\3 0003.
 */

public class PayBean {

    /**
     * status : 0
     * msg : 捡漏失败
     * list : {"type":1,"wait_time":5,"order_id":311}
     */

    private int status;
    private String msg;
    private ListBean list;

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

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * type : 1
         * wait_time : 5
         * order_id : 311
         */

        private int type;
        private int wait_time;
        private int order_id;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getWait_time() {
            return wait_time;
        }

        public void setWait_time(int wait_time) {
            this.wait_time = wait_time;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }
    }
}
