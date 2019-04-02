package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\5\2 0002.
 */

public class FootprintBean {


    /**
     * status : 0
     * msg : 获取足迹成功
     * total : 14
     * list : [{"id":"14","title":"韩版男士户外休闲双肩时尚手提包","memo":"","price":"118","countorders":"1","type":"0","avgprice":"100","pic":"http://api.jianlou.shop/uploads/goods/16c3b23b7038be9c91f06776830fca80/small_image__4_.jpg"},{"id":"15","title":"时尚学院风单肩斜挎帆布休闲旅行包","memo":"","price":"88","countorders":"27","type":"0","avgprice":"69","pic":"http://api.jianlou.shop/uploads/goods/e86b4921d7bad9523f0eaaa13b3bfa35/small_image__2_.jpg"},{"id":"16","title":"新款帆布精品男士单肩包","memo":"","price":"69","countorders":"12","type":"0","avgprice":"44","pic":"http://api.jianlou.shop/uploads/goods/3fcb1a397c44a58e140e81f8fa95ea93/small_image__1_.jpg"},{"id":"17","title":"新款潮流商务男士电脑旅行双肩包","memo":"","price":"159","countorders":"29","type":"0","avgprice":"69","pic":"http://api.jianlou.shop/uploads/goods/460cac1e6f07e67078efee8ef2570ec2/small_image.jpg"},{"id":"11","title":"时尚韩版潮流斜挎链条迷你女包","memo":"","price":"35","countorders":"59","type":"0","avgprice":"11","pic":"http://api.jianlou.shop/uploads/goods/a179544732c48102abe26a3b851bc7f2/small_image-1__1_.jpg"},{"id":"6","title":"港博78pcs磁力片益智儿童玩具 盒装diy磁性积木智力玩具","memo":"","price":"350","countorders":"39","type":"0","avgprice":"150","pic":"http://api.jianlou.shop/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/small_show_01__1_.jpg"},{"id":"5","title":"USB迷你小风扇 办公室桌面电扇 宿舍台扇 床上小电风扇","memo":"","price":"39","countorders":"7","type":"0","avgprice":"19.5","pic":"http://api.jianlou.shop/uploads/goods/97e0a586485a06f00cb318b212a476ba/small_show_01__2_.jpg"},{"id":"9","title":"2018新款韩版女士流苏复古斜跨包","memo":"","price":"88","countorders":"12","type":"0","avgprice":"49","pic":"http://api.jianlou.shop/uploads/goods/a7fd438195dde9ce3b27ccb4b1d88ec6/small_image-1.jpg"},{"id":"13","title":"2018新款心形链条撞色单肩斜挎包","memo":"","price":"77","countorders":"23","type":"0","avgprice":"26","pic":"http://api.jianlou.shop/uploads/goods/eac65496d47381934d63d503b7f7d9a2/small_image__3_.jpg"},{"id":"12","title":"潮流韩版复古零钱斜挎包","memo":"","price":"29.8","countorders":"23","type":"0","avgprice":"16.14","pic":"http://api.jianlou.shop/uploads/goods/ab2978d69f6d9d200799352f80a72c86/small_image.jpg"}]
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
         * id : 14
         * title : 韩版男士户外休闲双肩时尚手提包
         * memo :
         * price : 118
         * countorders : 1
         * type : 0
         * avgprice : 100
         * pic : http://api.jianlou.shop/uploads/goods/16c3b23b7038be9c91f06776830fca80/small_image__4_.jpg
         */

        private String id;
        private String title;
        private String memo;
        private String price;
        private String countorders;
        private String type;
        private String avgprice;

        public String getLowprice() {
            return lowprice;
        }

        public void setLowprice(String lowprice) {
            this.lowprice = lowprice;
        }

        private String lowprice;
        private String pic;

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

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCountorders() {
            return countorders;
        }

        public void setCountorders(String countorders) {
            this.countorders = countorders;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAvgprice() {
            return avgprice;
        }

        public void setAvgprice(String avgprice) {
            this.avgprice = avgprice;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
