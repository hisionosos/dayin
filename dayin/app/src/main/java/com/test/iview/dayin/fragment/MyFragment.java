package com.test.iview.dayin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.InstallActivity;
import com.test.iview.dayin.activity.MyInfosActivity;
import com.test.iview.dayin.entity.MyBean;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.utils.SettingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\4\25 0025.
 */

public class MyFragment extends BaseFragment {

    @BindView(R.id.person_info)
    RelativeLayout personInfo;
    @BindView(R.id.item1)
    RelativeLayout item1;
    @BindView(R.id.item2)
    RelativeLayout item2;
    @BindView(R.id.item3)
    RelativeLayout item3;
    @BindView(R.id.item4)
    RelativeLayout item4;
    private MyBean myBean;

    public MyFragment() {
    }


    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(MyApplication.getContext(), R.layout.fagment_my, null);
        return view;
    }

    @Override
    public void initData() {

    }



    @OnClick({R.id.person_info, R.id.item1, R.id.item2, R.id.item3, R.id.item4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_info:
                startActivity(new Intent(getActivity(), MyInfosActivity.class));
                break;
            case R.id.item1:
                break;
            case R.id.item2:
                break;
            case R.id.item3:
                break;
            case R.id.item4:
                break;
        }
    }
}
