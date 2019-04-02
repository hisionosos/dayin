package com.test.iview.dayin.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.AddressBean;
import com.test.iview.dayin.entity.BaseBean;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.test.iview.dayin.utils.DimensUtils.dp2px;


/**
 * Created by Administrator on 2018\5\2 0002.
 */

public class AddressActivity extends BaseActivity {
    @BindView(R.id.address_xrv)
    SwipeMenuListView addressXrv;
    @BindView(R.id.no)
    LinearLayout no;



    private AddressBean bean;
    private AppAdapter appAdapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
//        ToastUtils.showToast("左滑删除地址.");
        request();
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_address;
    }

    @Override
    protected void onResume() {
        super.onResume();

        request();
    }

    private void request() {

        OkGo.post(IURL.Address)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, AddressBean.class);
                        if (bean != null) {
                            if (bean.getList().size() == 0) {
                                no.setVisibility(View.VISIBLE);
                                addressXrv.setVisibility(View.GONE);
                            } else {
                                no.setVisibility(View.GONE);
                                addressXrv.setVisibility(View.VISIBLE);
                            }
                            if (bean.getStatus() == 0) {
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
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                // set item title font color
                deleteItem.setTitleColor(Color.WHITE);
                deleteItem.setWidth(dp2px(AddressActivity.this, 90));
                menu.addMenuItem(deleteItem);
            }
        };
        addressXrv.setMenuCreator(creator);
        addressXrv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, com.baoyz.swipemenulistview.SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        if (bean.getList().get(position).getType().equals("1")) {
                            ToastUtils.showToast("默认地址不能删除");
                        } else {
                            shanChu(bean.getList().get(position).getId());
                        }
                        break;
                }
                return false;
            }

        });
        addressXrv.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        appAdapter = new AppAdapter();
        addressXrv.setAdapter(appAdapter);
    }

    private void shanChu(String id) {

        OkGo.post(IURL.Address_Del)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .params("dress_id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        BaseBean baseBean = JsonUtil.parseJsonToBean(s, BaseBean.class);
                        if (baseBean != null) {
                            ToastUtils.showToast(baseBean.getMsg());
                            if (baseBean.getStatus() == 0) {
                                request();
                            }
                        } else {
                            ToastUtils.showToast("");
                        }

                    }
                });
    }

    private void moRen(String type, String id) {
        if (type.equals("0")) {

            OkGo.post(IURL.Address_Set)
                    .tag(this)
                    .params("user_id", MyApplication.userToken)
                    .params("dress_id", id)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            BaseBean baseBean = JsonUtil.parseJsonToBean(s, BaseBean.class);
                            if (baseBean != null) {
                                if (baseBean.getStatus() == 0) {
                                    request();
                                }
                                ToastUtils.showToast(baseBean.getMsg());
                            } else {
                                ToastUtils.showToast("");
                            }
                        }
                    });
        }
    }

    @OnClick({R.id.address_fanhui, R.id.address_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address_fanhui:
                finish();
                break;
            case R.id.address_add:
                startActivity(new Intent(this, AddAddressActivity.class));
                break;

        }
    }

    class AppAdapter extends BaseAdapter {

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount();
        }

        @Override
        public int getCount() {
            return bean.getList().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = View.inflate(AddressActivity.this, R.layout.item_address, null);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) view.findViewById(R.id.item_address_name);
                viewHolder.dizhi = (TextView) view.findViewById(R.id.item_address_dizhi);
                viewHolder.dizhi1 = (TextView) view.findViewById(R.id.item_address_dizhi1);
                viewHolder.moren = (TextView) view.findViewById(R.id.item_address_moren);
                viewHolder.pic = (ImageView) view.findViewById(R.id.item_address_moren_pic);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.name.setText(bean.getList().get(i).getName() + "    " + bean.getList().get(i).getTel());
            viewHolder.dizhi.setText(bean.getList().get(i).getSsq());
            viewHolder.dizhi1.setText(bean.getList().get(i).getDresscity());

            if (bean.getList().get(i).getType().equals("1")) {
                viewHolder.moren.setTextColor(getResources().getColor(R.color.main_tab_color));
                viewHolder.pic.setImageResource(R.drawable.gou);
            } else {
                viewHolder.moren.setTextColor(getResources().getColor(R.color.t99));
                viewHolder.pic.setImageResource(R.drawable.gou_no);
            }
            viewHolder.pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    moRen(bean.getList().get(i).getType(), bean.getList().get(i).getId());
                }
            });
            viewHolder.moren.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    moRen(bean.getList().get(i).getType(), bean.getList().get(i).getId());
                }
            });
            return view;
        }

    }

    /**
     * 存放控件
     */
    public final class ViewHolder {
        public TextView name;
        public TextView dizhi;
        public TextView dizhi1;
        public TextView moren;
        public ImageView pic;
    }



}
