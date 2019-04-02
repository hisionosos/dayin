package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\5\2 0002.
 */

public class OrderDataBean {


    /**
     * status : 0
     * msg : 获取成功
     * info : {"orderno":"2018060950489857","ordertime":"2018-06-09 11:36:02","price":"20","orderstatus":4,"dressname":"郑焕","dresstel":"13002122023","dressudress":"广东省,深圳市,南山区测试地址东东月以后","goodid":"22","goodtitle":"补血必备新疆特产若羌红枣酥脆枣160克散装","goodmemo":"","orderendtime":"2018-06-09","goodcover":"http://jianlou.91shunshi.com/uploads/goods/3f6e1146d8c6c7a4dddda5811698b659/small_big-01.jpg"}
     * deliver_no : 889937122258663903
     * deliver_name : 圆通速递
     * deliver_list : [{"station":"客户 签收人: 有问题联系业务员2289720 已签收 感谢使用圆通速递，期待再次为您服务","time":"2018-06-04 19:23:56"},{"station":"【福建省厦门市思明区厦禾路公司】 派件人: 吴志清 派件中 派件员电话13459250812","time":"2018-06-04 17:31:41"},{"station":"快件已被FJ深田国际大厦丰巢【自提柜】代收，请及时取件。有问题请联系派件员13950076655","time":"2018-06-04 10:42:04"},{"station":"快件已由深田国际大厦一楼物业服务处旁丰巢柜代收，取件码已发送，请及时取件。","time":"2018-06-04 10:42:00"},{"station":"【福建省厦门市文屏分部公司】 失败签收录入 杨纲文","time":"2018-06-04 08:31:41"},{"station":"【福建省厦门市文屏分部公司】 派件人: 杨纲文 派件中 派件员电话18965175256","time":"2018-06-04 08:31:39"},{"station":"【福建省厦门市文屏分部公司】 失败签收录入 杨纲文","time":"2018-06-03 15:49:50"},{"station":"【福建省厦门市文屏分部公司】 派件人: 杨纲文 派件中 派件员电话18965175256","time":"2018-06-03 15:45:09"},{"station":"【福建省厦门市公司】 已发出 下一站 【福建省厦门市思明区厦禾路公司】","time":"2018-06-03 03:23:35"},{"station":"【泉州转运中心】 已发出 下一站 【福建省厦门市公司】","time":"2018-06-02 21:40:56"},{"station":"【泉州转运中心】 已收入","time":"2018-06-02 21:28:09"},{"station":"【蚌埠转运中心】 已发出 下一站 【泉州转运中心】","time":"2018-06-01 21:22:21"},{"station":"【蚌埠转运中心】 已收入","time":"2018-06-01 21:19:57"},{"station":"【安徽省亳州市公司】 已发出 下一站 【蚌埠转运中心】","time":"2018-06-01 15:41:43"},{"station":"【安徽省亳州市公司】 已打包","time":"2018-06-01 14:47:52"},{"station":"【安徽省亳州市公司】 已收件","time":"2018-06-01 14:47:47"},{"station":"【安徽省亳州市公司】 取件人: 王世界 已收件","time":"2018-06-01 13:20:37"}]
     */

    private int status;
    private String msg;
    private InfoBean info;
    private String deliver_no;
    private String deliver_name;
    private List<DeliverListBean> deliver_list;

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

    public String getDeliver_no() {
        return deliver_no;
    }

    public void setDeliver_no(String deliver_no) {
        this.deliver_no = deliver_no;
    }

    public String getDeliver_name() {
        return deliver_name;
    }

    public void setDeliver_name(String deliver_name) {
        this.deliver_name = deliver_name;
    }

    public List<DeliverListBean> getDeliver_list() {
        return deliver_list;
    }

    public void setDeliver_list(List<DeliverListBean> deliver_list) {
        this.deliver_list = deliver_list;
    }

    public static class InfoBean {
        /**
         * orderno : 2018060950489857
         * ordertime : 2018-06-09 11:36:02
         * price : 20
         * orderstatus : 4
         * dressname : 郑焕
         * dresstel : 13002122023
         * dressudress : 广东省,深圳市,南山区测试地址东东月以后
         * goodid : 22
         * goodtitle : 补血必备新疆特产若羌红枣酥脆枣160克散装
         * goodmemo :
         * orderendtime : 2018-06-09
         * goodcover : http://jianlou.91shunshi.com/uploads/goods/3f6e1146d8c6c7a4dddda5811698b659/small_big-01.jpg
         */

        private String orderno;
        private String ordertime;
        private String price;
        private int orderstatus;
        private String dressname;
        private String dresstel;
        private String dressudress;
        private String goodid;
        private String goodtitle;
        private String goodmemo;
        private String orderendtime;
        private String goodcover;

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public String getOrdertime() {
            return ordertime;
        }

        public void setOrdertime(String ordertime) {
            this.ordertime = ordertime;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(int orderstatus) {
            this.orderstatus = orderstatus;
        }

        public String getDressname() {
            return dressname;
        }

        public void setDressname(String dressname) {
            this.dressname = dressname;
        }

        public String getDresstel() {
            return dresstel;
        }

        public void setDresstel(String dresstel) {
            this.dresstel = dresstel;
        }

        public String getDressudress() {
            return dressudress;
        }

        public void setDressudress(String dressudress) {
            this.dressudress = dressudress;
        }

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

        public String getGoodmemo() {
            return goodmemo;
        }

        public void setGoodmemo(String goodmemo) {
            this.goodmemo = goodmemo;
        }

        public String getOrderendtime() {
            return orderendtime;
        }

        public void setOrderendtime(String orderendtime) {
            this.orderendtime = orderendtime;
        }

        public String getGoodcover() {
            return goodcover;
        }

        public void setGoodcover(String goodcover) {
            this.goodcover = goodcover;
        }
    }

    public static class DeliverListBean {
        /**
         * station : 客户 签收人: 有问题联系业务员2289720 已签收 感谢使用圆通速递，期待再次为您服务
         * time : 2018-06-04 19:23:56
         */

        private String station;
        private String time;

        public String getStation() {
            return station;
        }

        public void setStation(String station) {
            this.station = station;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
