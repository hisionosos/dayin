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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Dialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.ZhuanTiBean;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.util.DiaLog;
import com.test.iview.dayin.util.GlideImage;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.util.QqShare;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.VpSwipeRefreshLayout;
import com.test.iview.dayin.view.XRecyclerView;
import com.test.iview.dayin.wbapi.WBShareActivity;
import com.test.iview.dayin.wxapi.WeiXinUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\6\13 0013.
 */

public class ZhuanTiActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.zhuanti_xrv)
    XRecyclerView zhuantiXrv;


    @BindView(R.id.zhuanti_vsrl)
    VpSwipeRefreshLayout zhuantiVsrl;

    private MyAdapter adapter;
    private int page = 1;
    private ZhuanTiBean bean;
    private boolean isShuaXin = true;
    int width;
    private boolean isOne = true;
    private String title1;
    private String pic;
    private String desc;
    private String url;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        zhuantiVsrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isShuaXin = true;
                page = 1;
                zhuantiVsrl.setRefreshing(true);
                request();
            }
        });
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;     // 屏幕宽度（像素）
        request();
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_zhuanti;
    }

    private void request() {
        if (isOne){
            isOne = false;

        }
        OkGo.post(IURL.ZhuanTi)
                .tag(this)
                .params("topic_id",getIntent().getStringExtra("ZhuanTiActivity_ID"))
                .params("page",page)
                .params("num",10)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s,ZhuanTiBean.class);
                        if (bean!=null){
                            if (bean.getStatus()==0){
                                if (page != 1) {
                                    adapter.setEnableLoadMore(false);
                                }
                                showView();
                            }else{
                                ToastUtils.showToast(bean.getMsg());
                            }
                        }else{
                            ToastUtils.showToast("");
                        }

                        zhuantiVsrl.setRefreshing(false);
                    }
                });
    }

    private void showView() {
        if (isShuaXin) {
            zhuantiVsrl.setRefreshing(true);
            isShuaXin = false;
        }
        if (page == 1) {
            adapter = new MyAdapter(R.layout.item_nearby, bean.getList());
            zhuantiXrv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(),2));
            zhuantiXrv.setAdapter(adapter);
        } else {
            adapter.addData(bean.getList());
        }
        if (bean.getTotal() > 9) {
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    if (bean.getTotal() >= page * 10) {
                        page++;
                        request();
                    } else {
                        adapter.loadMoreComplete();
                        adapter.loadMoreEnd();
                        adapter.setEnableLoadMore(false);
                    }
                }
            }, zhuantiXrv);
            if (page != 1) {
                adapter.loadMoreComplete();
            }
        }
        adapter.setEnableLoadMore(true);
        if (bean.getTotal() > 5) {
            if (bean.getTotal() != 0 && bean.getTotal() < page * 10) {
                View view = View.inflate(this, R.layout.item_footer, null);
                adapter.addFooterView(view);
            } else {
                adapter.removeAllFooterView();
            }
        }
        title.setText(bean.getInfo().getTitle());
        if (page==1){
            title1 = bean.getInfo().getSharetitle();
            pic = bean.getInfo().getSharepic();
            desc = bean.getInfo().getSharedesc();
            url = bean.getInfo().getShareurl();
            View view = View.inflate(this,R.layout.item_pic,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.item_pic);
            GlideImage.loaderImage(bean.getInfo().getPic(),imageView);
            adapter.addHeaderView(view);
        }
    }

    @OnClick({R.id.fanhui, R.id.title11})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.title11:
                initDialogView();
                break;
        }
    }
    Dialog dialog;
    private void initDialogView() {

        RelativeLayout layout = DiaLog.diaLog(dialog, this, R.layout.item_fenxiang);
        layout.findViewById(R.id.item_fenxiang_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
                WeiXinUtil.wangye1(url, title1, desc, false, pic);
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
                WeiXinUtil.wangye1(url, title1, desc, true, pic);
            }
        });
        layout.findViewById(R.id.item_fenxiang_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QqShare.qqShareToTuWen(ZhuanTiActivity.this,title1, desc,url, pic);
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.item_fenxiang_qqkongjian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QqShare.qzoneShareToTuWen(ZhuanTiActivity.this, title1,desc,url,pic);
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.item_fenxiang_weibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
                Intent intent = new Intent(ZhuanTiActivity.this, WBShareActivity.class);
                intent.putExtra("WBShareActivity_Title", "");
                intent.putExtra("WBShareActivity_Text", title1);
                intent.putExtra("WBShareActivity_Url", url);
                startActivity(intent);
            }
        });

    }
    class MyAdapter extends BaseQuickAdapter<ZhuanTiBean.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<ZhuanTiBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final ZhuanTiBean.ListBean item) {
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
            if (helper.getPosition() > 2) {
                helper.setVisible(R.id.item_nearby_iv1, false);
            } else {
                helper.setVisible(R.id.item_nearby_iv1, true);
            }
            if (helper.getPosition()-1 % 2 == 1) {
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
}
