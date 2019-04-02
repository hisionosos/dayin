package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\5\11 0011.
 */

public class SearchBean {


    /**
     * status : 0
     * msg : 获取成功
     * list : []
     * relist : [{"id":"7","title":"订制办公室落地式定时电风扇 家用无叶塔扇","price":"168","voucher":"0","countorders":"59","avgprice":"105","goodcover":"http://api.jianlou.shop/uploads/goods/bd5b57028faf0bc0a0d7b894e0aebd20/small_show_01.jpg"},{"id":"11","title":"时尚韩版潮流斜挎链条迷你女包","price":"35","voucher":"0","countorders":"59","avgprice":"11","goodcover":"http://api.jianlou.shop/uploads/goods/a179544732c48102abe26a3b851bc7f2/small_image-1__1_.jpg"},{"id":"18","title":"韩版时尚夜光户外休闲背包外置USB充电口","price":"88","voucher":"0","countorders":"51","avgprice":"38.57","goodcover":"http://api.jianlou.shop/uploads/goods/e44c883d5ab720e7134cb7353e75bcd0/small_big_image1.jpg"},{"id":"6","title":"港博78pcs磁力片益智儿童玩具 盒装diy磁性积木智力玩具","price":"350","voucher":"0","countorders":"39","avgprice":"150","goodcover":"http://api.jianlou.shop/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/small_show_01__1_.jpg"},{"id":"17","title":"新款潮流商务男士电脑旅行双肩包","price":"159","voucher":"0","countorders":"29","avgprice":"69","goodcover":"http://api.jianlou.shop/uploads/goods/460cac1e6f07e67078efee8ef2570ec2/small_image.jpg"},{"id":"10","title":"女士韩版流苏休闲手提小包包","price":"168","voucher":"0","countorders":"28","avgprice":"23.8","goodcover":"http://api.jianlou.shop/uploads/goods/077a2ba9437f3586a75708ce614758f4/small_image-2.jpg"},{"id":"15","title":"时尚学院风单肩斜挎帆布休闲旅行包","price":"88","voucher":"0","countorders":"27","avgprice":"69","goodcover":"http://api.jianlou.shop/uploads/goods/e86b4921d7bad9523f0eaaa13b3bfa35/small_image__2_.jpg"},{"id":"12","title":"潮流韩版复古零钱斜挎包","price":"29.8","voucher":"0","countorders":"23","avgprice":"16.14","goodcover":"http://api.jianlou.shop/uploads/goods/ab2978d69f6d9d200799352f80a72c86/small_image.jpg"}]
     */

    private int status;
    private String msg;
    private List<?> list;
    private List<RelistBean> relist;

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

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public List<RelistBean> getRelist() {
        return relist;
    }

    public void setRelist(List<RelistBean> relist) {
        this.relist = relist;
    }

    public static class RelistBean {
        /**
         * id : 7
         * title : 订制办公室落地式定时电风扇 家用无叶塔扇
         * price : 168
         * voucher : 0
         * countorders : 59
         * avgprice : 105
         * goodcover : http://api.jianlou.shop/uploads/goods/bd5b57028faf0bc0a0d7b894e0aebd20/small_show_01.jpg
         */

        private String id;
        private String title;

        public String getOrdersnum_txt() {
            return ordersnum_txt;
        }

        public void setOrdersnum_txt(String ordersnum_txt) {
            this.ordersnum_txt = ordersnum_txt;
        }

        private String ordersnum_txt;

        public String getOrdersnum() {
            return ordersnum;
        }

        public void setOrdersnum(String ordersnum) {
            this.ordersnum = ordersnum;
        }

        private String ordersnum;
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
        private String goodcover;

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

        public String getGoodcover() {
            return goodcover;
        }

        public void setGoodcover(String goodcover) {
            this.goodcover = goodcover;
        }
    }
}
