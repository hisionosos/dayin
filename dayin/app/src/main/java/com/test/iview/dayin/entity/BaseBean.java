package com.test.iview.dayin.entity;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class BaseBean {

    /**
     * status : 0
     * msg : 发送成功
     */

    private int status;
    private int step;
    private String usertoken;
    private String jianlou_price;
    private String msg;
    private int type;
    private String link;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }



    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }




    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getJianlou_price() {
        return jianlou_price;
    }

    public void setJianlou_price(String jianlou_price) {
        this.jianlou_price = jianlou_price;
    }


    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }


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
}
