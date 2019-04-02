package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\4\25 0025.
 */

public class HomeBean {

    /**
     * status : 0
     * msg : 首页获取成功
     * banner : [{"id":1,"url":"device_check","ord":1,"pic":"http://jianlou.cn/public/wap/images/banner.jpg"},{"id":2,"url":"device_check","ord":2,"pic":"http://jianlou.cn/public/wap/images/banner.jpg"},{"id":3,"url":"device_check","ord":3,"pic":"http://jianlou.cn/public/wap/images/banner.jpg"}]
     * cate : [{"id":"1","categoryname":"女装","icon":"ico_a"},{"id":"2","category_name":"鞋包","icon":"ico_b"},{"id":"3","categoryname":"美妆","icon":"ico_c"},{"id":"4","categoryname":"珠宝","icon":"ico_d"},{"id":"5","categoryname":"母婴","icon":"ico_e"},{"id":"6","categoryname":"男装","icon":"ico_f"},{"id":"7","categoryname":"数码","icon":"ico_g"},{"id":"8","categoryname":"食品","icon":"ico_h"},{"id":"9","categoryname":"水果","icon":"ico_i"},{"id":"10","categoryname":"百货","icon":"ico_l"}]
     * list : [{"goodid":"4","goodtitle":"测试一个商品，80自动成交50失败","goodprice":"100.00","countorders":0,"goodcover":"http://jianlou.cn/uploads/goods/nopic.jpg"},{"goodid":"3","goodtitle":"测试的商品3","goodprice":"80.00","countorders":177,"goodcover":"http://jianlou.cn/uploads/goods/b7fa3feed8b04080f05217d4191029cc/big_9778b68232094348e0da4801d00b0ac8.jpg"}]
     */

    private int status;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int total;
    private String msg;
    private List<BannerBean> banner;
    private List<CateBean> cate;
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

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<CateBean> getCate() {
        return cate;
    }

    public void setCate(List<CateBean> cate) {
        this.cate = cate;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class BannerBean {
        /**
         * id : 1
         * url : device_check
         * ord : 1
         * pic : http://jianlou.cn/public/wap/images/banner.jpg
         */

        private int id;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        private String link;
        private int ord;
        private String pic;
        private String to_type;
        private String to_item_id;

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        private String category_name;

        public String getTo_type() {
            return to_type;
        }

        public void setTo_type(String to_type) {
            this.to_type = to_type;
        }

        public String getTo_item_id() {
            return to_item_id;
        }

        public void setTo_item_id(String to_item_id) {
            this.to_item_id = to_item_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public int getOrd() {
            return ord;
        }

        public void setOrd(int ord) {
            this.ord = ord;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }

    public static class CateBean {
        /**
         * id : 1
         * categoryname : 女装
         * icon : ico_a
         * category_name : 鞋包
         */

        private String id;
        private String categoryname;
        private String icon;
        private String category_name;

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }
    }

    public static class ListBean {
        /**
         * goodid : 4
         * goodtitle : 测试一个商品，80自动成交50失败
         * goodprice : 100.00
         * countorders : 0
         * goodcover : http://jianlou.cn/uploads/goods/nopic.jpg
         */

        private String goodid;
        private String goodtitle;
        private String goodprice;

        public String getLowprice() {
            return lowprice;
        }

        public void setLowprice(String lowprice) {
            this.lowprice = lowprice;
        }

        private String lowprice;

        public String getAvgprice() {
            return avgprice;
        }

        public void setAvgprice(String avgprice) {
            this.avgprice = avgprice;
        }

        private String avgprice;
        private int countorders;
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
}
