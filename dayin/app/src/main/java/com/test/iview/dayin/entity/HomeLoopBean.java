package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class HomeLoopBean {

    /**
     * status : 0
     * msg : 获取消息成功
     * list : [{"avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","user_name":"1371","time":"刚刚","content":"捡漏成功"},{"avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","user_name":"1371","time":"刚刚","content":"捡漏成功"},{"avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","user_name":"","time":"刚刚","content":"捡漏成功"},{"avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","user_name":"1316","time":"刚刚","content":"捡漏成功"},{"avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","user_name":"1371","time":"刚刚","content":"捡漏成功"},{"avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","user_name":"1371","time":"刚刚","content":"捡漏成功"},{"user_name":"ship","avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","time":"刚刚","content":"发布了商品"},{"user_name":"baih","avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","time":"刚刚","content":"发布了商品"},{"avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","user_name":"1371","time":"3 天前","content":"捡漏成功"},{"user_name":"shum","avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","time":"刚刚","content":"发布了商品"},{"avatar":"http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg","user_name":"","time":"刚刚","content":"捡漏成功"}]
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
         * avatar : http://jianlou.91shunshi.com/uploads/avatar/default/avatar_small.jpg
         * user_name : 1371
         * time : 刚刚
         * content : 捡漏成功
         */

        private String avatar;
        private String user_name;
        private String time;
        private String content;

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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
