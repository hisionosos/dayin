package com.test.iview.dayin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.test.iview.dayin.entity.AfterSaleRecordBean;
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
 * Created by Administrator on 2018\6\12 0012.
 */

public class AfterSaleRecordActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.aftersalerecord_pic)
    ImageView aftersalerecordPic;
    @BindView(R.id.aftersalerecord_title)
    TextView aftersalerecordTitle;
    @BindView(R.id.aftersalerecord_money)
    TextView aftersalerecordMoney;
    @BindView(R.id.aftersalerecord_shijian)
    TextView aftersalerecordShijian;
    @BindView(R.id.aftersalerecord_bianhao)
    TextView aftersalerecordBianhao;
    @BindView(R.id.aftersalerecord_leixing)
    TextView aftersalerecordLeixing;
    @BindView(R.id.aftersalerecord_yuanyin)
    TextView aftersalerecordYuanyin;
    @BindView(R.id.aftersalerecord_no)
    TextView aftersalerecordNo;
    @BindView(R.id.aftersalerecord_xrv)
    XRecyclerView aftersalerecordXrv;



    private AfterSaleRecordBean bean;
    private MyAdapter adapter;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        title.setText("售后记录");
        requset();
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_aftersalerecord;
    }

    private void requset() {

        OkGo.post(IURL.AfterSaleRecord)
                .params("order_id",getIntent().getStringExtra("AfterSaleRecordActivity_ID"))
                .params("user_id", MyApplication.userToken)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s,AfterSaleRecordBean.class);
                        if (bean!=null){
                            if (bean.getStatus()==0){
                                showView();
                            }else{
                                ToastUtils.showToast(bean.getMsg());
                            }

                        }else{
                            ToastUtils.showToast("");
                        }

                    }
                });
    }

    private void showView() {
        GlideImage.loaderImage(bean.getInfo().getGood_pic(),aftersalerecordPic);
        aftersalerecordTitle.setText(bean.getInfo().getGood_title());
        aftersalerecordMoney.setText(bean.getInfo().getOrder_price()+"元");
        aftersalerecordShijian.setText(bean.getInfo().getServer_add_time());
        aftersalerecordLeixing.setText(bean.getInfo().getServer_type_txt());
        aftersalerecordYuanyin.setText(bean.getInfo().getServer_content());
        aftersalerecordBianhao.setText(bean.getInfo().getServer_no());
        if (bean.getList().size()==0){
            aftersalerecordNo.setVisibility(View.VISIBLE);
        }else{
            adapter = new MyAdapter(R.layout.item_logistics1, bean.getList());
            aftersalerecordXrv.setLayoutManager(new LinearLayoutManager(this));
            aftersalerecordXrv.setAdapter(adapter);
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
    class MyAdapter extends BaseQuickAdapter<AfterSaleRecordBean.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<AfterSaleRecordBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper,AfterSaleRecordBean.ListBean item) {
            helper.setText(R.id.item_logistics_title, item.getMsginfo())
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
