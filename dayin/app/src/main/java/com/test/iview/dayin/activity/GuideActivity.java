package com.test.iview.dayin.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.global.MyApplication;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018\5\25 0025.
 */

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    ViewPager ruideVp;
    TextView ruideTiyan;
    private static final int[] pics = {R.drawable.splash_img2, R.drawable.splash_img2, R.drawable.splash_img2,R.drawable.splash_img2};
    private Bitmap focusIndicationStyle;
    private Bitmap unFocusIndicationStyle;
    private int preIndex = 0;
    private LinearLayout ll;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        ruideVp = (ViewPager) findViewById(R.id.ruide_vp);
        ruideTiyan = (TextView) findViewById(R.id.ruide_tiyan);
        ll = (LinearLayout) findViewById(R.id.ruide_ll);
        ruideTiyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = getSharedPreferences("guide", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("guide", true);
                editor.commit();
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });
        List<View> views = new ArrayList<>();
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        //初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            Bitmap bitmap = readBitMap(this, pics[i]);
            iv.setImageBitmap(bitmap);
            views.add(iv);
        }
        unFocusIndicationStyle = drawCircle(50, R.color.white);
        focusIndicationStyle = drawCircle(50, R.color.main_tab_color);
        initIndication();
        ruideVp.setAdapter(new ViewPagerAdapter(views));
        ruideVp.setOnPageChangeListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayout() {
        return R.layout.act_guide;
    }


    private Bitmap drawCircle(int radius, int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(this.getResources().getColor(color));// 设置颜色
        Bitmap bitmap = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawCircle(radius / 2, radius / 2, radius / 2, paint);
        return bitmap;
    }
    private void initIndication() {
        ll.removeAllViews();
        for (int i = 0; i < pics.length; i++) {
            ImageView imageView = new ImageView(MyApplication.getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ll.getLayoutParams().height, LinearLayout.LayoutParams.MATCH_PARENT);
            if (i != 0) {
                params.leftMargin = (int) (ll.getLayoutParams().height * 2);
            }
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(params);
            if (i == 0) {
                imageView.setImageBitmap(focusIndicationStyle);
            } else {
                imageView.setImageBitmap(unFocusIndicationStyle);
            }
            ll.addView(imageView);
        }
//        ((ImageView) (ll.getChildAt(0))).setImageBitmap(focusIndicationStyle);
    }

    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
// 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (i == pics.length - 1) {
            ruideTiyan.setVisibility(View.VISIBLE);
        } else {
            ruideTiyan.setVisibility(View.GONE);
        }
        i = i % pics.length;
        ((ImageView) (ll.getChildAt(preIndex))).setImageBitmap(unFocusIndicationStyle);
        ((ImageView) (ll.getChildAt(i))).setImageBitmap(focusIndicationStyle);
        preIndex = i;
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public class ViewPagerAdapter extends PagerAdapter {
        private List<View> views;

        public ViewPagerAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

        //初始化arg1位置的界面
        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(views.get(arg1), 0);
            return views.get(arg1);
        }

        //销毁arg1位置的界面
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(views.get(arg1));
        }


    }
}
