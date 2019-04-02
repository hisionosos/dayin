package com.test.iview.dayin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.IntegrationBean;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.VpSwipeRefreshLayout;
import com.test.iview.dayin.view.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\4\28 0028.
 */

public class IntegrationActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.integration_money)
    TextView integrationMoney;
    @BindView(R.id.integration_huode)
    TextView integrationHuode;
    @BindView(R.id.integration_shiyong)
    TextView integrationShiyong;
    @BindView(R.id.integration_xrv)
    XRecyclerView integrationXrv;



    private int page = 1;
    private IntegrationBean bean;
    private MyAdapter adapter;
    VpSwipeRefreshLayout home_vsrl;
    private boolean isShuaXin = true;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_integration;
    }

    private void initView() {
        title.setText("我的积分");
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

    private void request() {

        OkGo.post(IURL.Integration)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .params("page", page)
                .params("num", 30)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, IntegrationBean.class);
                        if (bean != null) {
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
        integrationMoney.setText(bean.getAllinteral() + "");
        integrationHuode.setText(bean.getCreditnum() + "");
        integrationShiyong.setText(bean.getUse() + "");
        if (page == 1) {
            adapter = new MyAdapter(R.layout.item_accounts, bean.getList());
            integrationXrv.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
            integrationXrv.setAdapter(adapter);
        } else {
            adapter.addData(bean.getList());
        }
        if (bean.getTotal() > 29) {
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    if (bean.getTotal() > page * 30) {
                        page++;
                        request();
                    } else {
                        adapter.loadMoreComplete();
                        adapter.loadMoreEnd();
                        adapter.setEnableLoadMore(false);
                    }
                }
            }, integrationXrv);
            if (page != 1) {
                adapter.loadMoreComplete();
            }
        }
        adapter.setEnableLoadMore(true);
        if (bean.getTotal() > 5) {
            if (bean.getTotal() != 0 && bean.getTotal() < page * 30) {
                View view = View.inflate(MyApplication.getContext(), R.layout.item_footer, null);
                adapter.addFooterView(view);
            } else {
                adapter.removeAllFooterView();
            }
        }
    }

    @OnClick(R.id.fanhui)
    public void onViewClicked() {
        finish();
    }

    class MyAdapter extends BaseQuickAdapter<IntegrationBean.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<IntegrationBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, IntegrationBean.ListBean item) {
            helper.setText(R.id.item_accounts_time, item.getTime())
                    .setText(R.id.item_accounts_name, item.getTxt());
            switch (item.getType()) {
                case "0":
                    helper
                            .setText(R.id.item_accounts_money, "+" + item.getNum())
                            .setTextColor(R.id.item_accounts_money, getResources().getColor(R.color.main_tab_color));
                    break;
                case "1":
                    helper
                            .setText(R.id.item_accounts_money, "+" + item.getNum())
                            .setTextColor(R.id.item_accounts_money, getResources().getColor(R.color.main_tab_color));
                    break;
                case "2":
                    helper
                            .setText(R.id.item_accounts_money, "+" + item.getNum())
                            .setTextColor(R.id.item_accounts_money, getResources().getColor(R.color.main_tab_color));
                    break;
                case "3":
                    helper
                            .setText(R.id.item_accounts_money, "+" + item.getNum())
                            .setTextColor(R.id.item_accounts_money, getResources().getColor(R.color.main_tab_color));
                    break;
                case "4":
                    helper
                            .setText(R.id.item_accounts_money, "-" + item.getNum())
                            .setTextColor(R.id.item_accounts_money, getResources().getColor(R.color.t66));
                    break;
                case "5":
                    helper
                            .setText(R.id.item_accounts_money, "+" + item.getNum())
                            .setTextColor(R.id.item_accounts_money, getResources().getColor(R.color.main_tab_color));
                    break;
            }
        }
    }
}
