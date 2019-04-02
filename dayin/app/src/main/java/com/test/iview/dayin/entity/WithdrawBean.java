package com.test.iview.dayin.entity;

/**
 * Created by Administrator on 2018\4\28 0028.
 */

public class WithdrawBean {

    /**
     * status : 0
     * msg : 获取成功
     * money : 169245.00
     * utel : 13317258026
     */

    private int status;
    private String msg;
    private String money;
    private String utel;
    private String cashnum;
    private String cashrule;

    public String getCashnum() {
        return cashnum;
    }

    public void setCashnum(String cashnum) {
        this.cashnum = cashnum;
    }

    public String getCashrule() {
        return cashrule;
    }

    public void setCashrule(String cashrule) {
        this.cashrule = cashrule;
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUtel() {
        return utel;
    }

    public void setUtel(String utel) {
        this.utel = utel;
    }
}
