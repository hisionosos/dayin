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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.JianLouDetailsActivity;
import com.test.iview.dayin.entity.HotBean;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.util.GlideImage;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.utils.ScreenUtils;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.VpSwipeRefreshLayout;
import com.test.iview.dayin.view.XRecyclerView;

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
public class PlayFragment extends BaseFragment {
    @BindView(R.id.hot_xrv)
    XRecyclerView hot_xrv;
    @BindView(R.id.home_vsrl)
    VpSwipeRefreshLayout home_vsrl;
    LinearLayout no;

    private String type = "0";
    private int page = 1;
    private boolean isOne = true;
    private MyAdapter adapter;
    private HotBean hotBean;
    private boolean isShuaXin = true;

    public PlayFragment() {

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(MyApplication.getContext(), R.layout.fagment_hot, null);
        no = (LinearLayout) view.findViewById(R.id.no);
        home_vsrl = (VpSwipeRefreshLayout) view.findViewById(R.id.home_vsrl);
        return view;
    }

    @Override
    public void initData() {
        request();
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

    private void request() {
        OkGo.post(IURL.Hot)
                .tag(this)
                .params("type", type)
                .params("page", page)
                .params("num", 20)
                .execute(new StringCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        hotBean = JsonUtil.parseJsonToBean(s, HotBean.class);
                        if (hotBean != null) {
                            if (hotBean.getStatus() == 0) {
                                if (page != 1) {
                                    adapter.setEnableLoadMore(false);
                                }
                                if (hotBean.getTotal() == 0) {
                                    no.setVisibility(View.VISIBLE);
                                    hot_xrv.setVisibility(View.GONE);
                                } else {
                                    no.setVisibility(View.GONE);
                                    hot_xrv.setVisibility(View.VISIBLE);
                                }
                                showView();
                            } else {
                                ToastUtils.showToast(hotBean.getMsg());
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
//        if (isOne) {
//            isOne = false;
//            hotBean1 = hotBean;
//            String[] array = new String[hotBean1.getCate().size()];
//            for (int i = 0; i < hotBean1.getCate().size(); i++) {
//
//                mTabEntities.add(new TabBean(hotBean1.getCate().get(i).getCategoryname()));
//                array[i] = hotBean1.getCate().get(i).getCategoryname();
//                mFragments.add(SimpleCardFragment.getInstance(hotBean1.getCate().get(i).getCategoryname()));
//            }
//            View decorView = getActivity().getWindow().getDecorView();
//            ViewPager vp = ViewFindUtils.play_check(decorView, R.id.hot_vp);
//            mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager(), array);
//            vp.setAdapter(mAdapter);
//            final SlidingTabLayout tabLayout_7 = ViewFindUtils.play_check(decorView, R.id.hot_stl);
//            tabLayout_7.setViewPager(vp, array);
//            tabLayout_7.setOnTabSelectListener(new OnTabSelectListener() {
//                @Override
//                public void onTabSelect(int position) {
//                    page = 1;
//                    type = hotBean1.getCate().get(position).getId();
//                    request();
//                }
//
//                @Override
//                public void onTabReselect(int position) {
//                }
//            });
//        }
        if (page == 1) {
            adapter = new MyAdapter(R.layout.item_nearby, hotBean.getList());
            hot_xrv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(),2));
            hot_xrv.setAdapter(adapter);
        } else {
            adapter.addData(hotBean.getList());
        }
        if (hotBean.getTotal() > 19) {
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    if (hotBean.getTotal() >= page * 20) {
                        page++;
                        request();
                    } else {
                        adapter.loadMoreComplete();
                        adapter.loadMoreEnd();
                        adapter.setEnableLoadMore(false);
                    }
                }
            }, hot_xrv);
            if (page != 1) {
                adapter.loadMoreComplete();
            }
            adapter.setEnableLoadMore(true);
        }
        if (hotBean.getTotal() > 4) {
            if (hotBean.getTotal() != 0 && hotBean.getTotal() < page * 20) {
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

    class MyAdapter extends BaseQuickAdapter<HotBean.ListBean, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<HotBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final HotBean.ListBean item) {
            helper.setText(R.id.item_nearby_title,item.getGoodtitle())
                    .setText(R.id.item_nearby_money,item.getLowprice())
                    .setText(R.id.item_nearby_xl,item.getOrdersnum_txt());
            helper.getView(R.id.item_nearby_jianlou).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MyApplication.getContext(), JianLouDetailsActivity.class);
                    intent.putExtra("JianLouDetailsActivity_Id", item.getGoodid());
                    startActivity(intent);
                }
            });
            GlideImage.loaderImage(item.getGoodcover(), (ImageView) helper.getView(R.id.item_nearby_pic));
            if (helper.getPosition()>1){
                helper.setVisible(R.id.item_nearby_iv1,false);
            }else{
                helper.setVisible(R.id.item_nearby_iv1,true);
            }
            if (helper.getPosition()%2==1){
                helper.setVisible(R.id.item_nearby_iv_you,false)
                        .setVisible(R.id.item_nearby_iv_zuo,true)
                        .setVisible(R.id.item_nearby_iv_zuo1,false)
                        .setVisible(R.id.item_nearby_iv_you1,true);
            }else{
                helper.setVisible(R.id.item_nearby_iv_you,true)
                        .setVisible(R.id.item_nearby_iv_zuo,false)
                        .setVisible(R.id.item_nearby_iv_zuo1,true)
                        .setVisible(R.id.item_nearby_iv_you1,false);
            }
            View view = helper.getView(R.id.item_nearby_pic);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = ScreenUtils.getScreenWidth() / 2 -30;
            view.setLayoutParams(layoutParams);
        }
    }
}
