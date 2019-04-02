package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\6\12 0012.
 */

public class StoreBean {

    /**
     * status : 0
     * msg : 获取成功
     * total : 191
     * info : {"shop_title":"捡漏网商城","shop_pic":"http://jianlou.91shunshi.com/uploads/avatar/default/shop_logo_2.jpg","share_title":"捡漏网商城-在捡漏网等你出价！","share_dec":"捡漏网是一个由消费者来出价定义价值的新零售电商平台，超值好货等你来出价！","share_pic":"http://jianlou.91shunshi.com/public/wap/images/logo_big.jpg","share_url":"http://www.jianlou.shop/shop_index?shop_id=11","favorite":0}
     * notice : [{"title":"这个商家太懒了，还没有开始吆喝～","link":""}]
     * list : [{"goodid":"22","goodtitle":"补血必备新疆特产若羌红枣酥脆枣160克散装","ordersnum":"211","lowprice":"14","goodcover":"http://jianlou.91shunshi.com/uploads/goods/3f6e1146d8c6c7a4dddda5811698b659/small_big-01.jpg"},{"goodid":"93","goodtitle":"L码条纹男式宽松棉短袖T恤 POLO衫","ordersnum":"234","lowprice":"20","goodcover":"http://jianlou.91shunshi.com/uploads/goods/6398e7800aab93d2cc3bc2bb9f98cb2a/small_tz.jpg"},{"goodid":"187","goodtitle":"莱蔻蜗牛精华眼霜30g 眼袋保湿紧致","ordersnum":"356","lowprice":"12","goodcover":"http://jianlou.91shunshi.com/uploads/goods/397efc77501feae511c513ac9be42cb9/small_big.jpg"},{"goodid":"190","goodtitle":"莱蔻 深层清洁滋润橄榄洗发水温和护理洗发露300ml","ordersnum":"285","lowprice":"12","goodcover":"http://jianlou.91shunshi.com/uploads/goods/2fa627966ea9c681dc871c95982a93be/small_big.jpg"},{"goodid":"191","goodtitle":"莱蔻爽肤水 补水保湿清爽玫瑰水520ml","ordersnum":"278","lowprice":"14","goodcover":"http://jianlou.91shunshi.com/uploads/goods/3d6d1649612c4fe8afaf4d7f55c15987/small_big.jpg"},{"goodid":"154","goodtitle":"车载涡轮迷你电扇 无叶静音超强风力可调速","ordersnum":"410","lowprice":"40","goodcover":"http://jianlou.91shunshi.com/uploads/goods/449b5160ecdc78b346700aecb6264b26/small_big.jpg"},{"goodid":"125","goodtitle":"真皮汽车方向盘套 牛皮把套汽车用品","ordersnum":"171","lowprice":"43","goodcover":"http://jianlou.91shunshi.com/uploads/goods/168015c4187d8dfcb7b063062c175689/small_big.jpg"},{"goodid":"126","goodtitle":"L码男士沙滩休闲短裤 海边度假纯棉五分裤","ordersnum":"383","lowprice":"22","goodcover":"http://jianlou.91shunshi.com/uploads/goods/2b06c1844989c8418a6e6eebcf0751cc/small_tz.jpg"}]
     */

    private int status;
    private String msg;
    private int total;
    private InfoBean info;
    private List<NoticeBean> notice;
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

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<NoticeBean> getNotice() {
        return notice;
    }

    public void setNotice(List<NoticeBean> notice) {
        this.notice = notice;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class InfoBean {
        /**
         * shop_title : 捡漏网商城
         * shop_pic : http://jianlou.91shunshi.com/uploads/avatar/default/shop_logo_2.jpg
         * share_title : 捡漏网商城-在捡漏网等你出价！
         * share_dec : 捡漏网是一个由消费者来出价定义价值的新零售电商平台，超值好货等你来出价！
         * share_pic : http://jianlou.91shunshi.com/public/wap/images/logo_big.jpg
         * share_url : http://www.jianlou.shop/shop_index?shop_id=11
         * favorite : 0
         */

        private String shop_title;
        private String shop_pic;
        private String share_title;
        private String share_dec;
        private String share_pic;
        private String share_url;
        private int favorite;

        public String getShop_title() {
            return shop_title;
        }

        public void setShop_title(String shop_title) {
            this.shop_title = shop_title;
        }

        public String getShop_pic() {
            return shop_pic;
        }

        public void setShop_pic(String shop_pic) {
            this.shop_pic = shop_pic;
        }

        public String getShare_title() {
            return share_title;
        }

        public void setShare_title(String share_title) {
            this.share_title = share_title;
        }

        public String getShare_dec() {
            return share_dec;
        }

        public void setShare_dec(String share_dec) {
            this.share_dec = share_dec;
        }

        public String getShare_pic() {
            return share_pic;
        }

        public void setShare_pic(String share_pic) {
            this.share_pic = share_pic;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public int getFavorite() {
            return favorite;
        }

        public void setFavorite(int favorite) {
            this.favorite = favorite;
        }
    }

    public static class NoticeBean {
        /**
         * title : 这个商家太懒了，还没有开始吆喝～
         * link :
         */

        private String title;
        private String link;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }

    public static class ListBean {
        /**
         * goodid : 22
         * goodtitle : 补血必备新疆特产若羌红枣酥脆枣160克散装
         * ordersnum : 211
         * lowprice : 14
         * goodcover : http://jianlou.91shunshi.com/uploads/goods/3f6e1146d8c6c7a4dddda5811698b659/small_big-01.jpg
         */

        private String goodid;
        private String goodtitle;
        private String ordersnum;
        private String lowprice;
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

        public String getOrdersnum() {
            return ordersnum;
        }

        public void setOrdersnum(String ordersnum) {
            this.ordersnum = ordersnum;
        }

        public String getLowprice() {
            return lowprice;
        }

        public void setLowprice(String lowprice) {
            this.lowprice = lowprice;
        }

        public String getGoodcover() {
            return goodcover;
        }

        public void setGoodcover(String goodcover) {
            this.goodcover = goodcover;
        }
    }
}
