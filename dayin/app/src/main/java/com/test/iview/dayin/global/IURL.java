package com.test.iview.dayin.global;

/**
 * Created by Administrator on 2018\4\25 0025.
 */

public class IURL {
    public static final String URL = "https://api.iviewdisplays.com/rest/v1/user/";           //生产服务器

    public static final String IURL = "http://47.107.119.132/rest/v1/user/";         //测试服务器


    public static final String URL_LOGIN_CODE = IURL + "verificationCode";
    public static final String URL_LOGIN = IURL + "login";






    public static final String WXAppId = "wx16935fb19a1f43cb";
    public static final String WXAppSecret = "907cff00b57a71aad5d1754691aae727";

    public static final String host = "socket.jianlou.shop";                                    //生产环境

    public static final int port = 2347;

    public static final String QQAppId = "101471063";

    public static final String QQAppSecret = "4ae3e0910496b5e3cc237794e7eaceef";

    public static final String Home = URL + "/device_check/get_home"; //首页

    public static final String Nearby = URL + "/near/get_near"; //附近

    public static final String Find = URL + "/finds/get_finds_new"; //发现

    public static final String Hot = URL + "/hots/get_hots"; //热门

    public static final String My = URL + "/member/get_user"; //我的

    public static final String Home_Loop = URL + "/device_check/loop"; //头部滚动

    public static final String Login_YanZhengMa = URL + "/login/ajax_send_sms"; //获取验证码

    public static final String Login = URL + "/login/sms_login_in"; //登录

    public static final String Login_QQ = URL + "/login/qq_login"; //qq登录

    public static final String Login_WX = URL + "/login/wechat_login"; //微信登录

    public static final String Login_WB = URL + "/login/weibo_login"; //微博登录

    public static final String Home_Classification = URL + "/device_check/get_flist"; //首页分类

    public static final String JianLou_Details = URL + "/goods/goods_show"; //捡漏详情

    public static final String Good_Collection = URL + "/collection/goods_collection"; //商品收藏

    public static final String Accounts = URL + "/member/get_balance"; //我的账户

    public static final String Integration = URL + "/member/get_integral"; //我的积分

    public static final String ZhiFuBao = URL + "/alipay/app_recharge_order"; //支付宝支付

    public static final String WeiXin = URL + "/wxorder/app_wx_recharge"; //微信支付

    public static final String Withdraw = URL + "/withdraw/get_info"; //提现信息

    public static final String Withdraw_Pay = URL + "/withdraw/sms_withdraw"; //提现

    public static final String Withdraw_Record = URL + "/withdraw/withdraw_list"; //提现记录

    public static final String Collect = URL + "/collection/get_collection_new"; //收藏

    public static final String Record = URL + "/record/get_record"; //捡漏记录

    public static final String Order = URL + "/orders/get_orders"; //订单

    public static final String Logistics = URL + "/orders/order_logistics"; //物流

    public static final String Order_Data = URL + "/orders/order_show_new"; //订单详情

    public static final String Footprint = URL + "/footpoints/get_foots"; //我的足迹

    public static final String Address = URL + "/dress/get_dress"; //收货地址

    public static final String Address_Set = URL + "/dress/set_dress"; //设置默认地址

    public static final String Address_Add = URL + "/dress/add_dress"; //添加地址

    public static final String Address_Del = URL + "/dress/del_dress"; //删除地址

    public static final String Install = URL + "/member/user_site"; //用户信息

    public static final String Install_UpDate = URL + "/member/updateuser"; //更新用户信息

    public static final String ChuJia = URL + "/order/get_user_news_order"; //出价

    public static final String Pay_QianBao = URL + "/order/create_order"; //钱包支付

    public static final String Pay_WeiXin = URL + "/wxorder/app_wx_pay"; //微信出价

    public static final String Pay_WeiXin_Pay = URL + "/wxorder/wxjianlou"; //捡漏微信支付

    public static final String Pay_ZhiFuBao = URL + "/alipay/app_order"; //支付宝出价

    public static final String Pay_ZhiFuBao_Pay = URL + "/alipay/aliappback"; //捡漏支付宝支付

    public static final String News = URL + "/message/get_msg"; //消息

    public static final String UpData = URL + "/device_check/and_version"; //下载更新

    public static final String BinDingPhone = URL + "/bindmobile/bind_mobile"; //绑定手机号码

    public static final String Search_Init = URL + "/search/index"; //搜索入口

    public static final String Search_ = URL + "/search/find_key"; //搜索

    public static final String JianLou_Price = URL + "/goods/get_goods_jianlou_price"; //智能出价

    public static final String ShouCang_DEL = URL + "/collection/del_collection"; //删除收藏

    public static final String Welcome_start = URL + "/device_check/start"; //启动页

    public static final String Loop = URL + "/goods/goods_loop"; //

    public static final String Homne_Loop = URL + "/device_check/loop_broadcast"; //首页头部滚动

    public static final String Share = URL + "/member/share"; //分享页

    public static final String TiXian_YanZhengMa = URL + "/withdraw/send_sms"; //提现获取验证码

    public static final String AfterSale = URL + "/orders/after_sale"; //售后

    public static final String QueRenShouHuo = URL + "/orders/collect_goods"; //确认收货

    public static final String AfterSaleRecord = URL + "/orders/after_list"; //售后记录

    public static final String Store = URL + "/shop/get_home"; //商家主页

    public static final String Store_ShouCang = URL + "/shop/shop_collection"; //商家收藏

    public static final String ZhuanTi = URL + "/topic/get_topic"; //专题

    public static final String ChaoShi = URL + "/order/get_status_app"; //超时

}
