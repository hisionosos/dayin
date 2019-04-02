package com.test.iview.dayin.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.test.iview.dayin.R;
import com.test.iview.dayin.entity.BaseBean;
import com.test.iview.dayin.entity.ChuJiaBean;
import com.test.iview.dayin.entity.CollectionBean;
import com.test.iview.dayin.entity.HomeLoopBean;
import com.test.iview.dayin.entity.JianLouBean;
import com.test.iview.dayin.entity.JianLouDetailsBean;
import com.test.iview.dayin.entity.JianLouWXBean;
import com.test.iview.dayin.entity.JianLouZFBBean;
import com.test.iview.dayin.entity.PayBean;
import com.test.iview.dayin.entity.XiangQingViewPager;
import com.test.iview.dayin.global.IURL;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.util.AppManager;
import com.test.iview.dayin.util.DiaLog;
import com.test.iview.dayin.util.GlideImage;
import com.test.iview.dayin.util.JsonUtil;
import com.test.iview.dayin.util.QqShare;
import com.test.iview.dayin.utils.SettingUtils;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.PayResult;
import com.test.iview.dayin.view.ScrollSpeedLinearLayoutManger;
import com.test.iview.dayin.view.WrapContentLinearLayoutManager;
import com.test.iview.dayin.view.XRecyclerView;
import com.test.iview.dayin.view.XuanFuScrollview;
import com.test.iview.dayin.wbapi.WBShareActivity;
import com.test.iview.dayin.wxapi.WeiXinUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class JianLouDetailsActivity extends BaseActivity {
    @BindView(R.id.xiangqing_vp)
    ViewPager xiangqingVp;
    @BindView(R.id.jianlou_rl)
    RelativeLayout jianlou_rl;
    @BindView(R.id.xiangqing_jianlou_xrv_rl)
    RelativeLayout xiangqing_jianlou_xrv_rl;
    @BindView(R.id.xiangqing_yuanjia)
    TextView xiangqingYuanjia;
    @BindView(R.id.xiangqing_renshu)
    TextView xiangqingRenshu;
    @BindView(R.id.xiangqing_name)
    TextView xiangqingName;
    @BindView(R.id.xiangqing_time)
    TextView xiangqingTime;
    @BindView(R.id.xiangqing_dianpu_pic)
    ImageView xiangqingDianpuPic;
    @BindView(R.id.xiangqing_zhengzai)
    TextView xiangqingZhengzai;
    @BindView(R.id.xiangqing_dianpu_name)
    TextView xiangqingDianpuName;
    @BindView(R.id.xiangqing_dianpu_quanbu)
    TextView xiangqingDianpuQuanbu;
    @BindView(R.id.xiangqing_dianpu_ziying)
    TextView xiangqingDianpuZiying;
    @BindView(R.id.xiangqing_lunbo_name)
    TextView xiangqing_lunbo_name;
    @BindView(R.id.xiangqing_lunbo_time)
    TextView xiangqing_lunbo_time;
    @BindView(R.id.xiangqing_lunbo_type)
    TextView xiangqing_lunbo_type;
    @BindView(R.id.xiangqing_dianpu_jianlou)
    TextView xiangqingDianpuJianlou;
    @BindView(R.id.xiangqing_num)
    TextView xiangqingNum;
    @BindView(R.id.xiangqing_jianlou)
    TextView xiangqing_jianlou;
    @BindView(R.id.xiangqing_pingjunjia)
    TextView xiangqingPingjunjia;
    @BindView(R.id.xiangqing_xrv)
    XRecyclerView xiangqingXrv;
    @BindView(R.id.xiangqing_fanhui)
    ImageView xiangqingFanhui;
    @BindView(R.id.xiangqing_lunbo_pic)
    ImageView xiangqing_lunbo_pic;
    @BindView(R.id.xiangqing_jianlou_xrv)
    XRecyclerView xiangqing_jianlou_xrv;
    @BindView(R.id.xiangqing_title_xrv)
    XRecyclerView xiangqing_title_xrv;
    @BindView(R.id.xiangqing_shoucang)
    ImageView xiangqingShoucang;
    @BindView(R.id.xiangqing_iv1)
    ImageView xiangqing_iv1;
    @BindView(R.id.xiangqing_iv2)
    ImageView xiangqing_iv2;
    @BindView(R.id.xiangqing__iv)
    ImageView xiangqing__iv;
    @BindView(R.id.xiangqing_title_iv1)
    ImageView xiangqing_title_iv1;
    @BindView(R.id.xiangqing_xiajia)
    ImageView xiangqing_xiajia;
    @BindView(R.id.xiangqing_share)
    ImageView xiangqingShare;
    @BindView(R.id.xiangqing_title)
    LinearLayout xiangqingTitle;
    @BindView(R.id.xiangqing_xfsv_ll)
    LinearLayout xiangqing_xfsv_ll;


    @BindView(R.id.xiangqing_lunbo_ll)
    LinearLayout xiangqing_lunbo_ll;
    @BindView(R.id.xiangqing_xfsv)
    XuanFuScrollview xiangqing_xfsv;
    @BindView(R.id.xiangqing_ll)
    LinearLayout xiangqingLl;
    @BindView(R.id.xiangqing_title_rl)
    RelativeLayout xiangqing_title_rl;
    private Context context1;
    private static final int SDK_PAY_FLAG = 1001;
    private MyAdapter myAdapter;
    private JianLouDetailsBean bean;
    private Bitmap unFocusIndicationStyle;
    private Bitmap focusIndicationStyle;
    private boolean isShouCang = false;
    private ChuJiaBean chuJiaBean;
    private GunDongAdaoter gunDongAdaoter;
    private int time = 5;
    private int height;
    private boolean isPay = true;
    private int timedao = 0;
    private MyAdapter1 myAdapter1;
    Handler daojishihandler = new Handler();
    Handler daojishihandler1 = new Handler();
    private PayBean payBean;
    private JianLouWXBean weiXinBean;
    private JianLouZFBBean zfbBean;
    private TextView tv;
    private boolean isFinish = true;
    private boolean isLogin = true;
    InputMethodManager imm; //用于点击搜索后隐藏软键盘
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra("ChongZhiActivity_Code", -1) == 0) {
                DiaLog.dismiss();
                OkGo.post(IURL.Pay_WeiXin_Pay)
                        .tag(this)
                        .params("user_id", MyApplication.userToken)
                        .params("order_id", weiXinBean.getOrder_id())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                payBean = JsonUtil.parseJsonToBean(s, PayBean.class);
                                if (payBean != null) {
                                    if (payBean.getStatus() == 0) {
                                        RelativeLayout layout = DiaLog.diaLog(dialog, context1, R.layout.dialog_dengdai);
                                        showDengDai(layout);
                                    } else {
                                        ToastUtils.showToast(payBean.getMsg());
                                    }
                                } else {
                                    ToastUtils.showToast("");
                                }
                                isPay = true;
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                isPay = true;
                            }
                        });
            } else if (intent.getIntExtra("ChongZhiActivity_Code", 0) == -1) {
                ToastUtils.showToast("支付失败");
                isPay = true;
            } else if (intent.getIntExtra("NetWorkErrorActivity", 0) == 999999) {
                finish();
            } else if (intent.getIntExtra("Login", 0) == 1) {
                isLogin = false;
                request();
            } else if (intent.getIntExtra("MainActivity", 0) == 999999) {
                jianLouBean = JsonUtil.parseJsonToBean(intent.getStringExtra("MainActivity1"), JianLouBean.class);
                if (jianLouBean != null) {
                    DiaLog.dismiss();
                    showCGSB(jianLouBean.getList().getType());
                } else {
                }
            } else {
                ToastUtils.showToast("取消支付");
                isPay = true;
            }
        }
    };

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        imm = (InputMethodManager) getSystemService(
                INPUT_METHOD_SERVICE);
        ImmersionBar.with(this).statusBarAlpha(0.3f).init();
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        context1 = this;
        request1();
        height = metric.widthPixels;     // 屏幕高度（像素）    ViewPager 高度与屏幕宽度一致
        initView();
        connectThread = new Thread(connectRunnable);
        connectThread.start();
        connectThread11 = new Thread(getConnectRunnable1);
        connectThread11.start();
        if (MyApplication.userToken != null) {
            isconnectBoolean = true;
        }
