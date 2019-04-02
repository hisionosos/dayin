package com.test.iview.dayin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.BaseBean;
import com.test.iview.dayin.entity.OrderDataBean;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.util.GlideImage;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\5\2 0002.
 */

public class OrderDataActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.item_orderdata_pic)
    ImageView itemOrderdataPic;
    @BindView(R.id.item_orderdata_title)
    TextView itemOrderdataTitle;
    @BindView(R.id.item_orderdata_data)
    TextView itemOrderdataData;
    @BindView(R.id.item_orderdata_money)
    TextView itemOrderdataMoney;
    @BindView(R.id.item_orderdata_name)
    TextView itemOrderdataName;
    @BindView(R.id.item_orderdata_dizhi)
    TextView itemOrderdataDizhi;
    @BindView(R.id.orderdata_dengdai)
    TextView orderdata_dengdai;
    @BindView(R.id.item_orderdata_type)
    TextView itemOrderdataType;
    @BindView(R.id.item_orderdata_bianhao)
    TextView itemOrderdataBianhao;
    @BindView(R.id.item_orderdata_time)
    TextView itemOrderdataTime;


    @BindView(R.id.orderdata_anniu)
    LinearLayout orderdata_anniu;
    @BindView(R.id.orderdata_shouhuo)
    Button orderdata_shouhuo;
    @BindView(R.id.orderdata_shouhou)
    TextView orderdata_shouhou;
    @BindView(R.id.orederdata_wuliu_no)
    TextView orederdata_wuliu_no;
    @BindView(R.id.orederdata_wuliu)
    RelativeLayout orederdataWuliu;
    @BindView(R.id.logistics_gongsi)
    TextView logisticsGongsi;
    @BindView(R.id.logistics_danhao)
    TextView logisticsDanhao;
    @BindView(R.id.logistics_xrv)
    XRecyclerView logisticsXrv;

    private OrderDataBean bean;
    private MyAdapter adapter;


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        title.setText("订单详情");
        request();
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_orderdata;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        request();
    }

    private void request() {

        OkGo.post(IURL.Order_Data)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .params("order_id", getIntent().getStringExtra("OrderDataActivity_ID"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, OrderDataBean.class);
                        if (bean != null) {
                            if (bean.getStatus() == 0) {
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

    private void showHuo() {
        OkGo.post(IURL.QueRenShouHuo)
                .params("order_id", getIntent().getStringExtra("OrderDataActivity_ID"))
                .params("user_id", MyApplication.userToken)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        BaseBean baseBean = JsonUtil.parseJsonToBean(s, BaseBean.class);
                        if (baseBean != null) {
                            if (baseBean.getStatus() == 0) {
                                request();
                            }
                            ToastUtils.showToast(baseBean.getMsg());
                        } else {
                            ToastUtils.showToast("");
                        }
                    }
                });
    }

    private void showView() {
        if ("".equals(bean.getInfo().getOrderendtime())) {

        } else {
            orderdata_shouhuo.setText("收货时间 " + bean.getInfo().getOrderendtime());
        }
        switch (bean.getInfo().getOrderstatus()) {
            case 1:   //待发货
                orderdata_dengdai.setVisibility(View.VISIBLE);
                itemOrderdataType.setText("  待发货");
                orderdata_anniu.setVisibility(View.GONE);
                break;
            case 2:   //已发货
                itemOrderdataType.setText("  已发货");
                break;
            case 3:   //已签收
                itemOrderdataType.setText("  已确认收货");
                break;
            case 4:   //售后中
                itemOrderdataType.setText("  售后处理中");
                orderdata_shouhou.setText("售后处理中");
                break;
            case 5:   //已完成
                itemOrderdataType.setText("  售后处理完成");
                orderdata_shouhou.setText("售后处理完成");
                break;
        }

        GlideImage.loaderImage(bean.getInfo().getGoodcover(), itemOrderdataPic);
        itemOrderdataMoney.setText(bean.getInfo().getPrice() + "元");
        itemOrderdataTitle.setText(bean.getInfo().getGoodtitle());
        itemOrderdataData.setText(bean.getInfo().getGoodmemo());
        itemOrderdataName.setText(bean.getInfo().getDressname() + "    " + bean.getInfo().getDresstel());
        itemOrderdataDizhi.setText(bean.getInfo().getDressudress());
        itemOrderdataBianhao.setText("订单编号:  " + bean.getInfo().getOrderno());
        itemOrderdataTime.setText("交易时间:  " + bean.getInfo().getOrdertime());
        if (!"".equals(bean.getDeliver_name())) {
            logisticsGongsi.setText("物流公司:  " + bean.getDeliver_name());
            logisticsDanhao.setText("物流单号:  " + bean.getDeliver_no());
            if (bean.getDeliver_list().size() == 0) {
                orederdata_wuliu_no.setVisibility(View.VISIBLE);
            } else {
                adapter = new MyAdapter(R.layout.item_logistics, bean.getDeliver_list());
                logisticsXrv.setLayoutManager(new LinearLayoutManager(this));
                logisticsXrv.setAdapter(adapter);
            }
        } else {
            orederdataWuliu.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.fanhui, R.id.orderdata_shouhuo, R.id.orderdata_shouhou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.orderdata_shouhuo:
                if ("".equals(bean.getInfo().getOrderendtime())) {
                    showHuo();
                }
                break;
            case R.id.orderdata_shouhou:
                Intent intent;
                if (bean.getInfo().getOrderstatus() == 4 || bean.getInfo().getOrderstatus() == 5) {
                    intent = new Intent(this, AfterSaleRecordActivity.class);
                    intent.putExtra("AfterSaleRecordActivity_ID", getIntent().getStringExtra("OrderDataActivity_ID"));
                    startActivity(intent);
                } else {

                    intent = new Intent(this, AfterSaleActivity.class);
                    intent.putExtra("AfterSaleActivity_Pic", bean.getInfo().getGoodcover());
                    intent.putExtra("AfterSaleActivity_Money", bean.getInfo().getPrice());
                    intent.putExtra("AfterSaleActivity_Title", bean.getInfo().getGoodtitle());
                    intent.putExtra("AfterSaleActivity_num", bean.getInfo().getOrderno());
                    intent.putExtra("AfterSaleActivity_ID", getIntent().getStringExtra("OrderDataActivity_ID"));
                    startActivity(intent);
                }
                break;
        }
    }

    class MyAdapter extends BaseQuickAdapter<OrderDataBean.DeliverListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<OrderDataBean.DeliverListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OrderDataBean.DeliverListBean item) {
            helper.setText(R.id.item_logistics_title, item.getStation())
                    .setText(R.id.item_logistics_time, item.getTime());
            if (helper.getPosition() == 0) {
                helper.setTextColor(R.id.item_logistics_title, getResources().getColor(R.color.main_tab_color))
                        .setTextColor(R.id.item_logistics_time, getResources().getColor(R.color.t33));
            } else {
                helper.setTextColor(R.id.item_logistics_title, getResources().getColor(R.color.t66))
                        .setTextColor(R.id.item_logistics_time, getResources().getColor(R.color.t99));
            }
            if (item.getTime().length() == 0) {
                helper.setVisible(R.id.item_logistics_time, false);
                helper.setVisible(R.id.item_logistics_iv, false);
            } else {
                helper.setVisible(R.id.item_logistics_time, true);
                helper.setVisible(R.id.item_logistics_iv, true);
            }
        }
    }
}
