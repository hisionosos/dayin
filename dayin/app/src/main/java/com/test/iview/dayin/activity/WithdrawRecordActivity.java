package com.test.iview.dayin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.WithdrawRecordBean;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\5\15 0015.
 */

public class WithdrawRecordActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.no)
    LinearLayout no;
    @BindView(R.id.withdrawrecor_xrv)
    XRecyclerView withdrawrecorXrv;


    private int page = 1;
    private MyAdapter adapter;
    private WithdrawRecordBean bean;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        title.setText("提现记录");
        request();
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_withdrawrecor;
    }


    private void request() {

        OkGo.post(IURL.Withdraw_Record)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .params("page", page)
                .params("num", 30)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, WithdrawRecordBean.class);
                        if (bean != null) {
                            if (bean.getStatus() == 0) {
                                if (page != 1) {
                                    adapter.setEnableLoadMore(false);
                                }
                                if (bean.getTotal() == 0) {
                                    no.setVisibility(View.VISIBLE);
                                } else {
                                    no.setVisibility(View.GONE);
                                }
                                showView();
                            } else {
                                ToastUtils.showToast(bean.getMsg());
                            }
                        } else {
                            ToastUtils.showToast("");
                        }

                    }
                });
    }

    private void showView() {
        if (page == 1) {
            adapter = new MyAdapter(R.layout.item_withdraw, bean.getList());
            withdrawrecorXrv.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
            withdrawrecorXrv.setAdapter(adapter);
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
            }, withdrawrecorXrv);
            if (page != 1) {
                adapter.loadMoreComplete();
            }
            adapter.setEnableLoadMore(true);
        }
        if (bean.getTotal() > 10) {
            if (bean.getTotal() != 0 && bean.getTotal() < page * 30) {
                View view = View.inflate(MyApplication.getContext(), R.layout.item_footer, null);
                adapter.addFooterView(view);
            } else {
                adapter.removeAllFooterView();
            }
        }
    }

    @OnClick({R.id.fanhui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;

        }
    }

    class MyAdapter extends BaseQuickAdapter<WithdrawRecordBean.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<WithdrawRecordBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, WithdrawRecordBean.ListBean item) {
            helper.setText(R.id.item_withdraw_name, item.getCash_info())
                    .setText(R.id.item_withdraw_time, item.getTime())
                    .setText(R.id.item_withdraw_money, item.getCash_num())
                    .setText(R.id.item_withdraw_type, item.getCash_type());


        }
    }
}
