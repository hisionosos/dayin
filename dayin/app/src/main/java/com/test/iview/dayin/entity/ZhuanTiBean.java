package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\6\14 0014.
 */

public class ZhuanTiBean {

    /**
     * status : 0
     * msg : 获取成功
     * info : {"title":"测试专题","pic":"http://api.jianlou.shop/uploads/ads/1.jpg","sharetitle":"测试专题","sharepic":"http://jianlounew.cn/public/wap/images/logo_big.jpg","sharedesc":"捡漏网是一个由消费者来出价定义价值的新零售电商平台，超值好货等你来出价！","shareurl":"http://www.jianlou.shop"}
     * total : 4
     * list : [{"goodid":"9","goodtitle":"2018棕色复古气质女士约会斜挎包","lowprice":"32","ordersnum":"972","ordersnum_txt":"972人参与","goodcover":"http://jianlounew.cn/uploads/goods/18d9eaa3727e18e1c97ec5ac05b20968/small_big-1.jpg"},{"goodid":"10","goodtitle":"美呆了的女士粉色流苏休闲手提小包包","lowprice":"31","ordersnum":"1733","ordersnum_txt":"1733人参与","goodcover":"http://jianlounew.cn/uploads/goods/077a2ba9437f3586a75708ce614758f4/small_image-2.jpg"}]
     */

    private int status;
    private String msg;
    private InfoBean info;
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

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
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

    public static class InfoBean {
        /**
         * title : 测试专题
         * pic : http://api.jianlou.shop/uploads/ads/1.jpg
         * sharetitle : 测试专题
         * sharepic : http://jianlounew.cn/public/wap/images/logo_big.jpg
         * sharedesc : 捡漏网是一个由消费者来出价定义价值的新零售电商平台，超值好货等你来出价！
         * shareurl : http://www.jianlou.shop
         */

        private String title;
        private String pic;
        private String sharetitle;
        private String sharepic;
        private String sharedesc;
        private String shareurl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getSharetitle() {
            return sharetitle;
        }

        public void setSharetitle(String sharetitle) {
            this.sharetitle = sharetitle;
        }

        public String getSharepic() {
            return sharepic;
        }

        public void setSharepic(String sharepic) {
            this.sharepic = sharepic;
        }

        public String getSharedesc() {
            return sharedesc;
        }

        public void setSharedesc(String sharedesc) {
            this.sharedesc = sharedesc;
        }

        public String getShareurl() {
            return shareurl;
        }

        public void setShareurl(String shareurl) {
            this.shareurl = shareurl;
        }
    }

    public static class ListBean {
        /**
         * goodid : 9
         * goodtitle : 2018棕色复古气质女士约会斜挎包
         * lowprice : 32
         * ordersnum : 972
         * ordersnum_txt : 972人参与
         * goodcover : http://jianlounew.cn/uploads/goods/18d9eaa3727e18e1c97ec5ac05b20968/small_big-1.jpg
         */

        private String goodid;
        private String goodtitle;
        private String lowprice;
        private String ordersnum;
        private String ordersnum_txt;
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

        public String getLowprice() {
            return lowprice;
        }

        public void setLowprice(String lowprice) {
            this.lowprice = lowprice;
        }

        public String getOrdersnum() {
            return ordersnum;
        }

        public void setOrdersnum(String ordersnum) {
            this.ordersnum = ordersnum;
        }

        public String getOrdersnum_txt() {
            return ordersnum_txt;
        }

        public void setOrdersnum_txt(String ordersnum_txt) {
            this.ordersnum_txt = ordersnum_txt;
        }

        public String getGoodcover() {
            return goodcover;
        }

        public void setGoodcover(String goodcover) {
            this.goodcover = goodcover;
        }
    }
}
