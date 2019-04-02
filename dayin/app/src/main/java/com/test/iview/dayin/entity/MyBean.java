package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class MyBean {


    /**
     * status : 0
     * msg : 获取用户信息成功
     * info : {"id":"1","name":"admin","uname":"管理","avatar":"http://jianlou.91shunshi.com/uploads/avatar/1/01/1_avatar_big.png","tel":"13317258026","credit":"417","money":"84424.04"}
     * free : 0
     * sitetel : 0755-89588060
     * usertags : ["90后","母婴","鞋包","百货","果蔬","珠宝","男装","成功试水","牛刀客"]
     */

    private int status;
    private String msg;
    private InfoBean info;
    private int free;
    private String sitetel;
    private List<String> usertags;

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

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public String getSitetel() {
        return sitetel;
    }

    public void setSitetel(String sitetel) {
        this.sitetel = sitetel;
    }

    public List<String> getUsertags() {
        return usertags;
    }

    public void setUsertags(List<String> usertags) {
        this.usertags = usertags;
    }

    public static class InfoBean {
        /**
         * id : 1
         * name : admin
         * uname : 管理
         * avatar : http://jianlou.91shunshi.com/uploads/avatar/1/01/1_avatar_big.png
         * tel : 13317258026
         * credit : 417
         * money : 84424.04
         */

        private String id;
        private String name;
        private String uname;
        private String avatar;
        private String tel;
        private String credit;
        private String money;

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

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