//        Utiles.TongjiStart(this,"商品"+getIntent().getStringExtra("JianLouDetailsActivity_Id"));
        JAnalyticsInterface.onPageStart(this, "商品" + getIntent().getStringExtra("JianLouDetailsActivity_Id"));
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_jianloudetails;
    }


    boolean isOne = true;

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        Log.e("soctet111", statusBarHeight1 + "");
        ViewGroup.LayoutParams layoutParams = xiangqing_title_iv1.getLayoutParams();
        layoutParams.height = statusBarHeight1;
        xiangqing_title_iv1.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams params = jianlou_rl.getLayoutParams();
        params.height = height;
        jianlou_rl.setLayoutParams(params);
        registerReceiver(broadcastReceiver, new IntentFilter("ChongZhiActivity"));
        unFocusIndicationStyle = drawCircle(50, R.color.white);
        focusIndicationStyle = drawCircle(50, R.color.colorPrimary);
        xiangqingVp.setOnPageChangeListener(new ImageCyclePageChangeListener());
        request();
    }

    private void request() {

        OkGo.post(IURL.JianLou_Details)
                .tag(this)
                .params("good_id", getIntent().getStringExtra("JianLouDetailsActivity_Id"))
                .params("user_id", MyApplication.userToken)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bean = JsonUtil.parseJsonToBean(s, JianLouDetailsBean.class);
                        if (bean != null) {
                            if (bean.getStatus() == 0) {
                                if (isLogin) {
                                    showView();
                                } else {
                                    isLogin = true;
                                }
                            } else {
                                ToastUtils.showToast(bean.getMsg());
                            }
                        } else {
                            ToastUtils.showToast("");
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                    }
                });

    }

    private void showView() {
        xiangqing_xfsv.setOnScrollListener(new QiangQingScroll());
        xiangqing_xfsv.setScrollViewListener(new QiangQingScroll());
        xiangqingPingjunjia.setText("￥" + bean.getInfo().getLowprice());
        if (bean.getInfo().getPic().size() != 0) {
            showBanner();
        }
        if (bean.getFavorite() == 1) {
            isShouCang = true;
            xiangqingShoucang.setImageResource(R.drawable.xiangqing_xing);
        } else {
            isShouCang = false;
            xiangqingShoucang.setImageResource(R.drawable.xiangqing_xingno);
        }
        xiangqingName.setText(bean.getInfo().getGoodtitle());
        SpannableString ss = new SpannableString("￥" + bean.getInfo().getGoodprice());
        ss.setSpan(new StrikethroughSpan(), 0, ss.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        xiangqingYuanjia.setText(ss);
        xiangqingRenshu.setText(bean.getInfo().getCountorders_txt());
        xiangqingTime.setText("卖家将在" + bean.getInfo().getWaittime() + "秒内处理您的出价!");
        if (bean.getInfo().getContentpic() != null) {
            List<String> contentpic = bean.getInfo().getContentpic();
            myAdapter = new MyAdapter(R.layout.item_tupianxiangqing, bean.getInfo().getContentpic());
            xiangqingXrv.setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            xiangqingXrv.setAdapter(myAdapter);
        }
        if (isOne) {
            if (bean.getQuality().size() != 0) {
                for (int j = 0; j < bean.getQuality().size(); j++) {
                    LinearLayout view = (LinearLayout) View.inflate(this, R.layout.item_jianlouxiangqing_title, null);
                    TextView name = (TextView) view.findViewById(R.id.item_jianluxaingqing_name);
//                    LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.item_jianluxaingqing_name1);
//                    ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
//                    layoutParams.width = height / 4;
//                    view.setLayoutParams(layoutParams);
                    name.setText(bean.getQuality().get(j).getTitle());
                    xiangqingLl.addView(view);
                }
            } else {
                xiangqingLl.setVisibility(View.GONE);
            }
            showLoop();
        }
        isOne = false;
        if (bean.getNum() == 0 || bean.getIs_lock() == 1) {
            xiangqing__iv.setVisibility(View.GONE);
            xiangqingZhengzai.setVisibility(View.GONE);
            xiangqing_jianlou_xrv_rl.setVisibility(View.GONE);
            if (bean.getIs_lock() == 1) {
                xiangqing_iv1.setVisibility(View.GONE);
                xiangqing_iv2.setVisibility(View.GONE);
                xiangqing_xiajia.setVisibility(View.VISIBLE);
                xiangqingTime.setText("该商品目前已下架,请捡漏其他商品");
                xiangqingTime.setTextColor(getResources().getColor(R.color.t99));
                xiangqing_jianlou.setBackgroundResource(R.color.t66);
            }
        }
        xiangqingZhengzai.setText(bean.getNum_txt());
        xiangqingDianpuName.setText(bean.getInfo().getShoptitle());
        GlideImage.loaderImage(bean.getInfo().getShoppic(), xiangqingDianpuPic);
        xiangqingDianpuQuanbu.setText(bean.getInfo().getAllgoods_txt());
        xiangqingDianpuJianlou.setText(bean.getInfo().getAllorders_txt());
        if (bean.getInfo().getShopself() == 0) {
            xiangqingDianpuZiying.setVisibility(View.GONE);
        } else {
            xiangqingDianpuZiying.setVisibility(View.VISIBLE);
        }
    }

    class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MyAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView imageView = helper.getView(R.id.item_tupianxiangqing_pic);
            imageView.setAdjustViewBounds(true);
            GlideImage.loaderImage(item, (ImageView) helper.getView(R.id.item_tupianxiangqing_pic));
        }
    }

