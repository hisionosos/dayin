package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\4\26 0026.
 */

public class HotBean {

    /**
     * status : 0
     * msg : 获取成功
     * total : 14
     * list : [{"goodid":"15","goodtitle":"测试商品22","goodprice":"100.00","goodvoucher":"0","countorders":0,"goodcover":"http://jianlou.91shunshi.com/uploads/goods/84f3f0262d2856622230bc95b0048cf8/big_9cf899c4d6b11a4096ebd13abc55490f.jpg"},{"goodid":"16","goodtitle":"测试商品12","goodprice":"100.00","goodvoucher":"0","countorders":0,"goodcover":"http://jianlou.91shunshi.com/uploads/goods/nopic.jpg"}]
     * cate : [{"id":"0","categoryname":"全部"},{"id":"1","categoryname":"女装"},{"id":"2","categoryname":"鞋包"},{"id":"3","categoryname":"美妆"},{"id":"4","categoryname":"珠宝"},{"id":"5","categoryname":"母婴"},{"id":"6","categoryname":"男装"},{"id":"7","categoryname":"数码"},{"id":"8","categoryname":"食品"},{"id":"9","categoryname":"水果"},{"id":"10","categoryname":"百货"},{"id":"28","categoryname":"测试"}]
     */

    private int status;
    private String msg;
    private int total;
    private List<ListBean> list;
    private List<CateBean> cate;

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

    public List<CateBean> getCate() {
        return cate;
    }

    public void setCate(List<CateBean> cate) {
        this.cate = cate;
    }

    public static class ListBean {
        /**
         * goodid : 15
         * goodtitle : 测试商品22
         * goodprice : 100.00
         * goodvoucher : 0
         * countorders : 0
         * goodcover : http://jianlou.91shunshi.com/uploads/goods/84f3f0262d2856622230bc95b0048cf8/big_9cf899c4d6b11a4096ebd13abc55490f.jpg
         */

        private String goodid;
        private String goodtitle;

        public String getOrdersnum() {
            return ordersnum;
        }

        public void setOrdersnum(String ordersnum) {
            this.ordersnum = ordersnum;
        }

        private String ordersnum;

        public String getAvgprice() {
            return avgprice;
        }

        public void setAvgprice(String avgprice) {
            this.avgprice = avgprice;
        }

        private String avgprice;

        public String getLowprice() {
            return lowprice;
        }

        public void setLowprice(String lowprice) {
            this.lowprice = lowprice;
        }

        private String lowprice;
        private String goodprice;
        private String goodvoucher;
        private int countorders;
        private String goodcover;

        public String getOrdersnum_txt() {
            return ordersnum_txt;
        }

        public void setOrdersnum_txt(String ordersnum_txt) {
            this.ordersnum_txt = ordersnum_txt;
        }

        private String ordersnum_txt;

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

        public String getGoodvoucher() {
            return goodvoucher;
        }

        public void setGoodvoucher(String goodvoucher) {
            this.goodvoucher = goodvoucher;
        }

        public int getCountorders() {
            return countorders;
        }

        public void setCountorders(int countorders) {
            this.countorders = countorders;
        }

        public String getGoodcover() {
            return goodcover;
        }

        public void setGoodcover(String goodcover) {
            this.goodcover = goodcover;
        }
    }

    public static class CateBean {
        /**
         * id : 0
         * categoryname : 全部
         */

        private String id;
        private String categoryname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategoryname() {
            return categoryname;
        }

        public void setCategoryname(String categoryname) {
            this.categoryname = categoryname;
        }
    }
}
