package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\6\12 0012.
 */

public class AfterSaleRecordBean {

    /**
     * status : 0
     * msg : 获取成功
     * info : {"good_title":"汉方一族库拉索芦荟胶40g平衡水油收敛毛孔保湿补水","good_id":"202","good_pic":"http://jianlounew.cn/uploads/goods/0bb73356d95052d6b791dd154906fda2/small_show_pic1.jpg","order_price":"13","server_content":"不喜欢","server_type":"1","server_no":"SH2018060752100565","order_no":"2018060156100981","server_type_txt":"退款退货"}
     * list : [{"msginfo":"退款退货","time":"2018-06-07 07:12:36"},{"msginfo":"买家发货中通快递497520194099","time":"1528364596"}]
     */

    private int status;
    private String msg;
    private InfoBean info;
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class InfoBean {
        /**
         * good_title : 汉方一族库拉索芦荟胶40g平衡水油收敛毛孔保湿补水
         * good_id : 202
         * good_pic : http://jianlounew.cn/uploads/goods/0bb73356d95052d6b791dd154906fda2/small_show_pic1.jpg
         * order_price : 13
         * server_content : 不喜欢
         * server_type : 1
         * server_no : SH2018060752100565
         * order_no : 2018060156100981
         * server_type_txt : 退款退货
         */

        private String good_title;
        private String good_id;
        private String good_pic;
        private String order_price;
        private String server_content;
        private String server_type;
        private String server_no;
        private String order_no;
        private String server_type_txt;

        public String getServer_add_time() {
            return server_add_time;
        }

        public void setServer_add_time(String server_add_time) {
            this.server_add_time = server_add_time;
        }

        private String server_add_time;

        public String getGood_title() {
            return good_title;
        }

        public void setGood_title(String good_title) {
            this.good_title = good_title;
        }

        public String getGood_id() {
            return good_id;
        }

        public void setGood_id(String good_id) {
            this.good_id = good_id;
        }

        public String getGood_pic() {
            return good_pic;
        }

        public void setGood_pic(String good_pic) {
            this.good_pic = good_pic;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getServer_content() {
            return server_content;
        }

        public void setServer_content(String server_content) {
            this.server_content = server_content;
        }

        public String getServer_type() {
            return server_type;
        }

        public void setServer_type(String server_type) {
            this.server_type = server_type;
        }

        public String getServer_no() {
            return server_no;
        }

        public void setServer_no(String server_no) {
            this.server_no = server_no;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getServer_type_txt() {
            return server_type_txt;
        }

        public void setServer_type_txt(String server_type_txt) {
            this.server_type_txt = server_type_txt;
        }
    }

    public static class ListBean {
        /**
         * msginfo : 退款退货
         * time : 2018-06-07 07:12:36
         */

        private String msginfo;
        private String time;

        public String getMsginfo() {
            return msginfo;
        }

        public void setMsginfo(String msginfo) {
            this.msginfo = msginfo;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
