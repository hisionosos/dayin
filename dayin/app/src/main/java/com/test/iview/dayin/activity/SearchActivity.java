package com.test.iview.dayin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.SearchBean;
import com.test.iview.dayin.entity.SearchBean1;
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
 * Created by Administrator on 2018\5\11 0011.
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.no)
    LinearLayout no;
    @BindView(R.id.search_xrv)
    XRecyclerView searchXrv;
    @BindView(R.id.search_xrv1)
    XRecyclerView searchXrv1;


    int width;
    private MyAdapter adapter;
    private MyAdapter1 adapter1;
    private int page = 1;
    private SearchBean bean;
    private SearchBean1 bean1;
    InputMethodManager imm; //用于点击搜索后隐藏软键盘
    VpSwipeRefreshLayout home_vsrl;
    private boolean isShuaXin = true;


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;     // 屏幕宽度（像素）
        imm = (InputMethodManager) getSystemService(
                INPUT_METHOD_SERVICE);
        request();
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
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_search;
    }


    private void request() {
        OkGo.post(IURL.Search_Init)
                .params("user_id", MyApplication.userToken)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, SearchBean.class);
                        if (bean != null) {
                            if (bean.getStatus() == 0) {
                                showYouLike();
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

    private void showYouLike() {
        if (isShuaXin) {
            home_vsrl.setRefreshing(true);
            isShuaXin = false;
        }
        adapter1 = new MyAdapter1(R.layout.item_nearby, bean.getRelist());
        searchXrv1.setLayoutManager(new GridLayoutManager(MyApplication.getContext(), 2));
        searchXrv1.setAdapter(adapter1);
        adapter1.addHeaderView(View.inflate(this, R.layout.item_youlike, null));
    }


    @OnClick({R.id.search_fanhui, R.id.search_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_fanhui:
                finish();
                break;
            case R.id.search_search:
                if (!searchEt.getText().toString().equals("")) {
                    imm.hideSoftInputFromWindow(searchEt.getWindowToken(), 0);  //隐藏软键盘
                    page = 1;
                    search();
                } else {
                    ToastUtils.showToast("你啥都没有写嘛~");
                }
                break;

        }
    }

    private void search() {

        OkGo.post(IURL.Search_)
                .params("key", searchEt.getText().toString())
                .params("page", page)
                .params("num", 20)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean1 = JsonUtil.parseJsonToBean(s, SearchBean1.class);
                        if (bean1 != null) {
                            if (bean1.getStatus() == 0) {
                                if (page != 1) {
                                    adapter.setEnableLoadMore(false);
                                }
                                if (bean1.getTotal() == 0) {
                                    no.setVisibility(View.VISIBLE);
                                } else {
                                    no.setVisibility(View.GONE);
                                }
                                showView();

                            } else {
                                ToastUtils.showToast(bean1.getMsg());
                            }
                        } else {
                            ToastUtils.showToast("");
                        }



                    }
                });
    }

    private void showView() {
        searchXrv.setVisibility(View.VISIBLE);
        searchXrv1.setVisibility(View.GONE);
        if (page == 1) {
            adapter = new MyAdapter(R.layout.item_home_all, bean1.getList());
            searchXrv.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
            searchXrv.setAdapter(adapter);
        } else {
            adapter.addData(bean1.getList());
        }
        if (bean1.getTotal() > 19) {
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    if (bean1.getTotal() >= page * 20) {
                        page++;
                        search();
                    } else {
                        adapter.loadMoreComplete();
                        adapter.loadMoreEnd();
                        adapter.setEnableLoadMore(false);
                    }
                }
            }, searchXrv);
            if (page != 1) {
                adapter.loadMoreComplete();
            }
            adapter.setEnableLoadMore(true);
        }
        if (bean1.getTotal() > 4) {
            if (bean1.getTotal() != 0 && bean1.getTotal() < page * 20) {
                View view = View.inflate(MyApplication.getContext(), R.layout.item_footer, null);
                adapter.addFooterView(view);
            } else {
                adapter.removeAllFooterView();
            }
        }
    }

    class MyAdapter extends BaseQuickAdapter<SearchBean1.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<SearchBean1.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final SearchBean1.ListBean item) {
            StringBuffer str = new StringBuffer("");

            if (item.getKey()!= null) {
//                str = Utiles.addChild(item.getTitle(), item.getKey(), str);
            }

            SpannableString ss = new SpannableString(" ￥" + item.getPrice());
            ss.setSpan(new StrikethroughSpan(), 0, ss.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            helper.setText(R.id.item_home_all_name, Html.fromHtml(str.toString()))
                    .setText(R.id.item_home_all_yuanjia, ss)
                    .setText(R.id.item_home_all_canyu, "￥" + item.getLowprice());
            GlideImage.loaderImage(item.getCover(), (ImageView) helper.getView(R.id.item_home_all_pic));
            helper.getView(R.id.item_home_all_jianlou).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MyApplication.getContext(), JianLouDetailsActivity.class);
                    intent.putExtra("JianLouDetailsActivity_Id", item.getId());
                    startActivity(intent);
                }
            });
        }
    }

    class MyAdapter1 extends BaseQuickAdapter<SearchBean.RelistBean, BaseViewHolder> {

        public MyAdapter1(int layoutResId, @Nullable List<SearchBean.RelistBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final SearchBean.RelistBean item) {
            helper.setText(R.id.item_nearby_title, item.getTitle())
                    .setText(R.id.item_nearby_xl, item.getOrdersnum_txt())
                    .setText(R.id.item_nearby_money, item.getLowprice());


            helper.getView(R.id.item_nearby_jianlou).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MyApplication.getContext(), JianLouDetailsActivity.class);
                    intent.putExtra("JianLouDetailsActivity_Id", item.getId());
                    startActivity(intent);
                }
            });
            GlideImage.loaderImage(item.getGoodcover(), (ImageView) helper.getView(R.id.item_nearby_pic));
            helper.setVisible(R.id.item_nearby_iv1, false);
            if (helper.getPosition() - 1 % 2 == 1) {
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
