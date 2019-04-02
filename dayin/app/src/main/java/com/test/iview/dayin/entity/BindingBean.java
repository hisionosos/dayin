package com.test.iview.dayin.entity;

/**
 * Created by Administrator on 2018\5\7 0007.
 */

public class BindingBean {

    /**
     * status : 0
     * step : 1
     * msg : 绑定成功
     */

    private int status;
    private int step;
    private String msg;

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    private String user_token;

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
