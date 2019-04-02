package com.test.iview.dayin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.PostRequest;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.BaseBean;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\5\2 0002.
 */

public class AddAddressActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.addaddress_name)
    EditText addaddressName;
    @BindView(R.id.addaddress_phone)
    EditText addaddressPhone;
    @BindView(R.id.addaddress_diqv)
    TextView addaddressDiqv;
    @BindView(R.id.addaddress_dizhi)
    EditText addaddressDizhi;


    private String diqv = "";
    private CityPickerView mpicker = new CityPickerView();


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mpicker.init(this);
        title.setText("新增收货地址");
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_addaddress;
    }


    @OnClick({R.id.fanhui, R.id.addaddress_diqv, R.id.addaddress_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                Intent intent = new Intent();
                intent.putExtra("ChengGong_Id",0);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.addaddress_diqv:
                showdialog();
                break;
            case R.id.addaddress_add:

                                final int addAddressActivity_id = getIntent().getIntExtra("AddAddressActivity_Id", 0);
                                PostRequest post = OkGo.post(IURL.Address_Add)
                                        .tag(this);
                                if (addAddressActivity_id!=0){
                                    post.params("order_id", addAddressActivity_id);
                                }
                post.params("user_id", MyApplication.userToken)
                                        .params("user_name",addaddressName.getText().toString())
                                        .params("user_utel",addaddressPhone.getText().toString())
                                        .params("dress_all",diqv)
                                        .params("dress_udress",addaddressDizhi.getText().toString())
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onSuccess(String s, Call call, Response response) {
                                                BaseBean baseBean = JsonUtil.parseJsonToBean(s,BaseBean.class);
                                                if (baseBean!=null){
                                                    ToastUtils.showToast(baseBean.getMsg());
                                                    if (baseBean.getStatus()==0){
                                                        if (addAddressActivity_id!=0){
                                                            Intent intent = new Intent();
                                                            intent.putExtra("ChengGong_Id",1);
                                                            setResult(RESULT_OK, intent);
                                                        }
                                                        finish();
                                                    }
                                                }else{
                                                    ToastUtils.showToast("");
                                                }

                                            }

                                        });
                break;
        }
    }

    private void showdialog() {
        CityConfig cityConfig = new CityConfig.Builder()
                .title("选择城市")//标题
                .titleTextSize(18)//标题文字大小
                .titleTextColor("#585858")//标题文字颜  色
                .titleBackgroundColor("#E9E9E9")//标题栏背景色
                .confirTextColor("#ce4031")//确认按钮文字颜色
                .confirmText("确定")//确认按钮文字
                .confirmTextSize(16)//确认按钮文字大小
                .cancelTextColor("#585858")//取消按钮文字颜色
                .cancelText("取消")//取消按钮文字
                .cancelTextSize(16)//取消按钮文字大小
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                .showBackground(true)//是否显示半透明背景
                .visibleItemsCount(7)//显示item的数量
                .province("北京")//默认显示的省份
                .city("北京")//默认显示省份下面的城市
                .district("东城区")//默认显示省市下面的区县数据
                .provinceCyclic(false)//省份滚轮是否可以循环滚动
                .cityCyclic(false)//城市滚轮是否可以循环滚动
                .districtCyclic(false)//区县滚轮是否循环滚动
                .drawShadows(true)//滚轮不显示模糊效果
                .setLineColor("#03a9f4")//中间横线的颜色
                .setLineHeigh(5)//中间横线的高度
                .setShowGAT(false)//是否显示港澳台数据，默认不显示
                .build();
        mpicker.setConfig(cityConfig);
        mpicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                super.onSelected(province, city, district);
                diqv = province.getName()+" "+city.getName()+" "+district.getName();
                addaddressDiqv.setText(diqv);
            }

            @Override
            public void onCancel() {    //取消
                super.onCancel();
            }
        });
        mpicker.showCityPicker();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent();
        intent.setAction("ChongZhiActivity");
        intent.putExtra("Login", 1);
        sendBroadcast(intent);
        super.onDestroy();
    }


}
