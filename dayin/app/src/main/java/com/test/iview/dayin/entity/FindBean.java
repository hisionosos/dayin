package com.test.iview.dayin.entity;

import java.util.List;

/**
 * Created by Administrator on 2018\4\26 0026.
 */

public class FindBean {

    /**
     * status : 0
     * msg : 获取发现成功
     * total : 3
     * cate : [{"id":"0","categoryname":"热门"},{"id":"26","categoryname":"生活"},{"id":"24","categoryname":"时尚"},{"id":"30","categoryname":"科技"},{"id":"25","categoryname":"汽车"},{"id":"22","categoryname":"问答"},{"id":"23","categoryname":"辟谣"}]
     * list : [{"id":"14","title":"女生夏天背这些包，才是真的小仙女","link":"http://h5.91shunshi.com/find_show?article_id=14","god":"1","sharedes":" 又到了酷暑难耐的夏季，每天起床看到窗外的太阳，恨不得背个空调在身上。为了能够凉快点，在衣柜里找寻半天才终于把自己打扮成美美的小仙女，但是问题又来了，穿着精心挑选的衣服配什么样的包才能搭配呢？都说女人的品味从她背的包来体现，所以选包这件事万万不能马虎。","sharetitle":"女生夏天背这些包，才是真的小仙女","catename":"生活","sharepic":"http://jianlou.91shunshi.com/uploads/articles/4d6280b0dcd5f651653fcd160ab631c1/381117a593541d07df1c5482cbd4465f.jpg","pic":["http://jianlou.91shunshi.com/uploads/articles/4d6280b0dcd5f651653fcd160ab631c1/381117a593541d07df1c5482cbd4465f.jpg"]},{"id":"15","title":"想靠练瑜伽减肥？你可能要失望了","link":"http://h5.91shunshi.com/find_show?article_id=15","god":"2","sharedes":"瑜伽主要是指的修身养心方法，包括调身的体位法、调息的呼吸法、调心的冥想法等，以达到身心合一。\r\n但是现在的人对瑜伽似乎存在一些误解，如果去问一个人为什么要上瑜伽课，他的回答可能有以下几种：","sharetitle":"想靠练瑜伽减肥？你可能要失望了","catename":"生活","sharepic":"http://jianlou.91shunshi.com/uploads/articles/8fcfa2cc189b2c666b182cdbf06212aa/1c3e661363592a81cea7e9ef5401bb0e.jpg","pic":["http://jianlou.91shunshi.com/uploads/articles/8fcfa2cc189b2c666b182cdbf06212aa/1c3e661363592a81cea7e9ef5401bb0e.jpg"]},{"id":"16","title":"走路都带风的性感女神凉鞋","link":"http://h5.91shunshi.com/find_show?article_id=16","god":"3","sharedes":"夏季是一个躁动的季节，不仅仅是心变得热燥了，女王们买买买的原始欲望也开始躁动起来。除了美美的裙子和时尚的包包，更需要一双能搭配所有衣服的凉鞋，不需要你再苦恼今天穿的衣服用什么鞋子来配。甚至只要选对了鞋子，衣服也可以随意穿。来看看下面这几双值得入手的凉鞋吧。","sharetitle":"走路都带风的性感女神凉鞋","catename":"生活","sharepic":"http://jianlou.91shunshi.com/uploads/articles/beaf45c0b52caf9ed0deb490c2875a47/0d46f5a0543e285c91d50b7f64ebec1d.jpg","pic":["http://jianlou.91shunshi.com/uploads/articles/beaf45c0b52caf9ed0deb490c2875a47/0d46f5a0543e285c91d50b7f64ebec1d.jpg","http://jianlou.91shunshi.com/uploads/articles/beaf45c0b52caf9ed0deb490c2875a47/dc4f647516c0c94ac276a6b26e2ecf7e.jpg","http://jianlou.91shunshi.com/uploads/articles/beaf45c0b52caf9ed0deb490c2875a47/6eba7da603a8eb8bc23fc9ae69b2ac25.jpg"]}]
     */

    private int status;
    private String msg;
    private int total;
    private List<CateBean> cate;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CateBean> getCate() {
        return cate;
    }

    public void setCate(List<CateBean> cate) {
        this.cate = cate;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class CateBean {
        /**
         * id : 0
         * categoryname : 热门
         */

        private String id;
        private String categoryname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategoryname() {
            return categoryname;
        }

        public void setCategoryname(String categoryname) {
            this.categoryname = categoryname;
        }
    }

    public static class ListBean {
        /**
         * id : 14
         * title : 女生夏天背这些包，才是真的小仙女
         * link : http://h5.91shunshi.com/find_show?article_id=14
         * god : 1
         * sharedes :  又到了酷暑难耐的夏季，每天起床看到窗外的太阳，恨不得背个空调在身上。为了能够凉快点，在衣柜里找寻半天才终于把自己打扮成美美的小仙女，但是问题又来了，穿着精心挑选的衣服配什么样的包才能搭配呢？都说女人的品味从她背的包来体现，所以选包这件事万万不能马虎。
         * sharetitle : 女生夏天背这些包，才是真的小仙女
         * catename : 生活
         * sharepic : http://jianlou.91shunshi.com/uploads/articles/4d6280b0dcd5f651653fcd160ab631c1/381117a593541d07df1c5482cbd4465f.jpg
         * pic : ["http://jianlou.91shunshi.com/uploads/articles/4d6280b0dcd5f651653fcd160ab631c1/381117a593541d07df1c5482cbd4465f.jpg"]
         */

        private String id;
        private String title;
        private String link;
        private String god;
        private String sharedes;
        private String sharetitle;
        private String catename;
        private String sharepic;
        private List<String> pic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getGod() {
            return god;
        }

        public void setGod(String god) {
            this.god = god;
        }

        public String getSharedes() {
            return sharedes;
        }

        public void setSharedes(String sharedes) {
            this.sharedes = sharedes;
        }

        public String getSharetitle() {
            return sharetitle;
        }

        public void setSharetitle(String sharetitle) {
            this.sharetitle = sharetitle;
        }

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
        }

        public String getSharepic() {
            return sharepic;
        }

        public void setSharepic(String sharepic) {
            this.sharepic = sharepic;
        }

        public List<String> getPic() {
            return pic;
        }

        public void setPic(List<String> pic) {
            this.pic = pic;
        }
    }
}
