package com.test.iview.dayin.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.WebView1Activity;
import com.test.iview.dayin.entity.FindBean;
import com.test.iview.dayin.entity.TabBean;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.util.GlideImage;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.MyLinearLayoutManager;
import com.test.iview.dayin.view.SimpleCardFragment;
import com.test.iview.dayin.view.VpSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\4\25 0025.
 */

@SuppressLint("ValidFragment")
public class AdContentFragment extends BaseFragment {
    @BindView(R.id.find_xrv)
    RecyclerView findXrv;

    Unbinder unbinder;
    LinearLayout no;
    private FindBean findBean;
    private FindBean findBean1;
    private String type = "0";
    private int page = 1;
    private boolean isOne = true;
    private MyAdapter adapter;
    VpSwipeRefreshLayout home_vsrl;
    private boolean isShuaXin = true;

    public AdContentFragment( ) {}


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(MyApplication.getContext(), R.layout.fagment_find, null);
        no = (LinearLayout) view.findViewById(R.id.no);
        home_vsrl = (VpSwipeRefreshLayout) view.findViewById(R.id.home_vsrl);
        return view;
    }

    @Override
    public void initData() {
//        request();
        home_vsrl.setColorSchemeResources(R.color.main_tab_color);
    }

    private void request() {
        OkGo.post(IURL.Find)
                .tag(this)
                .params("type", type)
                .params("page", page)
                .params("num", 20)
                .execute(new StringCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        findBean = JsonUtil.parseJsonToBean(s, FindBean.class);
                        if (findBean != null) {
                            if (findBean.getStatus() == 0) {
                                if (page != 1) {
                                    adapter.setEnableLoadMore(false);
                                }
                                if (findBean.getTotal() == 0) {
                                    no.setVisibility(View.VISIBLE);
                                    findXrv.setVisibility(View.GONE);
                                } else {
                                    no.setVisibility(View.GONE);
                                    findXrv.setVisibility(View.VISIBLE);
                                }
                                showView();
                            } else {
                                ToastUtils.showToast(findBean.getMsg());
                            }
                        } else {
                            ToastUtils.showToast("");
                        }
                        home_vsrl.setRefreshing(false);
                    }
                });
    }

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    private class MyPagerAdapter extends FragmentPagerAdapter {
        String[] mTitles;

        public MyPagerAdapter(FragmentManager fm, String[] mTitles) {
            super(fm);
            this.mTitles = mTitles;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showView() {
        if (isShuaXin) {
            home_vsrl.setRefreshing(true);
            isShuaXin = false;
        }
        if (isOne) {
            findBean1 = findBean;
            String[] array = new String[findBean1.getCate().size()];
            for (int i = 0; i < findBean1.getCate().size(); i++) {
                mTabEntities.add(new TabBean(findBean1.getCate().get(i).getCategoryname()));
                array[i] = findBean1.getCate().get(i).getCategoryname();
                mFragments.add(SimpleCardFragment.getInstance(findBean1.getCate().get(i).getCategoryname()));
            }
            View decorView = getActivity().getWindow().getDecorView();
            ViewPager vp = decorView.findViewById(R.id.find_vp);
            mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager(), array);
            vp.setAdapter(mAdapter);
            final SlidingTabLayout tabLayout_7 = decorView.findViewById(R.id.find_stl);
            tabLayout_7.setViewPager(vp, array);
            tabLayout_7.setOnTabSelectListener(new OnTabSelectListener() {
                @Override
                public void onTabSelect(int position) {
                    page = 1;
                    type = findBean1.getCate().get(position).getId();
                    request();
                }

                @Override
                public void onTabReselect(int position) {

                }
            });
            isOne = false;
        }
        if (page == 1) {
            adapter = new MyAdapter(R.layout.item_find, findBean.getList());
            findXrv.setLayoutManager(new MyLinearLayoutManager(MyApplication.getContext()));
            findXrv.setAdapter(adapter);
        } else {
            adapter.addData(findBean.getList());
        }
        if (findBean.getTotal() > 19) {
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    if (findBean.getTotal() >= page * 20) {
                        page++;
                        request();
                    } else {
                        adapter.loadMoreComplete();
                        adapter.loadMoreEnd();
                        adapter.setEnableLoadMore(false);
                    }
                }
            }, findXrv);
            if (page != 1) {
                adapter.loadMoreComplete();
            }
            adapter.setEnableLoadMore(true);
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                switch (adapter.getData().get(position).getGod()) {
                    case "2":
                        TextView viewByPosition = (TextView) adapter1.getViewByPosition(findXrv,position, R.id.item_find1_title);
                        viewByPosition.setTextColor(getResources().getColor(R.color.t99));
                        break;
                    case "3":
                        TextView viewByPosition1 = (TextView) adapter1.getViewByPosition(findXrv,position, R.id.item_find2_title);
                        viewByPosition1.setTextColor(getResources().getColor(R.color.t99));
                        break;
                    case "1":
                        TextView viewByPosition2 = (TextView) adapter1.getViewByPosition(findXrv,position, R.id.item_find3_title);
                        viewByPosition2.setTextColor(getResources().getColor(R.color.t99));
                        break;
                }
                Intent intent = new Intent(MyApplication.getContext(), WebView1Activity.class);
                intent.putExtra("WebView_URL", adapter.getData().get(position).getLink());
                intent.putExtra("WebViewShare_URL", adapter.getData().get(position).getLink());
                intent.putExtra("WebView_title", adapter.getData().get(position).getSharetitle());
                intent.putExtra("WebView_Des", adapter.getData().get(position).getSharedes());
                intent.putExtra("WebView_img", adapter.getData().get(position).getSharepic());
                intent.putExtra("WebView_Title_t", adapter.getData().get(position).getCatename());
                startActivity(intent);
            }
        });
        adapter.removeAllFooterView();
        if (findBean.getTotal() > 3) {
            if (findBean.getTotal() != 0 && findBean.getTotal() < page * 20) {
                View view = View.inflate(MyApplication.getContext(), R.layout.item_footer, null);
                adapter.addFooterView(view);
            } else {
                adapter.removeAllFooterView();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class MyAdapter extends BaseQuickAdapter<FindBean.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<FindBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final FindBean.ListBean item) {
            switch (item.getGod()) {
                case "2":
                    helper.setVisible(R.id.item_find1_rl, true)
                            .setVisible(R.id.item_find2_ll, false)
                            .setVisible(R.id.item_find3_ll, false)
                            .setText(R.id.item_find1_title, item.getTitle());
//                        .setText(R.id.item_find1_time, item.getTime());
                    GlideImage.loaderImage(item.getPic().get(0), (ImageView) helper.getView(R.id.item_find1_pic));
                    break;
                case "3":
                    helper.setVisible(R.id.item_find1_rl, false)
                            .setVisible(R.id.item_find2_ll, true)
                            .setVisible(R.id.item_find3_ll, false)
                            .setText(R.id.item_find2_title, item.getTitle());
//                        .setText(R.id.item_find2_time, item.getTime());
                    if (item.getPic().size() == 1) {
                        GlideImage.loaderImage(item.getPic().get(0), (ImageView) helper.getView(R.id.item_find2_pic1));
                    } else if (item.getPic().size() == 2) {
                        GlideImage.loaderImage(item.getPic().get(0), (ImageView) helper.getView(R.id.item_find2_pic1));
                        GlideImage.loaderImage(item.getPic().get(10), (ImageView) helper.getView(R.id.item_find2_pic2));
                    } else if (item.getPic().size() == 3) {
                        GlideImage.loaderImage(item.getPic().get(0), (ImageView) helper.getView(R.id.item_find2_pic1));
                        GlideImage.loaderImage(item.getPic().get(1), (ImageView) helper.getView(R.id.item_find2_pic2));
                        GlideImage.loaderImage(item.getPic().get(2), (ImageView) helper.getView(R.id.item_find2_pic3));
                    }
                    break;
                case "1":
                    helper.setVisible(R.id.item_find1_rl, false)
                            .setVisible(R.id.item_find2_ll, false)
                            .setVisible(R.id.item_find3_ll, true)
                            .setText(R.id.item_find3_title, item.getTitle());
//                        .setText(R.id.item_find3_time, item.getTime());
                    GlideImage.loaderImage(item.getPic().get(0), (ImageView) helper.getView(R.id.item_find3_pic));
                    break;
            }
        }
    }
}
