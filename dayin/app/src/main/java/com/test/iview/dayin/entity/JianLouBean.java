package com.test.iview.dayin.entity;

/**
 * Created by Administrator on 2018\5\5 0005.
 */

public class JianLouBean {

    /**
     * status : 0
     * msg : 商家确认捡漏成功
     * list : {"type":0,"order_id":"182"}
     * user_key : 1b9655edf317200c680c206d2aec6c45
     */

    private int status;
    private String msg;
    private ListBean list;
    private String user_key;

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

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }

    public static class ListBean {
        /**
         * type : 0
         * order_id : 182
         */

        private int type;
        private String order_id;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
    }
}
