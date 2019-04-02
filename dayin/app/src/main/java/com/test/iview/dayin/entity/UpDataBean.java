package com.test.iview.dayin.entity;

/**
 * Created by Administrator on 2017/12/13.
 */

public class UpDataBean {


    /**
     * status : 0
     * msg : 更新版本
     * info : {"isupdate":"true","ismust":"false","updateinfo":"版本更新了","downloadurl":""}
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
         * isupdate : true
         * ismust : false
         * updateinfo : 版本更新了
         * downloadurl :
         */

        private String isupdate;
        private String ismust;
        private String updateinfo;
        private String downloadurl;

        public String getIsupdate() {
            return isupdate;
        }

        public void setIsupdate(String isupdate) {
            this.isupdate = isupdate;
        }

        public String getIsmust() {
            return ismust;
        }

        public void setIsmust(String ismust) {
            this.ismust = ismust;
        }

        public String getUpdateinfo() {
            return updateinfo;
        }

        public void setUpdateinfo(String updateinfo) {
            this.updateinfo = updateinfo;
        }

        public String getDownloadurl() {
            return downloadurl;
        }

        public void setDownloadurl(String downloadurl) {
            this.downloadurl = downloadurl;
        }
    }
}
