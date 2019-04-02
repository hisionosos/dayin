package com.test.iview.dayin.entity;

/**
 * Created by Administrator on 2018\5\3 0003.
 */

public class InstallBean {

    /**
     * status : 0
     * msg : 获取用户信息成功
     * info : {"uname":"幻听。","tel":null,"birth":null,"dress":"湖南长沙芙蓉区123栋","avatar":"http://thirdqq.qlogo.cn/qqapp/101465173/CE767B739C499D33ACC901560443BBAB/40"}
     */

    private int status;
    private String msg;
    private InfoBean info;

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

    public static class InfoBean {
        /**
         * uname : 幻听。
         * tel : null
         * birth : null
         * dress : 湖南长沙芙蓉区123栋
         * avatar : http://thirdqq.qlogo.cn/qqapp/101465173/CE767B739C499D33ACC901560443BBAB/40
         */

        private String uname;
        private String tel;
        private Object birth;
        private String dress;
        private String avatar;

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public Object getBirth() {
            return birth;
        }

        public void setBirth(Object birth) {
            this.birth = birth;
        }

        public String getDress() {
            return dress;
        }

        public void setDress(String dress) {
            this.dress = dress;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
