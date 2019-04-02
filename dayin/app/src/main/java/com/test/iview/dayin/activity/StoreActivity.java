package com.test.iview.dayin.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gcssloop.widget.RCRelativeLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.BaseBean;
import com.test.iview.dayin.entity.StoreBean;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.DiaLog;
import com.test.iview.dayin.util.GlideImage;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.util.QqShare;
import com.test.iview.dayin.utils.SettingUtils;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.VpSwipeRefreshLayout;
import com.test.iview.dayin.view.XRecyclerView;
import com.test.iview.dayin.view.XuanFuScrollview;
import com.test.iview.dayin.wbapi.WBShareActivity;
import com.test.iview.dayin.wxapi.WeiXinUtil;
import com.xiaosu.view.text.DataSetAdapter;
import com.xiaosu.view.text.VerticalRollingTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\6\12 0012.
 */

public class StoreActivity extends BaseActivity implements XuanFuScrollview.OnScrollListener, XuanFuScrollview.ScrollViewListener {
    @BindView(R.id.store_pic)
    ImageView storePic;
    @BindView(R.id.store_pic1)
    RCRelativeLayout storePic1;
    @BindView(R.id.store_rl)
    RelativeLayout storeRl;
    @BindView(R.id.store_xian_rl)
    RelativeLayout store_xian_rl;
    @BindView(R.id.store_ying_rl)
    RelativeLayout store_ying_rl;
    @BindView(R.id.store_name)
    TextView storeName;
    @BindView(R.id.store_rl_title111)
    RelativeLayout storeRlTitle;
    @BindView(R.id.store_vsrl)
    VpSwipeRefreshLayout store_vsrl;
    @BindView(R.id.verticalRollingView)
    VerticalRollingTextView verticalRollingView;
    @BindView(R.id.store_ll_title)
    LinearLayout storeLlTitle;
    @BindView(R.id.store_ll)
    LinearLayout store_ll;
    @BindView(R.id.store_quanbu)
    TextView storeQuanbu;
    @BindView(R.id.store_xinpin)
    TextView storeXinpin;
    @BindView(R.id.store_remen)
    TextView storeRemen;
    @BindView(R.id.store_money_tv)
    TextView storeMoneyTv;
    @BindView(R.id.store_money_iv)
    ImageView storeMoneyIv;
    @BindView(R.id.store_money)
    LinearLayout storeMoney;
    @BindView(R.id.act_home_classification_xrv)
    XRecyclerView actHomeClassificationXrv;
    @BindView(R.id.no)
    LinearLayout no;
    @BindView(R.id.store_fanhui)
    ImageView storeFanhui;
    @BindView(R.id.store_shoucang)
    ImageView storeShoucang;
    @BindView(R.id.store_share)
    ImageView storeShare;
    @BindView(R.id.store_title)
    LinearLayout storeTitle;
    @BindView(R.id.store_ying_quanbu)
    TextView storeYingQuanbu;
    @BindView(R.id.store_ying_xinpin)
    TextView storeYingXinpin;@BindView(R.id.store_dixian)
    TextView store_dixian;
    @BindView(R.id.store_ying_remen)
    TextView storeYingRemen;
    @BindView(R.id.store_ying_money_tv)
    TextView storeYingMoneyTv;
    @BindView(R.id.store_ying_money_iv)
    ImageView storeYingMoneyIv;
    @BindView(R.id.store_iv)
    ImageView store_iv;
    @BindView(R.id.store_ying_money)
    LinearLayout storeYingMoney;


