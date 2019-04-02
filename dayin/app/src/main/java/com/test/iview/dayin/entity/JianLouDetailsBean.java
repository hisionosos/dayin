package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class JianLouDetailsBean {
    /**
     * status : 0
     * msg : 没有数据
     * info : {"goodlink":"http://jianlou.91shunshi.com/index.php/apis/goods/goods_show/6","avgorders":"250","voucherscontent":"","vouchertime":"","shopdress":"广东深圳龙岗区","shoptitle":"官方自营店","allgoods":4,"allorders":259,"shoppic":"http://jianlou.91shunshi.com/uploads/avatar/default/shop_logo.jpg","shopself":1,"goodlat":"22.62737","goodlng":"114.06297","countorders":5,"pic":["http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/show_01.jpg"],"goodtitle":"港博78pcs磁力片益智儿童玩具 盒装diy磁性积木智力玩具","waittime":30,"contentpic":["http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_10.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_11.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_12.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_13.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_14.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_15.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_16.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_17.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_18.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_19.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_20.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_21.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_22.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_23.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_24.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_25.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_26.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_27.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_28.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_29.jpg"],"memo":"","goodvoucher":"0","goodprice":"350.00","sharpic":"http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/small_show_01.jpg"}
     * favorite : 0
     * dress : 0
     * tel : 0
     * quality : [{"title":"全场包邮"}]
     * list : [{"avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","user_name":"13002122023","order_price":"11.00","time":"刚刚"},{"avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","user_name":"13002122023","order_price":"11.00","time":"刚刚"},{"avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","user_name":"13823280279","order_price":"11.00","time":"刚刚"},{"avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","user_name":"18838112402","order_price":"58.79","time":"刚刚"},{"avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","user_name":"18838112402","order_price":"250.00","time":"刚刚"}]
     * num : 5
     */

    private int status;
    private String msg;
    private InfoBean info;
    private int favorite;
    private int dress;
    private int tel;
    private int num;

    public int getIs_lock() {
        return is_lock;
    }

    public void setIs_lock(int is_lock) {
        this.is_lock = is_lock;
    }

    private int is_lock;

    public String getNum_txt() {
        return num_txt;
    }

    public void setNum_txt(String num_txt) {
        this.num_txt = num_txt;
    }

    private String num_txt;
    private List<QualityBean> quality;
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

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getDress() {
        return dress;
    }

    public void setDress(int dress) {
        this.dress = dress;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<QualityBean> getQuality() {
        return quality;
    }

    public void setQuality(List<QualityBean> quality) {
        this.quality = quality;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class InfoBean {
        /**
         * goodlink : http://jianlou.91shunshi.com/index.php/apis/goods/goods_show/6
         * avgorders : 250
         * voucherscontent :
         * vouchertime :
         * shopdress : 广东深圳龙岗区
         * shoptitle : 官方自营店
         * allgoods : 4
         * allorders : 259
         * shoppic : http://jianlou.91shunshi.com/uploads/avatar/default/shop_logo.jpg
         * shopself : 1
         * goodlat : 22.62737
         * goodlng : 114.06297
         * countorders : 5
         * pic : ["http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/show_01.jpg"]
         * goodtitle : 港博78pcs磁力片益智儿童玩具 盒装diy磁性积木智力玩具
         * waittime : 30
         * contentpic : ["http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_10.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_11.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_12.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_13.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_14.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_15.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_16.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_17.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_18.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_19.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_20.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_21.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_22.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_23.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_24.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_25.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_26.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_27.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_28.jpg","http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/big_29.jpg"]
         * memo :
         * goodvoucher : 0
         * goodprice : 350.00
         * sharpic : http://jianlou.91shunshi.com/uploads/goods/1edfb13d6860740926c0fa2a2b61754c/small_show_01.jpg
         */

        private String goodlink;
        private String avgorders;
        private String voucherscontent;
        private String vouchertime;
        private String shopdress;
        private String shoptitle;

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        private String shopid;

        public String getSavemoney() {
            return savemoney;
        }

        public void setSavemoney(String savemoney) {
            this.savemoney = savemoney;
        }

        private String savemoney;
        private int allgoods;
        private int allorders;
        private String shoppic;
        private int shopself;
        private String goodlat;
        private String goodlng;
        private int countorders;
        private String goodtitle;
        private int waittime;
        private String memo;
        private String goodvoucher;
        private String sharetitle;

        public String getSharetitle() {
            return sharetitle;
        }

        public void setSharetitle(String sharetitle) {
            this.sharetitle = sharetitle;
        }

        public String getSharedsc() {
            return sharedsc;
        }

        public void setSharedsc(String sharedsc) {
            this.sharedsc = sharedsc;
        }

        private String sharedsc;
        private String goodprice;
        private String sharpic;
        private String countorders_txt;

        public String getCountorders_txt() {
            return countorders_txt;
        }

        public void setCountorders_txt(String countorders_txt) {
            this.countorders_txt = countorders_txt;
        }

        public String getAllorders_txt() {
            return allorders_txt;
        }

        public void setAllorders_txt(String allorders_txt) {
            this.allorders_txt = allorders_txt;
        }

        public String getAllgoods_txt() {
            return allgoods_txt;
        }

        public void setAllgoods_txt(String allgoods_txt) {
            this.allgoods_txt = allgoods_txt;
        }

        private String allorders_txt;
        private String allgoods_txt;

        public String getLowprice() {
            return lowprice;
        }

        public void setLowprice(String lowprice) {
            this.lowprice = lowprice;
        }

        private String lowprice;
        private List<String> pic;
        private List<String> contentpic;

        public String getGoodlink() {
            return goodlink;
        }

        public void setGoodlink(String goodlink) {
            this.goodlink = goodlink;
        }

        public String getAvgorders() {
            return avgorders;
        }

        public void setAvgorders(String avgorders) {
            this.avgorders = avgorders;
        }

        public String getVoucherscontent() {
            return voucherscontent;
        }

        public void setVoucherscontent(String voucherscontent) {
            this.voucherscontent = voucherscontent;
        }

        public String getVouchertime() {
            return vouchertime;
        }

        public void setVouchertime(String vouchertime) {
            this.vouchertime = vouchertime;
        }

        public String getShopdress() {
            return shopdress;
        }

        public void setShopdress(String shopdress) {
            this.shopdress = shopdress;
        }

        public String getShoptitle() {
            return shoptitle;
        }

        public void setShoptitle(String shoptitle) {
            this.shoptitle = shoptitle;
        }

        public int getAllgoods() {
            return allgoods;
        }

        public void setAllgoods(int allgoods) {
            this.allgoods = allgoods;
        }

        public int getAllorders() {
            return allorders;
        }

        public void setAllorders(int allorders) {
            this.allorders = allorders;
        }

        public String getShoppic() {
            return shoppic;
        }

        public void setShoppic(String shoppic) {
            this.shoppic = shoppic;
        }

        public int getShopself() {
            return shopself;
        }

        public void setShopself(int shopself) {
            this.shopself = shopself;
        }

        public String getGoodlat() {
            return goodlat;
        }

        public void setGoodlat(String goodlat) {
            this.goodlat = goodlat;
        }

        public String getGoodlng() {
            return goodlng;
        }

        public void setGoodlng(String goodlng) {
            this.goodlng = goodlng;
        }

        public int getCountorders() {
            return countorders;
        }

        public void setCountorders(int countorders) {
            this.countorders = countorders;
        }

        public String getGoodtitle() {
            return goodtitle;
        }

        public void setGoodtitle(String goodtitle) {
            this.goodtitle = goodtitle;
        }

        public int getWaittime() {
            return waittime;
        }

        public void setWaittime(int waittime) {
            this.waittime = waittime;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getGoodvoucher() {
            return goodvoucher;
        }

        public void setGoodvoucher(String goodvoucher) {
            this.goodvoucher = goodvoucher;
        }

        public String getGoodprice() {
            return goodprice;
        }

        public void setGoodprice(String goodprice) {
            this.goodprice = goodprice;
        }

        public String getSharpic() {
            return sharpic;
        }

        public void setSharpic(String sharpic) {
            this.sharpic = sharpic;
        }

        public List<String> getPic() {
            return pic;
        }

        public void setPic(List<String> pic) {
            this.pic = pic;
        }

        public List<String> getContentpic() {
            return contentpic;
        }

        public void setContentpic(List<String> contentpic) {
            this.contentpic = contentpic;
        }
    }

    public static class QualityBean {
        /**
         * title : 全场包邮
         */

        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class ListBean {
        /**
         * avatar : http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg
         * user_name : 13002122023
         * order_price : 11.00
         * time : 刚刚
         */

        private String avatar;
        private String user_name;
        private String order_price;
        private String time;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
