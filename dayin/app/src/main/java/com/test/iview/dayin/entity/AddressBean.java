package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\5\2 0002.
 */

public class AddressBean  {

    /**
     * status : 0
     * msg : 获取地址成功
     * list : [{"name":"郑女士","tel":"13002122023","id":"17","type":"1","ssq":"广东深圳南山区","dresscity":"西丽街道白芒村南"},{"name":"测试的","tel":"13002122023","id":"26","type":"0","ssq":"广东深圳南山区","dresscity":"走走走走走走啊走"},{"name":"测试添加app的地址","tel":"13002122023","id":"32","type":"0","ssq":"福建南平邵武市","dresscity":"这个是测试APP的对接地址"}]
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
         * name : 郑女士
         * tel : 13002122023
         * id : 17
         * type : 1
         * ssq : 广东深圳南山区
         * dresscity : 西丽街道白芒村南
         */

        private String name;
        private String tel;
        private String id;
        private String type;
        private String ssq;
        private String dresscity;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSsq() {
            return ssq;
        }

        public void setSsq(String ssq) {
            this.ssq = ssq;
        }

        public String getDresscity() {
            return dresscity;
        }

        public void setDresscity(String dresscity) {
            this.dresscity = dresscity;
        }
    }
}