    @BindView(R.id.store_xfsv)
    XuanFuScrollview store_xfsv;
    private int type = 1;
    private int page = 1;
    private StoreBean bean;
    private List<TextView> list;
    private List<TextView> list1;
    private boolean isOne = true;
    private int width;
    private MyAdapter adapter;
    private boolean isLoging = true;
    private boolean isShouCang = false;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        store_xfsv.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                store_vsrl.setEnabled(store_xfsv.getScrollY() == 0);
            }
        });
        store_vsrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isShuaXin = true;
                page = 1;
                store_vsrl.setRefreshing(true);
                requset();
            }
        });
        store_xfsv.setOnScrollListener(this);
        store_xfsv.setScrollViewListener(this);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;
        list = new ArrayList<>();
        list.add(storeQuanbu);
        list.add(storeXinpin);
        list.add(storeRemen);
        list.add(storeMoneyTv);
        list1 = new ArrayList<>();
        list1.add(storeYingQuanbu);
        list1.add(storeYingXinpin);
        list1.add(storeYingRemen);
        list1.add(storeYingMoneyTv);
        requset();
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_store;
    }

    private boolean isShuaXin = false;


    private void requset() {
        if (isShuaXin) {
            store_vsrl.setRefreshing(true);
            isShuaXin = false;
        }

        OkGo.post(IURL.Store)
                .params("shop_id", getIntent().getStringExtra("StoreActivity_ID"))
                .params("user_id", MyApplication.userToken)
                .params("type", type)
                .params("page", page)
                .params("num", 20)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, StoreBean.class);
                        if (bean != null) {
                            if (bean.getStatus() == 0) {
                                if (bean.getTotal() == 0) {
                                    no.setVisibility(View.VISIBLE);
                                    actHomeClassificationXrv.setVisibility(View.GONE);
                                } else {
                                    no.setVisibility(View.GONE);
                                    actHomeClassificationXrv.setVisibility(View.VISIBLE);
                                }
                                showView();
                            } else {
                                ToastUtils.showToast(bean.getMsg());
                            }
                        } else {
                            ToastUtils.showToast("");
                        }

                        store_vsrl.setRefreshing(false);
                    }
                });
    }

    private void showView() {
        if (isOne) {
            isOne = false;
            if (bean.getInfo().getFavorite() == 1) {
                isShouCang = true;
                storeShoucang.setImageResource(R.drawable.xiangqing_xing);
            } else {
                storeShoucang.setImageResource(R.drawable.xiangqing_xingno);
                isShouCang = false;
            }
            storeName.setText(bean.getInfo().getShop_title());
            GlideImage.loaderImage(bean.getInfo().getShop_pic(), storePic);
            verticalRollingView.setDataSetAdapter(new DataSetAdapter<StoreBean.NoticeBean>(bean.getNotice()) {

                @Override
                protected String text(StoreBean.NoticeBean s) {
                    return s.getTitle();
                }
            });
            verticalRollingView.run();
            verticalRollingView.setOnItemClickListener(new VerticalRollingTextView.OnItemClickListener() {
                @Override
                public void onItemClick(VerticalRollingTextView view, int index) {

                }
            });
        }
        if (bean.getTotal() > 4) {
            if (bean.getTotal() != 0 && bean.getTotal() < page * 20) {
                store_dixian.setVisibility(View.VISIBLE);
            } else {
                store_dixian.setVisibility(View.GONE);
            }
        }else{
            store_dixian.setVisibility(View.GONE);
        }
        store_xfsv.setOnTouchListener(new View.OnTouchListener() {
            private int lastY1 = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    lastY1 = store_xfsv.getScrollY();
//                    int height = home_ll.getHeight();
//                    int i = homeSv.getHeight() * 2;
                    if (lastY1 > (store_ll.getHeight() - store_xfsv.getHeight() * 2)) {
                        if (isLoging) {
                            if (bean.getTotal() >= page * 20) {
                                page++;
                                isjiazai = false;
                                requset();
                            }
                            isLoging = false;
                        }
                    }
                }
                return false;
            }
        });

        fenYe();
    }
    boolean is100 = false;
    private void fenYe() {
        if (page == 1|| adapter.getData().size() > 100) {
            if (adapter != null) {
                if (adapter.getData().size() > 100) {
                    is100 = true;
                }
            }
            adapter = new MyAdapter(R.layout.item_nearby, bean.getList());
            actHomeClassificationXrv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(), 2));
            actHomeClassificationXrv.setAdapter(adapter);
            if (is100) {
                isjiazai = true;
                scrollTo();
                isjiazai = false;
                is100 = false;
            }
        } else {
            adapter.addData(bean.getList());
        }
        if (page != 1) {
            adapter.loadMoreComplete();
        }
        isLoging = true;
        scrollTo();
        isjiazai = true;
    }

    private int y = 0;
    private boolean isjiazai = false;

    private void scrollTo() {
        if (isjiazai) {
            isjiazai = false;
            if (y >= storeRlTitle.getHeight() + storeLlTitle.getHeight() + store_iv.getHeight()) {
                store_xfsv.smoothScrollTo(0, storeRlTitle.getHeight() + storeLlTitle.getHeight() + store_iv.getHeight() - storeTitle.getHeight());
                for (int i = 0; i < 5; i++) {
                    if (y > storeRlTitle.getHeight() + storeLlTitle.getHeight() + store_iv.getHeight()) {
                        int i1 = storeRlTitle.getHeight() + storeLlTitle.getHeight() + store_iv.getHeight() - storeTitle.getHeight();
                        store_xfsv.smoothScrollTo(0, i1);
                    } else {
                        return;
                    }
                }
            } else {
                store_xfsv.smoothScrollTo(0, y);
            }
        }
    }
    private boolean b = false;
    @Override
    public void onScrollChanged(XuanFuScrollview scrollView, int x, int y, int oldx, int oldy) {
        this.y = y;
        if (store_xian_rl != null && store_ying_rl != null) {       //防止刚进入此页面.View还没来得及赋值 用户开始滑动页面,操作View
            if (y >= storeRlTitle.getHeight() + storeLlTitle.getHeight() + store_iv.getHeight() - storeTitle.getHeight()) {
                store_ying_rl.setVisibility(View.VISIBLE);
                store_xian_rl.setVisibility(View.INVISIBLE);
            } else {
                store_ying_rl.setVisibility(View.GONE);
                store_xian_rl.setVisibility(View.VISIBLE);
            }
        }
        if (y <= 0) {   //设置标题的背景颜色
            storeTitle.setBackgroundResource(R.color.transparent);
            storeFanhui.setImageResource(R.drawable.xiangqing_fanhui);
            storeShare.setImageResource(R.drawable.xiangqing_share_no);
            if (isShouCang) {
                storeShoucang.setImageResource(R.drawable.xiangqing_xing);
            } else {
                storeShoucang.setImageResource(R.drawable.xiangqing_xingno);
            }
            b = false;
        } else if (y > 0 && y <= storeRlTitle.getHeight() - storeTitle.getHeight()) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变

            float scale = (float) y / storeRlTitle.getHeight();
            float alpha = (255 * scale);
            storeTitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
        } else {    //滑动到banner下面设置普通颜色
            b = true;
            storeTitle.setBackgroundResource(R.color.white);
            storeFanhui.setImageResource(R.drawable.login_fanhui);
            storeShare.setImageResource(R.drawable.xiangqing_share);
            if (isShouCang) {
                storeShoucang.setImageResource(R.drawable.xiangqing_xing_yes);
            } else {
                storeShoucang.setImageResource(R.drawable.xiangqing_xing_no);
            }
        }
    }

    @Override
    public void onScroll(int scrollY) {

    }

    class MyAdapter extends BaseQuickAdapter<StoreBean.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<StoreBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final StoreBean.ListBean item) {
            helper.setText(R.id.item_nearby_title, item.getGoodtitle())
                    .setText(R.id.item_nearby_xl, item.getOrdersnum_txt())
                    .setText(R.id.item_nearby_money, item.getLowprice());
            helper.getView(R.id.item_nearby_jianlou).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MyApplication.getContext(), JianLouDetailsActivity.class);
                    intent.putExtra("JianLouDetailsActivity_Id", item.getGoodid());
                    startActivity(intent);
                }
            });
            GlideImage.loaderImage(item.getGoodcover(), (ImageView) helper.getView(R.id.item_nearby_pic));
            if (helper.getPosition() > 1) {
                helper.setVisible(R.id.item_nearby_iv1, false);
            } else {
                helper.setVisible(R.id.item_nearby_iv1, true);
            }
            if (helper.getPosition() % 2 == 1) {
                helper.setVisible(R.id.item_nearby_iv_you, false)
                        .setVisible(R.id.item_nearby_iv_zuo, true)
                        .setVisible(R.id.item_nearby_iv_zuo1, false)
                        .setVisible(R.id.item_nearby_iv_you1, true);
            } else {
                helper.setVisible(R.id.item_nearby_iv_you, true)
                        .setVisible(R.id.item_nearby_iv_zuo, false)
                        .setVisible(R.id.item_nearby_iv_zuo1, true)
                        .setVisible(R.id.item_nearby_iv_you1, false);
            }
            View view = helper.getView(R.id.item_nearby_pic);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = width / 2 - 30;
            view.setLayoutParams(layoutParams);
        }
    }

    @OnClick({R.id.store_quanbu, R.id.store_xinpin, R.id.store_remen, R.id.store_money, R.id.store_fanhui, R.id.store_shoucang, R.id.store_share, R.id.store_ying_quanbu, R.id.store_ying_xinpin, R.id.store_ying_remen, R.id.store_ying_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.store_quanbu:
                if (type != 1) {
                    page = 1;
                    type = 1;
                    show(0);
                }
                break;
            case R.id.store_xinpin:
                if (type != 2) {
                    page = 1;
                    type = 2;
                    show(1);
                }
                break;
            case R.id.store_remen:
                if (type != 3) {
                    page = 1;
                    type = 3;
                    show(2);
                }
                break;
            case R.id.store_money:
                if (type != 4) {
                    page = 1;
                    type = 4;
                    storeYingMoneyIv.setBackgroundResource(R.drawable.fenlei_sheng);
                    storeMoneyIv.setBackgroundResource(R.drawable.fenlei_sheng);
                } else {
                    page = 1;
                    type = 5;
                    storeYingMoneyIv.setBackgroundResource(R.drawable.fenlei_jiang);
                    storeMoneyIv.setBackgroundResource(R.drawable.fenlei_jiang);
                }
                show(3);
                break;
            case R.id.store_fanhui:
                finish();
                break;
            case R.id.store_shoucang:
                if (SettingUtils.getInstance().getIsLogin()) {
                    OkGo.post(IURL.Store_ShouCang)
                            .params("user_id", MyApplication.userToken)
                            .params("shop_id", getIntent().getStringExtra("StoreActivity_ID"))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    BaseBean baseBean = JsonUtil.parseJsonToBean(s, BaseBean.class);
                                    if (baseBean != null) {
                                        if (baseBean.getStatus() == 0) {
                                            if (baseBean.getStep() == 1) {//取消
                                                if (b){
                                                    storeShoucang.setImageResource(R.drawable.xiangqing_xing_no);
                                                }else{
                                                    storeShoucang.setImageResource(R.drawable.xiangqing_xingno);
                                                }
                                                isShouCang = false;
                                            } else {        //收藏
                                                if (b){
                                                    storeShoucang.setImageResource(R.drawable.xiangqing_xing_yes);
                                                }else{
                                                    storeShoucang.setImageResource(R.drawable.xiangqing_xing);
                                                }
                                                isShouCang = true;
                                            }
                                        }
                                        ToastUtils.showToast(baseBean.getMsg());
                                    } else {
                                        ToastUtils.showToast("");
                                    }
                                }
                            });
                }
                break;
            case R.id.store_share:
                initDialogView();
                break;
            case R.id.store_ying_quanbu:
                if (type != 1) {
                    page = 1;
                    type = 1;
                    show(0);
                }
                break;
            case R.id.store_ying_xinpin:
                if (type != 2) {
                    page = 1;
                    type = 2;
                    show(1);
                }
                break;
            case R.id.store_ying_remen:
                if (type != 3) {
                    page = 1;
                    type = 3;
                    show(2);
                }
                break;
            case R.id.store_ying_money:
                if (type != 4) {
                    page = 1;
                    type = 4;
                    storeYingMoneyIv.setBackgroundResource(R.drawable.fenlei_sheng);
                    storeMoneyIv.setBackgroundResource(R.drawable.fenlei_sheng);
                } else {
                    page = 1;
                    type = 5;
                    storeYingMoneyIv.setBackgroundResource(R.drawable.fenlei_jiang);
                    storeMoneyIv.setBackgroundResource(R.drawable.fenlei_jiang);
                }
                show(3);
                break;

        }
    }
    Dialog dialog1;
    private void initDialogView() {

        RelativeLayout layout = DiaLog.diaLog(dialog1, this, R.layout.item_fenxiang);
        layout.findViewById(R.id.item_fenxiang_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
                WeiXinUtil.wangye1(bean.getInfo().getShare_url(), bean.getInfo().getShare_title(), bean.getInfo().getShare_dec(), false, bean.getInfo().getShop_pic());
            }
        });
        layout.findViewById(R.id.item_fenxiang_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.item_fenxiang_pengyouquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
                WeiXinUtil.wangye1(bean.getInfo().getShare_url(), bean.getInfo().getShare_title(), bean.getInfo().getShare_dec(), true, bean.getInfo().getShop_pic());
            }
        });
        layout.findViewById(R.id.item_fenxiang_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QqShare.qqShareToTuWen(StoreActivity.this, bean.getInfo().getShare_title(), bean.getInfo().getShare_dec(), bean.getInfo().getShare_url(), bean.getInfo().getShop_pic());
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.item_fenxiang_qqkongjian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QqShare.qzoneShareToTuWen(StoreActivity.this, bean.getInfo().getShare_title(), bean.getInfo().getShare_dec(), bean.getInfo().getShare_url(), bean.getInfo().getShop_pic());
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.item_fenxiang_weibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
                Intent intent = new Intent(StoreActivity.this, WBShareActivity.class);
                intent.putExtra("WBShareActivity_Title", "");
                intent.putExtra("WBShareActivity_Text", bean.getInfo().getShare_title());
                intent.putExtra("WBShareActivity_Url", bean.getInfo().getShare_url());
                startActivity(intent);
            }
        });

    }
    private void show(int j) {
        for (int i = 0; i < list.size(); i++) {
            if (i == j) {
                list.get(j).setTextColor(getResources().getColor(R.color.main_tab_color));
                list1.get(j).setTextColor(getResources().getColor(R.color.main_tab_color));
            } else {
                list.get(i).setTextColor(getResources().getColor(R.color.t33));
                list1.get(i).setTextColor(getResources().getColor(R.color.t33));
            }
        }
        if (type != 4 && type != 5) {
            storeMoneyIv.setBackgroundResource(R.drawable.fenlei_no);
            storeYingMoneyIv.setBackgroundResource(R.drawable.fenlei_no);
        }
        requset();
    }
}
