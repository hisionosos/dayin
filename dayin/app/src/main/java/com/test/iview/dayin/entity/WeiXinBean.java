package com.test.iview.dayin.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018\4\28 0028.
 */

public class WeiXinBean {

    /**
     * appid : wx70de16eca0745c95
     * partnerid : 1501923991
     * prepayid : wx25094450816099ae58376de41073807363
     * noncestr : 5adfdd92d631e
     * timestamp : 1524620690
     * package : Sign=WXPay
     * sign : 4E005A701143C2071DCEBD1CEF235979
     */

    private String appid;
    private String partnerid;
    private String prepayid;
    private String noncestr;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;
    private int timestamp;
    @SerializedName("package")
    private String packageX;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
