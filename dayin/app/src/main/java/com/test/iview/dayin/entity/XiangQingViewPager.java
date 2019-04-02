package com.test.iview.dayin.entity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */

public class XiangQingViewPager extends PagerAdapter {
    public List<ImageView> li;

    public XiangQingViewPager(List<ImageView> li) {
        this.li = li;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        try {
            ((ViewPager) container).addView((View) li.get(position % li.size()),
                    0);
        } catch (Exception e) {
        }
        if (li.size() != 0) {
            return li.get(position % li.size());
        } else {
            return 0;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
