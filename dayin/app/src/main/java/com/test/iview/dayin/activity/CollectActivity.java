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
import com.test.iview.dayin.entity.BaseBean;
import com.test.iview.dayin.entity.CollectBean;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.global.MyApplication;
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
 * Created by Administrator on 2018\4\28 0028.
 */

public class CollectActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;@BindView(R.id.no_tv)
    TextView no_tv;
    @BindView(R.id.collect_dianpu)
    TextView collect_dianpu;
    @BindView(R.id.collect_baobei)
    TextView collect_baobei;
    @BindView(R.id.collect_xrv)
    XRecyclerView collectXrv;


    @BindView(R.id.no)
    LinearLayout no;

    private int page = 1;
    private CollectBean bean;
    private MyAdapter adapter;
    VpSwipeRefreshLayout home_vsrl;
    private boolean isShuaXin = true;
    private int type = 0;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        title.setText("我的收藏");
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
        return R.layout.act_collect;
    }

    private void request() {
        if (type==0){
            no_tv.setText("您没有收藏任何宝贝哦!");
        }else{
            no_tv.setText("您没有收藏任何店铺哦!");
        }

        OkGo.post(IURL.Collect)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .params("page", page)
                .params("num", 20)
                .params("type", type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, CollectBean.class);
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
            adapter = new MyAdapter(R.layout.item_collect, bean.getList());
            collectXrv.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
            collectXrv.setAdapter(adapter);
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
            }, collectXrv);
            if (page != 1) {
                adapter.loadMoreComplete();
            }
            adapter.setEnableLoadMore(true);
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                if (type==0){
                    Intent intent = new Intent(CollectActivity.this, JianLouDetailsActivity.class);
                    intent.putExtra("JianLouDetailsActivity_Id", adapter.getData().get(position).getGid());
                    startActivity(intent);
                }else{
                    Intent intent1 = new Intent(CollectActivity.this, StoreActivity.class);
                    intent1.putExtra("StoreActivity_ID", adapter.getData().get(position).getGid());
                    startActivity(intent1);
                }

            }
        });
        if (bean.getTotal() > 8) {
            if (bean.getTotal() != 0 && bean.getTotal() < page * 20) {
                View view = View.inflate(MyApplication.getContext(), R.layout.item_footer, null);
                adapter.addFooterView(view);
            } else {
                adapter.removeAllFooterView();
            }
        }
        collectXrv.scrollToPosition(0);
    }

    @OnClick({R.id.fanhui, R.id.collect_baobei, R.id.collect_dianpu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.collect_baobei:
                if (type != 0) {
                    type = 0;
                    page = 1;
                    collect_baobei.setTextColor(getResources().getColor(R.color.main_tab_color));
                    collect_dianpu.setTextColor(getResources().getColor(R.color.t66));
                    request();
                }
                break;
            case R.id.collect_dianpu:
                if (type != 1) {
                    type = 1;
                    page = 1;
                    collect_dianpu.setTextColor(getResources().getColor(R.color.main_tab_color));
                    collect_baobei.setTextColor(getResources().getColor(R.color.t66));
                    request();
                }
                break;
        }
    }

    class MyAdapter extends BaseQuickAdapter<CollectBean.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<CollectBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final CollectBean.ListBean item) {
            if (type == 0) {

                SpannableString ss = new SpannableString("原价∶￥" + item.getPrice_goods());
                ss.setSpan(new StrikethroughSpan(), 3, ss.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                GlideImage.loaderImage(item.getPic(), (ImageView) helper.getView(R.id.item_collect_pic));
                helper.setText(R.id.item_collect_title, item.getTitle())
                        .setVisible(R.id.item_collect_rl,true)
                        .setVisible(R.id.item_collect_rl1,false)
                        .setTextColor(R.id.item_collect_money,getResources().getColor(R.color.t81))
                        .setTextColor(R.id.item_collect_canyu,getResources().getColor(R.color.main_tab_color))
                        .setText(R.id.item_collect_canyu, "最低价∶￥" + item.getLowprice_nums())
                        .setText(R.id.item_collect_money, ss);
                helper.getView(R.id.item_collect_shoucang).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        OkGo.post(IURL.ShouCang_DEL)
                                .tag(this)
                                .params("user_id", MyApplication.userToken)
                                .params("favorite_id", item.getId())
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        BaseBean baseBean = JsonUtil.parseJsonToBean(s, BaseBean.class);
                                        if (baseBean != null) {
                                            if (baseBean.getStatus() == 0) {
                                                switch (baseBean.getStep()) {
                                                    case 1: //成功
                                                        adapter.remove(helper.getPosition());
                                                        if (adapter.getData().size() == 0) {
                                                            no.setVisibility(View.VISIBLE);
                                                            collectXrv.setVisibility(View.GONE);
                                                        }
                                                        ToastUtils.showToast(baseBean.getMsg());
                                                        break;
                                                    case 2: //失败
                                                        ToastUtils.showToast(baseBean.getMsg());
                                                        break;
                                                    case 3: //获取用户信息失败
                                                        ToastUtils.showToast(baseBean.getMsg());
                                                        break;
                                                }
                                            } else {
                                                ToastUtils.showToast(baseBean.getMsg());
                                            }
                                        } else {
                                            ToastUtils.showToast("");
                                        }

                                    }
                                });
                    }
                });
            } else {
                GlideImage.loaderImage(item.getPic(), (ImageView) helper.getView(R.id.item_collect_pic1));
                helper.setText(R.id.item_collect_title1, item.getTitle())
                        .setVisible(R.id.item_collect_rl,false)
                        .setVisible(R.id.item_collect_rl1,true)
                        .setTextColor(R.id.item_collect_money1,getResources().getColor(R.color.t99))
                        .setTextColor(R.id.item_collect_canyu1,getResources().getColor(R.color.t99))
                        .setText(R.id.item_collect_canyu1, item.getLowprice_nums())
                        .setText(R.id.item_collect_money1, item.getPrice_goods());
                helper.getView(R.id.item_collect_shoucang1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OkGo.post(IURL.ShouCang_DEL)
                                .params("user_id", MyApplication.userToken)
                                .params("favorite_id",  item.getId())
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        BaseBean baseBean = JsonUtil.parseJsonToBean(s, BaseBean.class);
                                        if (baseBean != null) {
                                            if (baseBean.getStatus() == 0) {
                                                if (baseBean.getStep() == 1) {//
                                                    adapter.remove(helper.getPosition());
                                                    if (adapter.getData().size() == 0) {
                                                        no.setVisibility(View.VISIBLE);
                                                        collectXrv.setVisibility(View.GONE);
                                                    }
                                                }
                                                ToastUtils.showToast(baseBean.getMsg());
                                            }
                                            ToastUtils.showToast(baseBean.getMsg());
                                        } else {
                                            ToastUtils.showToast("");
                                        }
                                    }
                                });
                    }
                });
            }
        }
    }
}
