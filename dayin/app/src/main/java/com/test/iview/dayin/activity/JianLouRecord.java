package com.test.iview.dayin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.RecordBean;
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
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\5\2 0002.
 */

public class JianLouRecord extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.record_all)
    TextView recordAll;
    @BindView(R.id.record_success)
    TextView recordSuccess;
    @BindView(R.id.record_fail)
    TextView recordFail;
    @BindView(R.id.record_xrv)
    XRecyclerView recordXrv;
    @BindView(R.id.no)
    LinearLayout no;


    VpSwipeRefreshLayout home_vsrl;
    private boolean isShuaXin = true;
    private int page = 1;
    private int type = 0;
    private RecordBean bean;
    private MyAdapter adapter;
    private List<TextView> list;


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        title.setText("捡漏记录");
        list = new ArrayList<>();
        list.add(recordAll);
        list.add(recordFail);
        list.add(recordSuccess);
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
        return R.layout.act_jianlourecord;
    }


    private void request() {

        OkGo.post(IURL.Record)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .params("page", page)
                .params("num", 20)
                .params("type", type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, RecordBean.class);
                        if (bean != null) {
                            if (bean.getTotal() == 0) {
                                no.setVisibility(View.VISIBLE);
                                recordXrv.setVisibility(View.GONE);
                            } else {
                                no.setVisibility(View.GONE);
                                recordXrv.setVisibility(View.VISIBLE);
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
            adapter = new MyAdapter(R.layout.item_record, bean.getList());
            recordXrv.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
            recordXrv.setAdapter(adapter);
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
            }, recordXrv);
            if (page != 1) {
                adapter.loadMoreComplete();
            }
            adapter.setEnableLoadMore(true);
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                Intent intent = new Intent(JianLouRecord.this, JianLouDetailsActivity.class);
                intent.putExtra("JianLouDetailsActivity_Id", adapter.getData().get(position).getGoodid());
                startActivity(intent);
            }
        });
        if (bean.getTotal() > 6) {
            if (bean.getTotal() != 0 && bean.getTotal() <= page * 20) {
                View view = View.inflate(MyApplication.getContext(), R.layout.item_footer, null);
                adapter.addFooterView(view);
            } else {
                adapter.removeAllFooterView();
            }
        }
    }

    class MyAdapter extends BaseQuickAdapter<RecordBean.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<RecordBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, RecordBean.ListBean item) {
            SpannableString ss = new SpannableString("原价:￥" + item.getGoodprice());
            ss.setSpan(new StrikethroughSpan(), 3, ss.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.item_record_name, item.getGoodtitle())
                    .setText(R.id.item_record_chujia, "出价:￥" + item.getOrderprice())
                    .setText(R.id.item_record_yuanjia, ss)
                    .setText(R.id.item_record_time, item.getOrdertime());
            GlideImage.loaderImage(item.getGoodcover(), (ImageView) helper.getView(R.id.item_record_pic));
            if (item.getOrderstatus().equals("1")) {
                helper.setText(R.id.item_record_type, "失败")
                        .setTextColor(R.id.item_record_type, getResources().getColor(R.color.t66))
                        .setBackgroundRes(R.id.item_record_type, R.drawable.button_heiseding22);
            } else {
                helper.setText(R.id.item_record_type, "成功")
                        .setTextColor(R.id.item_record_type, getResources().getColor(R.color.white))
                        .setBackgroundRes(R.id.item_record_type, R.drawable.button_hongseding2);
            }
        }
    }

    @OnClick({R.id.fanhui, R.id.record_all, R.id.record_success, R.id.record_fail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.record_all:
                setColor(0);
                break;
            case R.id.record_success:
                setColor(2);
                break;
            case R.id.record_fail:
                setColor(1);
                break;

        }
    }

    private void setColor(int i) {
        if (i != type) {
            type = i;
            for (int j = 0; j < list.size(); j++) {
                if (j != i) {
                    list.get(j).setTextColor(getResources().getColor(R.color.t66));
                } else {
                    list.get(j).setTextColor(getResources().getColor(R.color.main_tab_color));
                }
            }
            page = 1;
            request();
        }
    }
}
