package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\5\2 0002.
 */

public class LogisticsBean {

    /**
     * status : 0
     * msg : 获取物流成功
     * company : 中通快递
     * number : 486133654088
     * info : [{"station":"【深圳市】  快件已在 【龙岗坂田】 签收,签收人: 拍照签收, 感谢使用中通快递,期待再次为您服务!","time":"2018-04-01 18:38:23"},{"station":"【深圳市】  快件已到达 【龙岗坂田】（0755-89605306、0755-89605316、0755-89605326）,业务员 林伟强（15338825871） 正在第2次派件, 请保持电话畅通,并耐心等待","time":"2018-04-01 14:26:28"},{"station":"【深圳市】  快件离开 【深圳中心】 发往 【龙岗坂田】","time":"2018-04-01 09:21:05"},{"station":"【深圳市】  快件到达 【深圳中心】","time":"2018-04-01 08:14:56"},{"station":"【揭阳市】  快件离开 【潮汕中心】 发往 【深圳中心】","time":"2018-04-01 00:40:22"},{"station":"【揭阳市】  快件到达 【潮汕中心】","time":"2018-04-01 00:38:52"},{"station":"【汕头市】  快件离开 【潮南】 发往 【深圳中心】","time":"2018-03-31 21:31:38"},{"station":"【汕头市】  【潮南】（0754-85793333） 的 张志伟 （13192195772） 已揽收","time":"2018-03-31 21:21:20"}]
     */

    private int status;
    private String msg;
    private String company;
    private String number;
    private List<InfoBean> info;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * station : 【深圳市】  快件已在 【龙岗坂田】 签收,签收人: 拍照签收, 感谢使用中通快递,期待再次为您服务!
         * time : 2018-04-01 18:38:23
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
