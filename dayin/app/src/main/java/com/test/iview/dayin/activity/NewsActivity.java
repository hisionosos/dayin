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
import com.test.iview.dayin.entity.NewsBean;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\5\4 0004.
 */

public class NewsActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.news_xrv)
    XRecyclerView newsXrv;
    @BindView(R.id.no)
    LinearLayout no;



    private NewsBean bean;
    private int page = 1;
    private MyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news);
        ButterKnife.bind(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        title.setText("消息");
//        request();
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_news;
    }


    private void request() {

        OkGo.post(IURL.News)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .params("page", page)
                .params("num", 20)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, NewsBean.class);
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

                    }
                });
    }

    private void showView() {
        if (page == 1) {
            adapter = new MyAdapter(R.layout.item_news, bean.getList());
            newsXrv.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
            newsXrv.setAdapter(adapter);
        } else {
            adapter.addData(bean.getList());
        }
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
        }, newsXrv);
        if (page != 1) {
            adapter.loadMoreComplete();
        }
        adapter.setEnableLoadMore(true);
    }

    @OnClick({R.id.fanhui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
        }
    }
    class MyAdapter extends BaseQuickAdapter<NewsBean.ListBean,BaseViewHolder>{

        public MyAdapter(int layoutResId, @Nullable List<NewsBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, NewsBean.ListBean item) {
            helper.setText(R.id.item_news_time,item.getTime()).
                    setText(R.id.item_news_title,item.getMsgtxt());
            if (item.getMsgtype()==0||item.getMsgtype()==6||item.getMsgtype()==5||item.getMsgtype()==7||item.getMsgtype()==8||item.getMsgtype()==10){
                helper.setTextColor(R.id.item_news_title,getResources().getColor(R.color.main_tab_color));
            }else{
                helper.setTextColor(R.id.item_news_title,getResources().getColor(R.color.t99));
            }
        }
    }
}
