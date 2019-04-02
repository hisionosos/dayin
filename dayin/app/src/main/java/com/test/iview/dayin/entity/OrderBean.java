package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\5\2 0002.
 */

public class OrderBean {

    /**
     * status : 0
     * msg : 获取订单成功
     * total : 26
     * list : [{"goodid":"1","orderid":"250","orderno":"2018041449100989","orderdeliver":"1","goodtitle":"唐晶童薇同款重磅真丝衬衫女长袖 加厚大飘带蝴蝶结上衣白蓝OL","goodprice":100,"orderprice":80,"ordertime":"2018-04-14 18:27:13","goodcover":"http://jianlou.91shunshi.com/uploads/goods/9e045ff78c4858d1c7351579635c96b3/small_4d843dca622f237d2797b1dbda9e6516.jpg"},{"goodid":"2","orderid":"199","orderno":"2018041357534849","orderdeliver":"2","goodtitle":"春季2018新款女装超仙气质收腰女神范蕾丝连衣裙子灯笼袖小香风裙","goodprice":"100.00","orderprice":"80.00","ordertime":"2018-04-13 15:33:13","goodcover":"http://jianlou.91shunshi.com/uploads/goods/c5c1b92572f2cf930d12737ab27f8ee7/small_d36b071e900f48582dc71d2ec624eda0.jpg"}]
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
         * goodid : 1
         * orderid : 250
         * orderno : 2018041449100989
         * orderdeliver : 1
         * goodtitle : 唐晶童薇同款重磅真丝衬衫女长袖 加厚大飘带蝴蝶结上衣白蓝OL
         * goodprice : 100
         * orderprice : 80
         * ordertime : 2018-04-14 18:27:13
         * goodcover : http://jianlou.91shunshi.com/uploads/goods/9e045ff78c4858d1c7351579635c96b3/small_4d843dca622f237d2797b1dbda9e6516.jpg
         */

        private String goodid;
        private String orderid;
        private String orderno;
        private int orderdeliver;
        private String goodtitle;
        private String goodprice;
        private String orderprice;
        private String ordertime;
        private String goodcover;

        public String getGoodid() {
            return goodid;
        }

        public void setGoodid(String goodid) {
            this.goodid = goodid;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public int getOrderdeliver() {
            return orderdeliver;
        }

        public void setOrderdeliver(int orderdeliver) {
            this.orderdeliver = orderdeliver;
        }

        public String getGoodtitle() {
            return goodtitle;
        }

        public void setGoodtitle(String goodtitle) {
            this.goodtitle = goodtitle;
        }

        public String getGoodprice() {
            return goodprice;
        }

        public void setGoodprice(String goodprice) {
            this.goodprice = goodprice;
        }

        public String getOrderprice() {
            return orderprice;
        }

        public void setOrderprice(String orderprice) {
            this.orderprice = orderprice;
        }

        public String getOrdertime() {
            return ordertime;
        }

        public void setOrdertime(String ordertime) {
            this.ordertime = ordertime;
        }

        public String getGoodcover() {
            return goodcover;
        }

        public void setGoodcover(String goodcover) {
            this.goodcover = goodcover;
        }
    }
}
