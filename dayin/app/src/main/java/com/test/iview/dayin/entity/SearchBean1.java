package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\5\18 0018.
 */

public class SearchBean1 {


    /**
     * status : 0
     * msg : 商品列表
     * total : 3
     * key : 儿童风扇办公
     * list : [{"id":"7","title":"订制办公室落地式定时电风扇 家用无叶塔扇","price":"168","voucher":"0","countorders":"59","avgprice":"105","cover":"http://api.jianlou.shop/uploads/goods/bd5b57028faf0bc0a0d7b894e0aebd20/big_home_big.jpg","key":["风扇","办公"]},{"id":"6","title":"港博78pcs磁力片益智儿童玩具 盒装diy磁性积木智力玩具","price":"350","voucher":"0","countorders":"39","avgprice":"150","cover":"http://api.jianlou.shop/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_home_big.jpg","key":["儿童"]},{"id":"5","title":"USB迷你小风扇 办公室桌面电扇 宿舍台扇 床上小电风扇","price":"39","voucher":"0","countorders":"7","avgprice":"19.5","cover":"http://api.jianlou.shop/uploads/goods/97e0a586485a06f00cb318b212a476ba/big_home_big.jpg","key":["风扇","办公"]}]
     */

    private int status;
    private String msg;
    private int total;
    private String key;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 7
         * title : 订制办公室落地式定时电风扇 家用无叶塔扇
         * price : 168
         * voucher : 0
         * countorders : 59
         * avgprice : 105
         * cover : http://api.jianlou.shop/uploads/goods/bd5b57028faf0bc0a0d7b894e0aebd20/big_home_big.jpg
         * key : ["风扇","办公"]
         */

        private String id;
        private String title;
        private String price;
        private String voucher;
        private String countorders;

        public String getLowprice() {
            return lowprice;
        }

        public void setLowprice(String lowprice) {
            this.lowprice = lowprice;
        }

        private String lowprice;
        private String avgprice;
        private String cover;
        private List<String> key;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getVoucher() {
            return voucher;
        }

        public void setVoucher(String voucher) {
            this.voucher = voucher;
        }

        public String getCountorders() {
            return countorders;
        }

        public void setCountorders(String countorders) {
            this.countorders = countorders;
        }

        public String getAvgprice() {
            return avgprice;
        }

        public void setAvgprice(String avgprice) {
            this.avgprice = avgprice;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public List<String> getKey() {
            return key;
        }

        public void setKey(List<String> key) {
            this.key = key;
        }
    }
}
