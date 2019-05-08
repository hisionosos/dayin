package com.test.iview.dayin.activity;

import android.content.Intent;
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
import com.test.iview.dayin.entity.AccountsBean;
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

public class AccountsActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.accounts_money)
    TextView accountsMoney;
    @BindView(R.id.accounts_zhichu_tv)
    TextView accounts_zhichu_tv;
    @BindView(R.id.accounts_chongzhi_tv)
    TextView accounts_chongzhi_tv;
    @BindView(R.id.accounts_xrv)
    XRecyclerView accountsXrv;


    private int page = 1;
    private AccountsBean bean;
    private MyAdapter adapter;
    VpSwipeRefreshLayout home_vsrl;
    private boolean isShuaXin = true;

    @Override
    protected void onResume() {
        super.onResume();
        request();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        title.setText("我的账户");
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
        return R.layout.act_accounts;
    }

    private void request() {

        OkGo.post(IURL.Accounts)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .params("page", page)
                .params("num", 30)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, AccountsBean.class);
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
        accountsMoney.setText(bean.getMoney());
        accounts_chongzhi_tv.setText(bean.getRecharge());
        accounts_zhichu_tv.setText(bean.getPay());
        if (page == 1) {
            adapter = new MyAdapter(R.layout.item_accounts, bean.getList());
            accountsXrv.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
            accountsXrv.setAdapter(adapter);
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
            }, accountsXrv);
            if (page != 1) {
                adapter.loadMoreComplete();
            }
            adapter.setEnableLoadMore(true);
        }
        if (bean.getTotal() > 10) {
            if (bean.getTotal() != 0 && bean.getTotal() <= page * 30) {
                View view = View.inflate(MyApplication.getContext(), R.layout.item_footer, null);
                adapter.addFooterView(view);
            } else {
                adapter.removeAllFooterView();
            }
        }
    }

    @OnClick({R.id.fanhui, R.id.accounts_tixian, R.id.accounts_chongzhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.accounts_tixian:
                startActivity(new Intent(this, WithdrawActivity.class));
                break;
            case R.id.accounts_chongzhi:
                startActivity(new Intent(this, ChongZhiActivity.class));
                break;
        }
    }

    class MyAdapter extends BaseQuickAdapter<AccountsBean.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<AccountsBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, AccountsBean.ListBean item) {
            helper.setText(R.id.item_accounts_time, item.getTime())
                    .setText(R.id.item_accounts_name,item.getTxt());
            switch (item.getType()) {
                case "0":
                    helper
                            .setText(R.id.item_accounts_money, "-" + item.getNum())
                            .setTextColor(R.id.item_accounts_money, getResources().getColor(R.color.t66));
                    break;
                case "1":
                    helper
                            .setText(R.id.item_accounts_money, "+" + item.getNum())
                            .setTextColor(R.id.item_accounts_money, getResources().getColor(R.color.main_tab_color));
                    break;
                case "2":
                    helper
                            .setText(R.id.item_accounts_money, "-" + item.getNum())
                            .setTextColor(R.id.item_accounts_money, getResources().getColor(R.color.t66));
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