//    /**
//     * 实现自动轮播
//     */
//    private Handler mHandler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            if (xiangqingVp != null) {
//                xiangqingVp.setCurrentItem(xiangqingVp.getCurrentItem() + 1);
//                mHandler.sendEmptyMessageDelayed(0, 5000);
//            }
//            return false;
//        }
//    });


    /**
     * 轮播图
     */
    private void showBanner() {
        xiangqingNum.setText(1 + "/" + bean.getInfo().getPic().size());
        List<ImageView> li = new ArrayList<>();
        for (int i = 0; i < bean.getInfo().getPic().size(); i++) {
            ImageView iv = new ImageView(MyApplication.getContext());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            GlideImage.loaderImage(bean.getInfo().getPic().get(i), iv);
            ImageView iv1 = new ImageView(MyApplication.getContext());
            iv1.setScaleType(ImageView.ScaleType.FIT_XY);
            li.add(iv);
        }
        if (bean.getInfo().getPic().size() == 1) {
            xiangqingVp.setCurrentItem(300);
            for (int i = 0; i < 3; i++) {
                ImageView iv = new ImageView(MyApplication.getContext());
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                GlideImage.loaderImage(bean.getInfo().getPic().get(0), iv);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                li.add(iv);
            }
        }
        if (bean.getInfo().getPic().size() == 2) {
            for (int i = 0; i < 2; i++) {
                ImageView iv = new ImageView(MyApplication.getContext());
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                GlideImage.loaderImage(bean.getInfo().getPic().get(i), iv);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                li.add(iv);
            }
        }
        XiangQingViewPager x = new XiangQingViewPager(li);
        xiangqingVp.setAdapter(x);

        for (int j = 0; j < li.size(); j++) {
            final int finalJ = j;
            li.get(j).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    private Bitmap drawCircle(int radius, int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(this.getResources().getColor(color));// 设置颜色
        Bitmap bitmap = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawCircle(radius / 2, radius / 2, radius / 2, paint);
        return bitmap;
    }


    private int preIndex = 0;

    private final class ImageCyclePageChangeListener implements ViewPager.OnPageChangeListener {
        //上次指示器指示的位置,开始为默认位置0

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            position = position % bean.getInfo().getPic().size();
            xiangqingNum.setText(position + 1 + "/" + bean.getInfo().getPic().size());
            preIndex = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private HomeLoopBean homeLoopBean;

    private void request1() {

        OkGo.post(IURL.Loop)
                .tag(this)
                .params("good_id", getIntent().getStringExtra("JianLouDetailsActivity_Id"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        homeLoopBean = JsonUtil.parseJsonToBean(s, HomeLoopBean.class);
                        if (homeLoopBean != null) {
                            if (homeLoopBean.getStatus() == 0) {
                                showLoop1();
                            } else {
                                ToastUtils.showToast(homeLoopBean.getMsg());
                            }
                        } else {
                            ToastUtils.showToast("");
                        }
                    }
                });
    }

    private void showLoop1() {
        myAdapter1 = new MyAdapter1(R.layout.item_loop, homeLoopBean.getList());
        xiangqing_title_xrv.setLayoutManager(new ScrollSpeedLinearLayoutManger(this));
        xiangqing_title_xrv.setAdapter(myAdapter1);
        timer1.schedule(task1, 1000, 5000);
    }

    private void showLoop() {
        gunDongAdaoter = new GunDongAdaoter(R.layout.item_jianlou_gundong, bean.getList());
        xiangqing_jianlou_xrv.setLayoutManager(new ScrollSpeedLinearLayoutManger(this));
        xiangqing_jianlou_xrv.setAdapter(gunDongAdaoter);
        timer.schedule(task, 1000, 3000);
    }

    private final Timer timer1 = new Timer();
    private TimerTask task1 = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            handle1r.sendMessage(message);
        }
    };
    int i2 = 0;
    Handler handle1r = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            // 要做的事情

            if (i2 == homeLoopBean.getList().size()) {
                xiangqing_title_xrv.scrollToPosition(0);
                i2 = 0;
            } else {
                xiangqing_title_xrv.smoothScrollToPosition(i2);
            }
            if (oldyy < xiangqingTitle.getHeight()) {
                xiangqing_lunbo_name.setText(homeLoopBean.getList().get(i2).getUser_name() + "  ");
                xiangqing_lunbo_time.setText(homeLoopBean.getList().get(i2).getTime() + "  ");
                xiangqing_lunbo_type.setText(homeLoopBean.getList().get(i2).getContent());
                GlideImage.loaderImage(homeLoopBean.getList().get(i2).getAvatar(), xiangqing_lunbo_pic);
                xiangqing_lunbo_ll.setAnimation(AnimationUtils.loadAnimation(JianLouDetailsActivity.this, R.anim.push_in));
                xiangqing_lunbo_ll.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        xiangqing_lunbo_ll.setAnimation(AnimationUtils.loadAnimation(JianLouDetailsActivity.this, R.anim.push_out));
                        xiangqing_lunbo_ll.setVisibility(View.GONE);
                        isLunBo = true;
                    }
                }, 3000);
            }
            i2++;
            super.handleMessage(msg);
        }
    };
    private Dialog dialog;

    private void jianLou() {
        if (SettingUtils.getInstance().getIsLogin()) {
            RelativeLayout layout = DiaLog.diaLog(dialog, context1, R.layout.dialog_chujia);
            initDialogView(layout);
        } else {

        }
    }

    @OnClick({R.id.xiangqing_jianlou, R.id.xiangqing_dianpu_jindian, R.id.xiangqing_lijijianlou, R.id.xiangqing_fanhui, R.id.xiangqing_iv, R.id.xiangqing_shoucang, R.id.xiangqing_share, R.id.xiangqing_title_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xiangqing_fanhui:
//                if (Integer.parseInt("s".toString() > Integer.parseInt("d")))
                finish();
                break;
            case R.id.xiangqing_dianpu_jindian:
                Intent intent = new Intent(this, StoreActivity.class);
                intent.putExtra("StoreActivity_ID", bean.getInfo().getShopid());
                startActivity(intent);
                break;
            case R.id.xiangqing_title_iv:
                break;
            case R.id.xiangqing_lijijianlou:

                jianLou();
                break;
            case R.id.xiangqing_iv:
                break;
            case R.id.xiangqing_jianlou:
                if (bean.getIs_lock() == 0) {
                    jianLou();
                }
                break;
            case R.id.xiangqing_shoucang:
                if (MyApplication.userToken != null) {

                    OkGo.post(IURL.Good_Collection)
                            .params("user_id", MyApplication.userToken)
                            .params("good_id", getIntent().getStringExtra("JianLouDetailsActivity_Id"))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    CollectionBean collectionBean = JsonUtil.parseJsonToBean(s, CollectionBean.class);
                                    if (collectionBean != null) {
                                        if (collectionBean.getStatus() == 0) {
                                            if (collectionBean.getStep() == 1) {
                                                isShouCang = false;
                                                if (isTitle) {
                                                    xiangqingShoucang.setImageResource(R.drawable.xiangqing_xingno);
                                                } else {
                                                    xiangqingShoucang.setImageResource(R.drawable.xiangqing_xing_no);
                                                }

                                            } else {
                                                if (isTitle) {
                                                    xiangqingShoucang.setImageResource(R.drawable.xiangqing_xing);

                                                } else {
                                                    xiangqingShoucang.setImageResource(R.drawable.xiangqing_xing_yes);
                                                }

                                                isShouCang = true;
                                            }
                                            ToastUtils.showToast(collectionBean.getMsg());
                                        } else {
                                            ToastUtils.showToast(collectionBean.getMsg());
                                        }
                                    } else {
                                        ToastUtils.showToast("");
                                    }
                                }
                            });
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.xiangqing_share:
                initDialogView();
