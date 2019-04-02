package com.test.iview.dayin.entity;

/**
 * Created by Administrator on 2018\5\23 0023.
 */

public class ShareBean {

    /**
     * status : 0
     * msg : 获取成功
     * link : http://jianlounew.cn/uploads/public/share.jpg
     * share : http://www.jianlou.shop/user_invite_friends
     */

    private int status;
    private String msg;
    private String link;
    private String share;
    private String shartitle;

    public String getSharpic() {
        return sharpic;
    }

    public void setSharpic(String sharpic) {
        this.sharpic = sharpic;
    }

    private String sharpic;

    public String getShartitle() {
        return shartitle;
    }

    public void setShartitle(String shartitle) {
        this.shartitle = shartitle;
    }

    public String getShardesc() {
        return shardesc;
    }

    public void setShardesc(String shardesc) {
        this.shardesc = shardesc;
    }

    private String shardesc;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }
}
