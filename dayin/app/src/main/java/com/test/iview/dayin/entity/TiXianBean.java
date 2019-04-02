package com.test.iview.dayin.entity;

/**
 * Created by Administrator on 2018\4\28 0028.
 */

public class TiXianBean {

    /**
     * status : 0
     * step : 1
     * msg : 申请成功，等待商家处理
     */

    private int status;
    private int step;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