//                if (isconnectBoolean == true) {
//                    try {
//                        //依次关闭输入流、输出流，socket连接
//                        socket.close();
//                    } catch (IOException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                    //中断连接线程
//                    connectThread.interrupt();
////                    Toast.makeText(MainActivity.this, "断开连接！", 2).show();
//                    isconnectBoolean = false;
//                }
                break;
        }
    }

    private void initDialogView() {
        RelativeLayout layout = DiaLog.diaLog(dialog, context1, R.layout.item_fenxiang);
        layout.findViewById(R.id.item_fenxiang_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
                WeiXinUtil.wangye(bean.getInfo().getGoodlink(), bean.getInfo().getSharetitle(), bean.getInfo().getSharedsc(), false, bean.getInfo().getSharpic());
            }
        });
        layout.findViewById(R.id.item_fenxiang_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.item_fenxiang_pengyouquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
                WeiXinUtil.wangye(bean.getInfo().getGoodlink(), bean.getInfo().getSharetitle(), bean.getInfo().getSharedsc(), true, bean.getInfo().getSharpic());
            }
        });
        layout.findViewById(R.id.item_fenxiang_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QqShare.qqShareToTuWen(JianLouDetailsActivity.this, bean.getInfo().getSharetitle(), bean.getInfo().getSharedsc(), bean.getInfo().getGoodlink(), bean.getInfo().getSharpic());
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.item_fenxiang_qqkongjian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QqShare.qzoneShareToTuWen(JianLouDetailsActivity.this, bean.getInfo().getSharetitle(), bean.getInfo().getSharedsc(), bean.getInfo().getGoodlink(), bean.getInfo().getSharpic());
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.item_fenxiang_weibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog.dismiss();
                Intent intent = new Intent(JianLouDetailsActivity.this, WBShareActivity.class);
                intent.putExtra("WBShareActivity_Title", "");
                intent.putExtra("WBShareActivity_Text", bean.getInfo().getSharetitle() + bean.getInfo().getSharedsc());
                intent.putExtra("WBShareActivity_Url", bean.getInfo().getGoodlink());
                startActivity(intent);
            }
        });

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            timedao++;
            if (tv != null) {
                tv1.setText(time + "");
            }
            //UI
            if (timedao >= payBean.getList().getWait_time()) {
                if (isFinish) {
                    DiaLog.dismiss();
                    showCGSB(payBean.getList().getType());
                }
            } else {
                daojishihandler.postDelayed(this, 1000);
            }
        }
    };
    private int isShouHuo = 0;
    TextView dialog_chenggong_see;
    ImageView dialog_chenggong_cha;

    private void showCGSB(int type) {
        if (type == 1) {//失败
            RelativeLayout layout = DiaLog.diaLog(dialog, context1, R.layout.dialog_shibai);
            layout.findViewById(R.id.dialog_shibai_cha).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DiaLog.dismiss();
                }
            });
            layout.findViewById(R.id.dialog_shibai_chujia).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DiaLog.dismiss();
                    RelativeLayout layout = DiaLog.diaLog(dialog, context1, R.layout.dialog_chujia);
                    initDialogView(layout);
                }
            });
        } else {//成功
            isShouHuo = bean.getDress();
            RelativeLayout layout = DiaLog.diaLog(dialog, context1, R.layout.dialog_chenggongi);
            TextView textView = (TextView) layout.findViewById(R.id.dialog_chenggong_money);
            dialog_chenggong_cha = (ImageView) layout.findViewById(R.id.dialog_chenggong_cha);
            dialog_chenggong_see = (TextView) layout.findViewById(R.id.dialog_chenggong_see);
            textView.setText(chuJiaBean.getSavemoney() + " 元");
            layout.findViewById(R.id.dialog_chenggong_cha).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DiaLog.dismiss();
                }
            });
            layout.findViewById(R.id.dialog_chenggong_see).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isShouHuo == 1) {
                        Intent intent = new Intent(JianLouDetailsActivity.this, OrderDataActivity.class);
                        intent.putExtra("OrderDataActivity_ID", payBean.getList().getOrder_id() + "");
                        startActivity(intent);
                        DiaLog.dismiss();
                    } else {
                        Intent intent = new Intent(JianLouDetailsActivity.this, AddAddressActivity.class);
                        intent.putExtra("AddAddressActivity_Id", payBean.getList().getOrder_id());
                        startActivityForResult(intent, 0);
                    }
                }
            });
            if (isShouHuo != 1) {
                dialog_chenggong_cha.setVisibility(View.GONE);
                dialog_chenggong_see.setText("完善收货地址");
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setMessage("您还没有收货地址.是否去添加地址?");
//                builder.setTitle("提示");
//                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                        startActivity(new Intent(JianLouDetailsActivity.this, AddAddressActivity.class));
//                    }
//                });
//                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
//                builder.create().show();
            } else {
                dialog_chenggong_cha.setVisibility(View.VISIBLE);
                dialog_chenggong_see.setText("查看订单");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            isShouHuo = data.getIntExtra("ChengGong_Id", 0);
            if (isShouHuo != 1) {
                dialog_chenggong_see.setText("完善收货地址");
                dialog_chenggong_cha.setVisibility(View.GONE);
            } else {
                dialog_chenggong_see.setText("查看订单");
                dialog_chenggong_cha.setVisibility(View.VISIBLE);
            }
        }
    }


    private void initDialogView(RelativeLayout layout) {
        final EditText editText = (EditText) layout.findViewById(R.id.dialog_chujia_et);
        final TextView textView = (TextView) layout.findViewById(R.id.dialog_chujia_money);
        final TextView textView1 = (TextView) layout.findViewById(R.id.dialog_chujia_money1);
        textView.setText(" " + bean.getInfo().getSavemoney() + "元");
        textView1.setText(" " + bean.getInfo().getAvgorders() + "元");
        final ImageView cha1 = (ImageView) layout.findViewById(R.id.dialog_chujia_cha);
        final ImageView cha2 = (ImageView) layout.findViewById(R.id.dialog_chujia_cha1);
        final TextView payMoney = (TextView) layout.findViewById(R.id.dialog_zhifu_money);
        final TextView yue = (TextView) layout.findViewById(R.id.dialog_zhifu_yue);
        final TextView jiesheng = (TextView) layout.findViewById(R.id.dialog_zhifu_jiesheng);
        final LinearLayout ll = (LinearLayout) layout.findViewById(R.id.dialog_chujia_ll);
        final LinearLayout ll1 = (LinearLayout) layout.findViewById(R.id.dialog_chujia_ll1);
        layout.findViewById(R.id.dialog_chujia_cha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);  //隐藏软键盘
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.dialog_chujia_cha1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);  //隐藏软键盘
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.dialog_chujia_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);  //隐藏软键盘
                DiaLog.dismiss();
            }
        });
        layout.findViewById(R.id.dialog_chujia_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        layout.findViewById(R.id.dialog_chujia_ll1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        layout.findViewById(R.id.dialog_chujia_chujia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!"".equals(editText.getText().toString())) {

                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);  //隐藏软键盘

                    OkGo.post(IURL.ChuJia)
                            .tag(this)
                            .params("user_id", MyApplication.userToken)
                            .params("good_id", getIntent().getStringExtra("JianLouDetailsActivity_Id"))
                            .params("order_price", editText.getText().toString())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    chuJiaBean = JsonUtil.parseJsonToBean(s, ChuJiaBean.class);
                                    if (chuJiaBean != null) {
                                        if (chuJiaBean.getStatus() == 0) {
                                            cha1.setVisibility(View.GONE);
                                            cha2.setVisibility(View.VISIBLE);
                                            ll.setVisibility(View.GONE);
                                            ll1.setVisibility(View.VISIBLE);
                                            payMoney.setText(editText.getText().toString() + "元");
                                            jiesheng.setText("为您节省 " + chuJiaBean.getSavemoney() + " 元");
                                            yue.setText(chuJiaBean.getMoney() + "元");
                                        } else {
                                            ToastUtils.showToast(chuJiaBean.getMsg());
                                        }
                                    } else {
                                        ToastUtils.showToast("");
                                    }

                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    ToastUtils.showToast("服务器错误");

                                }
                            });
                } else {
                    ToastUtils.showToast("请输入金额");
                }
            }
        });
        layout.findViewById(R.id.dialog_chujia_zhineng).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OkGo.post(IURL.JianLou_Price)
                        .tag(this)
                        .params("good_id", getIntent().getStringExtra("JianLouDetailsActivity_Id"))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                BaseBean baseBean = JsonUtil.parseJsonToBean(s, BaseBean.class);
                                if (baseBean != null) {
                                    if (baseBean.getStatus() == 0) {
                                        editText.setText(baseBean.getJianlou_price() + "");
                                        String s1 = baseBean.getJianlou_price() + "";
                                        editText.setSelection(s1.length());
                                    } else {
                                        editText.setText(bean.getInfo().getAvgorders());
                                        editText.setSelection(bean.getInfo().getAvgorders().length());
                                    }
                                } else {
                                    editText.setText(bean.getInfo().getAvgorders());
                                    editText.setSelection(bean.getInfo().getAvgorders().length());
                                }

                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                editText.setText(bean.getInfo().getAvgorders());
                                editText.setSelection(bean.getInfo().getAvgorders().length());

                            }
                        });

            }
        });

        layout.findViewById(R.id.dialog_zhifu_qianbao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPay) {

                    isPay = false;
                    OkGo.post(IURL.Pay_QianBao)
                            .tag(this)
                            .params("user_id", MyApplication.userToken)
                            .params("good_id", getIntent().getStringExtra("JianLouDetailsActivity_Id"))
                            .params("order_price", editText.getText().toString())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    payBean = JsonUtil.parseJsonToBean(s, PayBean.class);
                                    if (payBean != null) {
                                        if (payBean.getStatus() == 0) {
                                            if (payBean.getList().getType() == 3) {
                                                ToastUtils.showToast(payBean.getMsg());
                                            } else {
                                                DiaLog.dismiss();
                                                RelativeLayout layout = DiaLog.diaLog(dialog, context1, R.layout.dialog_dengdai);
                                                showDengDai(layout);
                                            }
                                        } else {
                                            ToastUtils.showToast(payBean.getMsg());
                                        }
                                    } else {
                                        ToastUtils.showToast("");
                                    }

                                    isPay = true;
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    ToastUtils.showToast("");
                                    isPay = true;

                                }
                            });
                }
            }
        });
        layout.findViewById(R.id.dialog_zhifu_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPay) {
                    isPay = false;

                    OkGo.post(IURL.Pay_WeiXin)
                            .tag(this)
                            .params("user_id", MyApplication.userToken)
                            .params("good_id", getIntent().getStringExtra("JianLouDetailsActivity_Id"))
                            .params("order_price", editText.getText().toString())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    weiXinBean = JsonUtil.parseJsonToBean(s, JianLouWXBean.class);
                                    if (weiXinBean != null) {
                                        if (weiXinBean.getStatus() == 0) {
                                            weiXinPay();
                                        } else {
                                            ToastUtils.showToast(bean.getMsg());
                                            isPay = true;
                                        }
                                    } else {
                                        ToastUtils.showToast("");
                                        isPay = true;
                                    }

                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    ToastUtils.showToast("");

                                    isPay = true;
                                }
                            });
                }
            }
        });
        layout.findViewById(R.id.dialog_zhifu_zhifubao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPay) {
                    isPay = false;

                    OkGo.post(IURL.Pay_ZhiFuBao)
                            .tag(this)
                            .params("user_id", MyApplication.userToken)
                            .params("good_id", getIntent().getStringExtra("JianLouDetailsActivity_Id"))
                            .params("order_price", editText.getText().toString())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    zfbBean = JsonUtil.parseJsonToBean(s, JianLouZFBBean.class);
                                    if (zfbBean != null) {
                                        if (zfbBean.getStatus() == 0) {
                                            zhiFuBaoPay(zfbBean.getBody());
                                        } else {
                                            ToastUtils.showToast(bean.getMsg());
                                            isPay = true;
                                        }
                                    } else {
                                        ToastUtils.showToast("");
                                        isPay = true;
                                    }

                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    ToastUtils.showToast("");

                                    isPay = true;
                                }
                            });
                }
            }
        });

    }

    private void zhiFuBaoPay(final String data) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(JianLouDetailsActivity.this);
                // 调用支付接口，获取支付结果
                Map<String, String> result = alipay.payV2(data, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandlerzhifubao.sendMessage(msg);
            }
        };

