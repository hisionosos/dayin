package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class HomeClassificationBean {


    /**
     * status : 0
     * msg : 获取成功
     * total : 10
     * list : [{"title":"2018新款韩版女士流苏复古斜跨包","id":"9","price":"88","voucher":"0","countorders":"12","avgprice":"49","goodcover":"http://api.jianlou.shop/uploads/goods/a7fd438195dde9ce3b27ccb4b1d88ec6/small_image-1.jpg"},{"title":"女士韩版流苏休闲手提小包包","id":"10","price":"46","voucher":"0","countorders":"28","avgprice":"33","goodcover":"http://api.jianlou.shop/uploads/goods/077a2ba9437f3586a75708ce614758f4/small_image-2.jpg"},{"title":"时尚韩版潮流斜挎链条迷你女包","id":"11","price":"35","voucher":"0","countorders":"59","avgprice":"11","goodcover":"http://api.jianlou.shop/uploads/goods/a179544732c48102abe26a3b851bc7f2/small_image-1__1_.jpg"},{"title":"潮流韩版复古零钱斜挎包","id":"12","price":"29.8","voucher":"0","countorders":"23","avgprice":"16.14","goodcover":"http://api.jianlou.shop/uploads/goods/ab2978d69f6d9d200799352f80a72c86/small_image.jpg"},{"title":"2018新款心形链条撞色单肩斜挎包","id":"13","price":"77","voucher":"0","countorders":"23","avgprice":"26","goodcover":"http://api.jianlou.shop/uploads/goods/eac65496d47381934d63d503b7f7d9a2/small_image__3_.jpg"},{"title":"韩版男士户外休闲双肩时尚手提包","id":"14","price":"118","voucher":"0","countorders":"1","avgprice":"100","goodcover":"http://api.jianlou.shop/uploads/goods/16c3b23b7038be9c91f06776830fca80/small_image__4_.jpg"},{"title":"时尚学院风单肩斜挎帆布休闲旅行包","id":"15","price":"88","voucher":"0","countorders":"27","avgprice":"69","goodcover":"http://api.jianlou.shop/uploads/goods/e86b4921d7bad9523f0eaaa13b3bfa35/small_image__2_.jpg"},{"title":"新款帆布精品男士单肩包","id":"16","price":"69","voucher":"0","countorders":"12","avgprice":"44","goodcover":"http://api.jianlou.shop/uploads/goods/3fcb1a397c44a58e140e81f8fa95ea93/small_image__1_.jpg"},{"title":"新款潮流商务男士电脑旅行双肩包","id":"17","price":"159","voucher":"0","countorders":"29","avgprice":"69","goodcover":"http://api.jianlou.shop/uploads/goods/460cac1e6f07e67078efee8ef2570ec2/small_image.jpg"},{"title":"韩版时尚夜光户外休闲背包外置USB充电口","id":"18","price":"88","voucher":"0","countorders":"51","avgprice":"38.57","goodcover":"http://api.jianlou.shop/uploads/goods/e44c883d5ab720e7134cb7353e75bcd0/small_big_image1.jpg"}]
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
         * title : 2018新款韩版女士流苏复古斜跨包
         * id : 9
         * price : 88
         * voucher : 0
         * countorders : 12
         * avgprice : 49
         * goodcover : http://api.jianlou.shop/uploads/goods/a7fd438195dde9ce3b27ccb4b1d88ec6/small_image-1.jpg
         */

        private String title;
        private String id;
        private String price;
        private String voucher;

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
        private String countorders;
        private String avgprice;

        public String getLowprice() {
            return lowprice;
        }

        public void setLowprice(String lowprice) {
            this.lowprice = lowprice;
        }

        private String lowprice;
        private String goodcover;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
