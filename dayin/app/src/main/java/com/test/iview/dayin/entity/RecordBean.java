package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\5\2 0002.
 */

public class RecordBean {

    /**
     * status : 0
     * msg : 获取订单成功
     * total : 18
     * list : [{"goodid":"2","goodtitle":"春季2018新款女装超仙气质收腰女神范蕾丝连衣裙子灯笼袖小香风裙","goodprice":"100.00","orderprice":"20.00","orderstatus":"1","ordertime":"2018-04-16 09:13:34","goodcover":"http://jianlou.91shunshi.com/uploads/goods/c5c1b92572f2cf930d12737ab27f8ee7/small_d36b071e900f48582dc71d2ec624eda0.jpg"},{"goodid":"2","goodtitle":"春季2018新款女装超仙气质收腰女神范蕾丝连衣裙子灯笼袖小香风裙","goodprice":"100.00","orderprice":"10.00","orderstatus":"1","ordertime":"2018-04-16 09:11:56","goodcover":"http://jianlou.91shunshi.com/uploads/goods/c5c1b92572f2cf930d12737ab27f8ee7/small_d36b071e900f48582dc71d2ec624eda0.jpg"}]
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
         * goodid : 2
         * goodtitle : 春季2018新款女装超仙气质收腰女神范蕾丝连衣裙子灯笼袖小香风裙
         * goodprice : 100.00
         * orderprice : 20.00
         * orderstatus : 1
         * ordertime : 2018-04-16 09:13:34
         * goodcover : http://jianlou.91shunshi.com/uploads/goods/c5c1b92572f2cf930d12737ab27f8ee7/small_d36b071e900f48582dc71d2ec624eda0.jpg
         */

        private String goodid;
        private String goodtitle;
        private String goodprice;
        private String orderprice;
        private String orderstatus;
        private String ordertime;
        private String goodcover;

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

        public String getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(String orderstatus) {
            this.orderstatus = orderstatus;
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
