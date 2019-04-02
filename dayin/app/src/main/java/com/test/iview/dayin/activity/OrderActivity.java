package com.test.iview.dayin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.OrderBean;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.util.GlideImage;
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
 * Created by Administrator on 2018\5\2 0002.
 */

public class OrderActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.order_xrv)
    XRecyclerView orderXrv;
    @BindView(R.id.no)
    LinearLayout no;



    private int page = 1;
    private OrderBean bean;
    private MyAdapter adapter;
    VpSwipeRefreshLayout home_vsrl;
    private boolean isShuaXin = true;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        title.setText("我的订单");
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
        return R.layout.act_order;
    }


    @Override
    protected void onResume() {
        super.onResume();
        request();
    }

    private void request() {

        OkGo.post(IURL.Order)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .params("page", page)
                .params("num", 20)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, OrderBean.class);
                        if (bean != null) {
                            if (bean.getTotal() == 0) {
                                no.setVisibility(View.VISIBLE);
                                orderXrv.setVisibility(View.GONE);
                            } else {
                                no.setVisibility(View.GONE);
                                orderXrv.setVisibility(View.VISIBLE);
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
            adapter = new MyAdapter(R.layout.item_order, bean.getList());
            orderXrv.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
            orderXrv.setAdapter(adapter);
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
            }, orderXrv);
            if (page != 1) {
                adapter.loadMoreComplete();
            }
            adapter.setEnableLoadMore(true);
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                if (adapter.getData().get(position).getOrderdeliver() == 9) {
                    Intent intent = new Intent(OrderActivity.this, AddAddressActivity.class);
                    intent.putExtra("AddAddressActivity_Id", Integer.parseInt(adapter.getData().get(position).getOrderid()));
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(OrderActivity.this, OrderDataActivity.class);
                    intent.putExtra("OrderDataActivity_ID", adapter.getData().get(position).getOrderid());
                    startActivity(intent);
                }
//                Intent intent = new Intent(OrderActivity.this, JianLouDetailsActivity.class);
//                intent.putExtra("JianLouDetailsActivity_Id",adapter.getData().get(position).getGoodid());
//                startActivity(intent);
            }
        });
        if (bean.getTotal() > 5) {
            if (bean.getTotal() != 0 && bean.getTotal() <= page * 20) {
                View view = View.inflate(MyApplication.getContext(), R.layout.item_footer, null);
                adapter.addFooterView(view);
            } else {
                adapter.removeAllFooterView();
            }
        }
    }

    class MyAdapter extends BaseQuickAdapter<OrderBean.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<OrderBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final OrderBean.ListBean item) {

            helper.setText(R.id.item_order_order, "订单号： " + item.getOrderno())
                    .setText(R.id.item_order_time, item.getOrdertime())
                    .setText(R.id.item_order_name, item.getGoodtitle())
//                    .setText(R.id.item_order_yuanjia,"原价:  "+item.getGoodprice()+"元")
                    .setText(R.id.item_order_money, "" + item.getOrderprice());
            GlideImage.loaderImage(item.getGoodcover(), (ImageView) helper.getView(R.id.item_order_pic));
            if (item.getOrderdeliver() == 1) {
                helper.setText(R.id.item_order_wancheng, "待发货");
            } else if (item.getOrderdeliver() == 2) {
                helper.setText(R.id.item_order_wancheng, "查看物流");
            } else if (item.getOrderdeliver() == 3) {
                helper.setText(R.id.item_order_wancheng, "已签收");
            } else if (item.getOrderdeliver() == 9) {
                helper.setText(R.id.item_order_wancheng, "完善收货地址");
            }else if (item.getOrderdeliver() == 4) {
                helper.setText(R.id.item_order_wancheng, "售后中");
            }else if (item.getOrderdeliver() == 5) {
                helper.setText(R.id.item_order_wancheng, "已完成");
            }
//            helper.getView(R.id.item_order_xiangqing).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(OrderActivity.this,OrderDataActivity.class);
//                    intent.putExtra("OrderDataActivity_ID",item.getOrderid());
//                    startActivity(intent);
//                }
//            });
//            helper.getView(R.id.item_order_wancheng).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (item.getOrderdeliver()==2){
//                        Intent intent = new Intent(OrderActivity.this,LogisticsActivity.class);
//                        intent.putExtra("LogisticsActivity_Order_ID",item.getOrderid());
//                        startActivity(intent);
//                    }
//                }
//            });
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
}
