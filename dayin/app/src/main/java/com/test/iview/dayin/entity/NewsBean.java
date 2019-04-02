package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\5\5 0005.
 */

public class NewsBean {

    /**
     * status : 0
     * msg : 获取消息成功
     * total : 3
     * list : [{"msgtxt":"很抱歉，捡漏失败，捡漏金额已退款到账户余额","msgtype":1,"time":"2018-04-16 09:13:42"},{"msgtxt":"很抱歉，捡漏失败，捡漏金额已退款到账户余额","msgtype":1,"time":"2018-04-16 09:12:09"},{"msgtxt":"恭喜您捡漏成功","msgtype":0,"time":"2018-04-14 18:27:13"}]
     */

    private int status;
    private String msg;
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
         * msgtxt : 很抱歉，捡漏失败，捡漏金额已退款到账户余额
         * msgtype : 1
         * time : 2018-04-16 09:13:42
         */

        private String msgtxt;
        private int msgtype;
        private String time;

        public String getMsgtxt() {
            return msgtxt;
        }

        public void setMsgtxt(String msgtxt) {
            this.msgtxt = msgtxt;
        }

        public int getMsgtype() {
            return msgtype;
        }

        public void setMsgtype(int msgtype) {
            this.msgtype = msgtype;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
