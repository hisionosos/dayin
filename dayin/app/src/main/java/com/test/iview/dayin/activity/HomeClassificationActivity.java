package com.test.iview.dayin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.HomeClassificationBean;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.GlideImage;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.VpSwipeRefreshLayout;
import com.test.iview.dayin.view.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class HomeClassificationActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.classification_quanbu)
    TextView classification_quanbu;
    @BindView(R.id.classification_money_tv)
    TextView classification_money_tv;
    @BindView(R.id.classification_xinpin)
    TextView classification_xinpin;
    @BindView(R.id.classification_remen)
    TextView classification_remen;
    @BindView(R.id.classification_money_iv)
    ImageView classification_money_iv;
    @BindView(R.id.act_home_classification_xrv)
    XRecyclerView act_home_classification_xrv;


    @BindView(R.id.no)
    LinearLayout no;
    private int page = 1;
    private HomeClassificationBean bean;
    private MyAdapter adapter;
    int width;
    private int type = 1;
    private int flag = 0;
    private List<TextView> list;
    VpSwipeRefreshLayout home_vsrl;
    private boolean isShuaXin = true;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        JAnalyticsInterface.onPageEnd(this, getIntent().getStringExtra("Home_Classification_title"));
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        JAnalyticsInterface.onPageStart(this, getIntent().getStringExtra("Home_Classification_title"));
        list = new ArrayList<>();
        list.add(classification_quanbu);
        list.add(classification_xinpin);
        list.add(classification_remen);
        list.add(classification_money_tv);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;     // 屏幕宽度（像素）
        title.setText(getIntent().getStringExtra("Home_Classification_title"));
        home_vsrl = (VpSwipeRefreshLayout) findViewById(R.id.home_vsrl);
        home_vsrl.setColorSchemeResources(R.color.main_tab_color);
        home_vsrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isShuaXin = true;
                page = 1;
                home_vsrl.setRefreshing(true);
                request();
            }
        });
        request();
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_home_classification;
    }


    private void request() {

        OkGo.post(IURL.Home_Classification)
                .tag(this)
                .params("id", getIntent().getStringExtra("Home_Classification_id"))
                .params("page", page)
                .params("num", 20)
                .params("type", type)
                .params("user_id", MyApplication.userToken)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, HomeClassificationBean.class);
                        if (bean != null) {
                            if (bean.getTotal() == 0) {
                                no.setVisibility(View.VISIBLE);
                            } else {
                                no.setVisibility(View.GONE);
                            }
                            if (bean.getStatus() == 0) {
                                if (page != 1) {
                                    adapter.setEnableLoadMore(false);
                                }
                                showView();
                            } else {
                                ToastUtils.showToast(bean.getMsg());
                            }
                        } else {
                            ToastUtils.showToast("");
                        }

                        home_vsrl.setRefreshing(false);
                    }
                });
    }

    private void showView() {
        if (isShuaXin) {
            home_vsrl.setRefreshing(true);
            isShuaXin = false;
        }
        if (page == 1) {
            adapter = new MyAdapter(R.layout.item_nearby, bean.getList());
            act_home_classification_xrv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(), 2));
            act_home_classification_xrv.setAdapter(adapter);
        } else {
            adapter.addData(bean.getList());
        }
        if (bean.getTotal() > 19) {
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    if (bean.getTotal() > page * 20) {
                        page++;
                        request();
                    } else {
                        adapter.loadMoreComplete();
                        adapter.loadMoreEnd();
                        adapter.setEnableLoadMore(false);
                    }
                }
            }, act_home_classification_xrv);
            if (page != 1) {
                adapter.loadMoreComplete();
            }
        }
        adapter.setEnableLoadMore(true);
        if (bean.getTotal() > 2) {
            if (bean.getTotal() != 0 && bean.getTotal() < page * 20) {
                View view = View.inflate(MyApplication.getContext(), R.layout.item_footer, null);
                adapter.addFooterView(view);
            } else {
                adapter.removeAllFooterView();
            }
        }
    }

    class MyAdapter extends BaseQuickAdapter<HomeClassificationBean.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<HomeClassificationBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final HomeClassificationBean.ListBean item) {
            helper.setText(R.id.item_nearby_title, item.getTitle())
                    .setText(R.id.item_nearby_xl, item.getOrdersnum_txt())
                    .setText(R.id.item_nearby_money, item.getLowprice());
            helper.getView(R.id.item_nearby_jianlou).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MyApplication.getContext(), JianLouDetailsActivity.class);
                    intent.putExtra("JianLouDetailsActivity_Id", item.getId());
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

    @OnClick({R.id.fanhui, R.id.classification_quanbu, R.id.classification_xinpin, R.id.classification_remen, R.id.classification_money, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.classification_quanbu:
                if (type != 1) {
                    page = 1;
                    type = 1;
                    show(0);
                }
                break;
            case R.id.classification_xinpin:
                if (type != 2) {
                    page = 1;
                    type = 2;
                    show(1);
                }
                break;
            case R.id.classification_remen:
                if (type != 3) {
                    page = 1;
                    type = 3;
                    show(2);
                }
                break;
            case R.id.classification_money:
                if (type != 4) {
                    page = 1;
                    type = 4;
                    classification_money_iv.setBackgroundResource(R.drawable.fenlei_sheng);
                } else {
                    page = 1;
                    type = 5;
                    classification_money_iv.setBackgroundResource(R.drawable.fenlei_jiang);
                }
                show(3);
                break;

            case R.id.search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
    }

    private void show(int j) {
        for (int i = 0; i < list.size(); i++) {
            if (i == j) {
                list.get(j).setTextColor(getResources().getColor(R.color.main_tab_color));
            } else {
                list.get(i).setTextColor(getResources().getColor(R.color.t33));
            }
        }
        if (type != 4 && type != 5) {
            classification_money_iv.setBackgroundResource(R.drawable.fenlei_no);
        }
        request();
    }
}