// 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private Handler mHandlerzhifubao = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    xiangqingName.setText(resultStatus);
                    if (TextUtils.equals(resultStatus, "9000")) {
                        OkGo.post(IURL.Pay_ZhiFuBao_Pay)
                                .tag(this)
                                .params("user_id", MyApplication.userToken)
                                .params("order_id", zfbBean.getOrder_id())
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        payBean = JsonUtil.parseJsonToBean(s, PayBean.class);
                                        if (payBean != null) {
                                            if (payBean.getStatus() == 0) {
                                                DiaLog.dismiss();
                                                RelativeLayout layout = DiaLog.diaLog(dialog, context1, R.layout.dialog_dengdai);
                                                showDengDai(layout);

                                            } else {
                                                ToastUtils.showToast(payBean.getMsg());
                                            }
                                        } else {
                                            ToastUtils.showToast("");
                                        }
                                        isPay = true;
                                    }

                                    @Override
                                    public void onError(Call call, Response response, Exception e) {
                                        super.onError(call, response, e);
                                        isPay = true;
                                    }
                                });
                    } else {
                        isPay = true;
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUtils.showToast("支付结果确认中");
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            ToastUtils.showToast("支付失败");
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };


    private void weiXinPay() {
        PayReq req = new PayReq();
        req.appId = weiXinBean.getAppid();
        req.partnerId = weiXinBean.getPartnerid();
        req.prepayId = weiXinBean.getPrepayid();
        req.packageValue = weiXinBean.getPackageX();
        req.nonceStr = weiXinBean.getNoncestr();
        req.timeStamp = weiXinBean.getTimestamp() + "";
        req.sign = weiXinBean.getSign();
//        MyApplication.mWxApi.sendReq(req);
    }

    private TextView tv1;

    private void showDengDai(RelativeLayout layout) {
        tv = (TextView) layout.findViewById(R.id.dialog_dengdai_tv);
        tv1 = (TextView) layout.findViewById(R.id.dialog_dengdai_tv1);
        layout.findViewById(R.id.dialog_dengdai_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        layout.findViewById(R.id.dialog_dengdai_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        if (payBean.getList().getType() != 2) {
            timedao = 0;
            time = bean.getInfo().getWaittime();
            tv.setText("卖家将在" + time + "秒内处理您的出价！");
            tv1.setText(time + "");
            daojishihandler.postDelayed(runnable, 1000);
        } else {//长连接
            isDao = true;
            time = payBean.getList().getWait_time();
            tv.setText("卖家将在" + time + "秒内处理您的出价！");
            tv1.setText(time + "");
            daojishihandler1.postDelayed(runnable1, 1000);
        }
    }

    Boolean isconnectBoolean = false;//初始化socket连接值为未连接
    private Thread connectThread;//声明连接服务器线程
    private Thread connectThread11;//声明连接服务器线程
    private BufferedReader bufferedReader;//声明输入流对象
    private OutputStream outputStream;//声明输出流对象
    private String host = "111.230.186.215";
    private int port = 2347;
    private Socket socket;//声明socket对象
    private boolean flage = true;
    int i3 = 0;
    Runnable getConnectRunnable1 = new Runnable() {
        @Override
        public void run() {
            //接收数据可用子线程也可直接在此线程操作
            char[] buffer = new char[2560];//定义数组接收输入流数据
            String bufferString = "";//定义一个字符接收数组数据
            int conut = 0;//初始化buffer数组长度为0
            int tag = 0;//初识写入数组的位置

            //死循环重复接收输入流数据并进行处理
            while (flage) {
                //当输入流写入buffer数组的长度大于0时   即 接收到数据时
                try {
                    if (bufferedReader != null) {
                        while ((conut = bufferedReader.read(buffer)) > 0) {
                            //将buffer数组的数据全部写入bufferString字符类型
                            while (tag < buffer.length) {
                                bufferString = bufferString + buffer[tag];
                                tag++;
                            }
                            //bufferedReader.close();
                            //将数据给messageHandler刷新UI界面
                            Log.e("socket", bufferString.toString().substring(0, conut));
                            Message msgMessage = new Message();
                            msgMessage.obj = bufferString.toString().substring(0, conut);
                            msgMessage.what = 1;
                            messageHandler.sendMessage(msgMessage);
                            //初始化数据，以便处理下一条输入流信息
                            tag = 0;
                            bufferString = "";
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    Runnable connectRunnable1 = new Runnable() {
        @Override
        public void run() {
            try {
                outputStream.write((MyApplication.userToken).
                        getBytes("utf-8"));
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable connectRunnable = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub

            try {
                //新建socket对象，填入编辑框IP,端口号
                socket = new Socket();
                SocketAddress socAddress = new InetSocketAddress(IURL.host, IURL.port);
                socket.connect(socAddress, 5000);
                //取得输入流、输出流
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
                //输出流发送至服务器
                outputStream = socket.getOutputStream();
                while (flage) {
                    Thread.sleep(3000);
                    if (MyApplication.userToken != null) {
                        outputStream.write((MyApplication.userToken).
                                getBytes("utf-8"));
                        //输出流发送至服务器
                        outputStream.flush();
                    }
                }
//                //接收数据可用子线程也可直接在此线程操作
//                char[] buffer = new char[256];//定义数组接收输入流数据
//                String bufferString = "";//定义一个字符接收数组数据
//                int conut = 0;//初始化buffer数组长度为0
//                int tag = 0;//初识写入数组的位置
//
//                //死循环重复接收输入流数据并进行处理
//                while (true) {
//                    //当输入流写入buffer数组的长度大于0时   即 接收到数据时
//                    while ((conut = bufferedReader.read(buffer)) > 0) {
//                        //将buffer数组的数据全部写入bufferString字符类型
//                        while (tag < buffer.length) {
//                            bufferString = bufferString + buffer[tag];
//                            tag++;
//                        }
////                        bufferedReader.close();
//                        //将数据给messageHandler刷新UI界面
//                        Log.e("socket", bufferString.toString().substring(0, conut));
//                        Message msgMessage = new Message();
//                        msgMessage.obj = bufferString.toString().substring(0, conut);
//                        msgMessage.what = 1;
//                        messageHandler.sendMessage(msgMessage);
//                        //初始化数据，以便处理下一条输入流信息
//                        tag = 0;
//                        bufferString = "";
//                    }
//                }
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                connectThread = null;
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                socket = null;
                outputStream = null;
                bufferedReader = null;
                connectThread = new Thread(connectRunnable);
                connectThread.start();
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    };
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            time--;
            if (tv != null) {
                tv1.setText(time + "");
            }
            //UI
            if (time > 0) {
                if (isDao) {
                    daojishihandler1.postDelayed(this, 1000);
                }
            } else {
                if (isChaoShi) {
                    chaoshi();
                } else {
                    isChaoShi = true;
                }
            }
        }
    };

    private void chaoshi() {
        OkGo.post(IURL.ChaoShi)
                .tag(this)
                .params("user_id", MyApplication.userToken)
                .params("order_id", payBean.getList().getOrder_id())
                .params("type", 1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        BaseBean baseBean = JsonUtil.parseJsonToBean(s, BaseBean.class);
                        if (baseBean != null) {
                            if (baseBean.getStatus() == 0) {
                                if (baseBean.getType() == 0) {
                                    DiaLog.dismiss();
                                    showCGSB(0);
                                } else {
                                    DiaLog.dismiss();
                                    showCGSB(1);
                                }
                            } else {
                                ToastUtils.showToast(baseBean.getMsg());
                            }
                        } else {
                            DiaLog.dismiss();
                            showCGSB(1);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        DiaLog.dismiss();
                        showCGSB(1);
                    }
                });
    }

    private boolean isChaoShi = true;
    HomeLoopBean.ListBean listBean;
    private boolean isDao = true;
    private JianLouBean jianLouBean;
    private boolean isLunBo = true;
    @SuppressLint("HandlerLeak")
    Handler messageHandler = new Handler() {
        public void handleMessage(android.os.Message msgMessage) {
            switch (msgMessage.what) {
                case 1:
                    if (msgMessage.obj.toString().equals("success")) {
                    } else {
                        isChaoShi = false;
                        BaseBean baseBean = JsonUtil.parseJsonToBean(msgMessage.obj.toString(), BaseBean.class);
                        if (baseBean != null) {
                            if (baseBean.getStatus() == 0) { //捡漏通知
                                isDao = false;
                                JianLouDetailsActivity.this.jianLouBean = JsonUtil.parseJsonToBean(msgMessage.obj.toString(), JianLouBean.class);
                                if (JianLouDetailsActivity.this.jianLouBean != null) {
                                    DiaLog.dismiss();
                                    showCGSB(JianLouDetailsActivity.this.jianLouBean.getList().getType());
                                } else {

                                }
                            } else {  // 轮播 通知
                                listBean = JsonUtil.parseJsonToBean(msgMessage.obj.toString(), HomeLoopBean.ListBean.class);
                                if (listBean != null) {
                                    myAdapter1.addData(i2, listBean);
                                }
                            }
                        } else {
                            String substring = msgMessage.obj.toString().substring(msgMessage.obj.toString().indexOf("}") + 1);
                            JianLouDetailsActivity.this.jianLouBean = JsonUtil.parseJsonToBean(substring, JianLouBean.class);
                            isDao = false;
                            if (JianLouDetailsActivity.this.jianLouBean != null) {
                                DiaLog.dismiss();
                                showCGSB(JianLouDetailsActivity.this.jianLouBean.getList().getType());
                            } else {
                                String substring1 = msgMessage.obj.toString().substring(0, msgMessage.obj.toString().indexOf("}{") + 1);
                                JianLouDetailsActivity.this.jianLouBean = JsonUtil.parseJsonToBean(substring, JianLouBean.class);
                                if (JianLouDetailsActivity.this.jianLouBean != null) {
                                    DiaLog.dismiss();
                                    showCGSB(JianLouDetailsActivity.this.jianLouBean.getList().getType());
                                } else {
                                    ToastUtils.showToast("解析失败");
                                }
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private final Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            // 要做的事情
            if (i == bean.getList().size()) {
                xiangqing_jianlou_xrv.scrollToPosition(0);
                i = 0;
            } else {
                xiangqing_jianlou_xrv.smoothScrollToPosition(i);
                i++;
            }
            super.handleMessage(msg);
        }
    };
    private int i = 0;

    class GunDongAdaoter extends BaseQuickAdapter<JianLouDetailsBean.ListBean, BaseViewHolder> {

        public GunDongAdaoter(int layoutResId, @Nullable List<JianLouDetailsBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, JianLouDetailsBean.ListBean item) {
            helper.setText(R.id.xiangqing_name1, item.getUser_name())
                    .setText(R.id.xiangqing_chujia, item.getTime() + "  出价" + item.getOrder_price() + "元");
            GlideImage.loaderImage(item.getAvatar(), (ImageView) helper.getView(R.id.xiangqing_pic));
        }
    }

    private int oldyy = 0;
    private boolean isTitle = true;
    private boolean isHei = true;

    class QiangQingScroll implements XuanFuScrollview.OnScrollListener, XuanFuScrollview.ScrollViewListener {

        @Override
        public void onScroll(int scrollY) {

        }

        @Override
        public void onScrollChanged(XuanFuScrollview scrollView, int x, int y, int oldx, int oldy) {
            oldyy = y;
            if (y <= 0) {   //设置标题的背景颜色
                xiangqingTitle.setBackgroundResource(R.color.transparent);
                isTitle = true;
                xiangqingShoucang.setBackgroundResource(R.drawable.button_heiseding);
                xiangqingShare.setBackgroundResource(R.drawable.button_heiseding);
                xiangqingFanhui.setBackgroundResource(R.drawable.button_heiseding);
                xiangqingFanhui.setImageResource(R.drawable.xiangqing_fanhui);
                if (isShouCang) {
                    xiangqingShoucang.setImageResource(R.drawable.xiangqing_xing);
                } else {
                    xiangqingShoucang.setImageResource(R.drawable.xiangqing_xingno);
                }
                xiangqingShare.setImageResource(R.drawable.xiangqing_share_no);
                xiangqing_title_rl.setVisibility(View.INVISIBLE);
                ImmersionBar.with(JianLouDetailsActivity.this).statusBarAlpha(0.3f).init();
                isHei = true;
//                xiangqingFanhui.setBackgroundResource(R.drawable.button_heiseding);
            } else if (y > 0 && y <= xiangqingTitle.getHeight()) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                float scale = (float) y / xiangqingTitle.getHeight();
                float alpha = (255 * scale);
                if (alpha / 255 < 0.3) {
                    ImmersionBar.with(JianLouDetailsActivity.this).statusBarAlpha(0.3f).init();
                } else {
                    ImmersionBar.with(JianLouDetailsActivity.this).statusBarAlpha(alpha / 255).init();
                }
//                xiangqingTitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
//                xiangqingFanhui.setImageResource(R.drawable.xiangqing_fanhui);
//                xiangqingShoucang.setBackgroundResource(R.drawable.button_heiseding);
//                xiangqing_title_iv.setBackgroundResource(R.drawable.button_heiseding3);
//                xiangqingShare.setBackgroundResource(R.drawable.button_heiseding);
//                xiangqingFanhui.setBackgroundResource(R.drawable.button_heiseding);
//                if (isShouCang) {
//                    xiangqingShoucang.setImageResource(R.drawable.xiangqing_xing);
//                } else {
//                    xiangqingShoucang.setImageResource(R.drawable.xiangqing_xingno);
//                }
//                xiangqingShare.setImageResource(R.drawable.xiangqing_share_no);
////                mTitle.setTextColor(Color.argb((int) alpha, 000, 155, 244));
//                isTitle = true;
//                xiangqing_lunbo_ll.setVisibility(View.VISIBLE);
                xiangqingTitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                isHei = true;
            } else {    //滑动到banner下面设置普通颜色
                if (isHei) {
                    ImmersionBar.with(JianLouDetailsActivity.this).statusBarAlpha(1).init();
                    isHei = false;
                }
                xiangqing_title_rl.setVisibility(View.VISIBLE);
                xiangqingShoucang.setBackgroundResource(0);
                xiangqingShare.setBackgroundResource(0);
                xiangqingFanhui.setBackgroundResource(0);
                xiangqingFanhui.setImageResource(R.drawable.login_fanhui);
                if (isShouCang) {
                    xiangqingShoucang.setImageResource(R.drawable.xiangqing_xing_yes);
                } else {
                    xiangqingShoucang.setImageResource(R.drawable.xiangqing_xing_no);
                }
                xiangqingShare.setImageResource(R.drawable.xiangqing_share);
                xiangqingTitle.setBackgroundColor(Color.argb(255, 255, 255, 255));
                isTitle = false;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        timer.cancel();
        isFinish = false;
        task.cancel();
        JAnalyticsInterface.onPageEnd(this, "商品" + getIntent().getStringExtra("JianLouDetailsActivity_Id"));
//        Utiles.TongjiEnd(this,"商品"+getIntent().getStringExtra("JianLouDetailsActivity_Id"));
        task1.cancel();
//        connectThread11.stop();
//        connectThread.stop();
//        connectRunnable.
        ImmersionBar.with(this).destroy();
        flage = false;
        connectThread.interrupt();
        connectThread11.interrupt();
        connectThread = null;
        connectThread11 = null;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null;
        }
        if (socket != null) {
            try {
                socket.shutdownInput();
                socket.shutdownOutput();
                bufferedReader.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    class MyAdapter1 extends BaseQuickAdapter<HomeLoopBean.ListBean, BaseViewHolder> {

        public MyAdapter1(int layoutResId, @Nullable List<HomeLoopBean.ListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeLoopBean.ListBean item) {
            helper.setText(R.id.item_loop_content, item.getContent())
                    .setText(R.id.item_loop_name, item.getUser_name())
                    .setText(R.id.item_loop_time, item.getTime());
            GlideImage.loaderImage(item.getAvatar(), (ImageView) helper.getView(R.id.item_loop_pic));
        }
    }

}
