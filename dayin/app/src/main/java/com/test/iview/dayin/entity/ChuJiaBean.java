package com.test.iview.dayin.entity;

/**
 * Created by Administrator on 2018\5\3 0003.
 */

public class ChuJiaBean {

    /**
     * status : 0
     * msg : 请求成功
     * money : 83624.04
     * can : 0
     * savemoney : 30
     */
    private int status;
    private String msg;
    private String money;
    private int can;
    private String savemoney;

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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getCan() {
        return can;
    }

    public void setCan(int can) {
        this.can = can;
    }

    public String getSavemoney() {
        return savemoney;
    }

    public void setSavemoney(String savemoney) {
        this.savemoney = savemoney;
    }
}
