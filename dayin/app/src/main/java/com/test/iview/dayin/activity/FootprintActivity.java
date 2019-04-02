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
import com.test.iview.dayin.entity.FootprintBean;
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

public class FootprintActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.footpint_xrv)
    XRecyclerView footpintXrv;


    @BindView(R.id.no)
    LinearLayout no;

    private int page = 1;
    private FootprintBean bean;
    private MyAdapter adapter;
    VpSwipeRefreshLayout home_vsrl;
    private boolean isShuaXin = true;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        title.setText("我的足迹");
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
        return R.layout.act_footprint;
    }


    private void request() {

        OkGo.post(IURL.Footprint)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .params("page", page)
                .params("num", 20)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, FootprintBean.class);
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
            adapter = new MyAdapter(R.layout.item_footprint, bean.getList());
            footpintXrv.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
            footpintXrv.setAdapter(adapter);
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
            }, footpintXrv);
            if (page != 1) {
                adapter.loadMoreComplete();
            }
            adapter.setEnableLoadMore(true);
        }
        if (bean.getTotal() > 7) {
            if (bean.getTotal() != 0 && bean.getTotal() <= page * 20) {
                View view = View.inflate(MyApplication.getContext(), R.layout.item_footer, null);
                adapter.addFooterView(view);
            } else {
                adapter.removeAllFooterView();
            }
        }
    }

    class MyAdapter extends BaseQuickAdapter<FootprintBean.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<FootprintBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final FootprintBean.ListBean item) {
            SpannableString ss = new SpannableString("原价:￥" + item.getPrice() );
            ss.setSpan(new StrikethroughSpan(), 3, ss.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.item_footoprint_title, item.getTitle())
                    .setText(R.id.item_footprint_yuanjia, ss)
                    .setText(R.id.item_footprint_num, "最低价:￥" + item.getLowprice());
            GlideImage.loaderImage(item.getPic(), (ImageView) helper.getView(R.id.item_footoprint_pic));
            helper.getView(R.id.item_footprint_jianlou).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MyApplication.getContext(), JianLouDetailsActivity.class);
                    intent.putExtra("JianLouDetailsActivity_Id", item.getId());
                    startActivity(intent);
                }
            });
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
