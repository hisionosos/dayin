package com.test.iview.dayin.entity;

/**
 * Created by Administrator on 2018\5\4 0004.
 */

public class JianLouZFBBean {

    /**
     * status : 0
     * order_id : 132
     * order_no : 5566654444
     * body : XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
     */

    private int status;
    private int order_id;
    private String order_no;
    private String body;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
