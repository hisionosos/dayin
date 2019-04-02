package com.test.iview.dayin.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.PiaojuActivity;
import com.test.iview.dayin.utils.DimensUtils;
import com.test.iview.dayin.view.CustomPopWindow;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018\4\25 0025.
 */

@SuppressLint("ValidFragment")
public class HomeFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.item1)
    LinearLayout item1;
    @BindView(R.id.item2)
    LinearLayout item2;
    @BindView(R.id.item3)
    LinearLayout item3;
    @BindView(R.id.item4)
    LinearLayout item4;
    @BindView(R.id.back)
    ImageView back;
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_device, container, false);
    }

    @Override
    public void initData() {
        back.setVisibility(View.INVISIBLE);
        homeAdd.setImageResource(R.drawable.home_add);
    }


    @OnClick({R.id.home_add, R.id.item1, R.id.item2, R.id.item3, R.id.item4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_add:
                showPopBottom();
                break;
            case R.id.item1:

                break;
            case R.id.item2:
                startActivity(new Intent(getActivity(), PiaojuActivity.class));
                break;
            case R.id.item3:
                break;
            case R.id.item4:
                break;
        }
    }



    private void showPopBottom(){
        final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(R.layout.pop_layout1)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .create();

        popWindow.showAsDropDown(homeAdd, DimensUtils.dp2px(getActivity(),-90),10);

        LinearLayout add_device = popWindow.getmContentView().findViewById(R.id.add_device);
        LinearLayout history_device = popWindow.getmContentView().findViewById(R.id.history_device);

        add_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dissmiss();
            }
        });

        history_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dissmiss();

            }
        });
    }

}
