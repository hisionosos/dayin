package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\4\28 0028.
 */

public class CollectBean {


    /**
     * status : 0
     * msg : 获取收藏成功
     * total : 3
     * list : [{"id":473,"gid":25,"title":"小嘛小二郎","price_goods":"全部商品3件","lowprice_nums":"被捡83次","pic":"http://jianlou.91shunshi.com/uploads/shop_auth/company/63ebdb4f4ccb96e6f338d0220850560d.png"},{"id":476,"gid":24,"title":"宇宙超级无敌","price_goods":"全部商品2件","lowprice_nums":"被捡163次","pic":"http://jianlou.91shunshi.com/uploads/shop_auth/personal/fc7d6e37e56984b4999effda3ed0a3f5_icon_pic.jpg"},{"id":477,"gid":26,"title":"测试版企业商家店铺","price_goods":"全部商品3件","lowprice_nums":"被捡15次","pic":"http://jianlou.91shunshi.com/uploads/shop_auth/personal/4fd94838de74943b0ae89a0e8cc96fdc_logo_avatar.jpg"}]
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
         * id : 473
         * gid : 25
         * title : 小嘛小二郎
         * price_goods : 全部商品3件
         * lowprice_nums : 被捡83次
         * pic : http://jianlou.91shunshi.com/uploads/shop_auth/company/63ebdb4f4ccb96e6f338d0220850560d.png
         */

        private int id;
        private String gid;
        private String title;
        private String price_goods;
        private String lowprice_nums;
        private String pic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice_goods() {
            return price_goods;
        }

        public void setPrice_goods(String price_goods) {
            this.price_goods = price_goods;
        }

        public String getLowprice_nums() {
            return lowprice_nums;
        }

        public void setLowprice_nums(String lowprice_nums) {
            this.lowprice_nums = lowprice_nums;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
