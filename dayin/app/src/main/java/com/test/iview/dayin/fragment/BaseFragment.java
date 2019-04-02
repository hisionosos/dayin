package com.test.iview.dayin.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.IllegalFormatFlagsException;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类
 * Created by zou on 2017/2/11.
 */

public abstract class BaseFragment extends Fragment {

    public Activity mActivity;
    public Unbinder unbinder;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        if (view == null) {
            throw new IllegalFormatFlagsException("View 不能为空");
        }
        initData();
        return view;
    }

    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    public abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
