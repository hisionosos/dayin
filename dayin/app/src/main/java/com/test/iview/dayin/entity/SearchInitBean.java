package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\5\11 0011.
 */

public class SearchInitBean {

    /**
     * status : 0
     * msg : 获取成功
     * list : [{"id":"9","name":"水果"},{"id":"10","name":"百货"},{"id":"8","name":"食品"},{"id":"5","name":"母婴"}]
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
         * id : 9
         * name : 水果
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
