package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\5\12 0012.
 */

public class CeShiBean {

    /**
     * status : 0
     * msg : 获取成功
     * info : {"orderno":"2018051252995554","ordertime":"1526118276","price":"77.88","orderstatus":"1","dressname":"zouwang","dresstel":"13823280279","dressudress":"dddd","goodid":"7","goodtitle":"订制办公室落地式定时电风扇 家用无叶塔扇","goodmemo":"","goodcover":"http://jianlou.91shunshi.com/uploads/goods/bd5b57028faf0bc0a0d7b894e0aebd20/small_show_01.jpg"}
     * list : []
     */

    private int status;
    private String msg;
    private InfoBean info;
    private List<?> list;

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

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public static class InfoBean {
        /**
         * orderno : 2018051252995554
         * ordertime : 1526118276
         * price : 77.88
         * orderstatus : 1
         * dressname : zouwang
         * dresstel : 13823280279
         * dressudress : dddd
         * goodid : 7
         * goodtitle : 订制办公室落地式定时电风扇 家用无叶塔扇
         * goodmemo :
         * goodcover : http://jianlou.91shunshi.com/uploads/goods/bd5b57028faf0bc0a0d7b894e0aebd20/small_show_01.jpg
         */

        private String orderno;
        private String ordertime;
        private String price;
        private String orderstatus;
        private String dressname;
        private String dresstel;
        private String dressudress;
        private String goodid;
        private String goodtitle;
        private String goodmemo;
        private String goodcover;

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public String getOrdertime() {
            return ordertime;
        }

        public void setOrdertime(String ordertime) {
            this.ordertime = ordertime;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(String orderstatus) {
            this.orderstatus = orderstatus;
        }

        public String getDressname() {
            return dressname;
        }

        public void setDressname(String dressname) {
            this.dressname = dressname;
        }

        public String getDresstel() {
            return dresstel;
        }

        public void setDresstel(String dresstel) {
            this.dresstel = dresstel;
        }

        public String getDressudress() {
            return dressudress;
        }

        public void setDressudress(String dressudress) {
            this.dressudress = dressudress;
        }

        public String getGoodid() {
            return goodid;
        }

        public void setGoodid(String goodid) {
            this.goodid = goodid;
        }

        public String getGoodtitle() {
            return goodtitle;
        }

        public void setGoodtitle(String goodtitle) {
            this.goodtitle = goodtitle;
        }

        public String getGoodmemo() {
            return goodmemo;
        }

        public void setGoodmemo(String goodmemo) {
            this.goodmemo = goodmemo;
        }

        public String getGoodcover() {
            return goodcover;
        }

        public void setGoodcover(String goodcover) {
            this.goodcover = goodcover;
        }
    }
}
