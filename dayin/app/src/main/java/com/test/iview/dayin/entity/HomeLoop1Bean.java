package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\6\12 0012.
 */

public class HomeLoop1Bean {

    /**
     * status : 0
     * msg : 获取成功
     * list : [{"to_type":"category","to_item_id":"1","avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","content":"投放附近页面，点击去女装分类","shop_id":"0","category_name":"女装"},{"to_type":"goods","to_item_id":"208","avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","content":"投放全站广播，点击去BC霜商品详情页","shop_id":"0"}]
     */

    private int status;
    private String msg;
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * to_type : category
         * to_item_id : 1
         * avatar : http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg
         * content : 投放附近页面，点击去女装分类
         * shop_id : 0
         * category_name : 女装
         */

        private String to_type;
        private String to_item_id;
        private String avatar;
        private String content;
        private String shop_id;
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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }
    }
}
