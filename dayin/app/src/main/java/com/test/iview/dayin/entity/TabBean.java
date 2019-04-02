package com.test.iview.dayin.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by Administrator on 2018\5\3 0003.
 */

public class TabBean implements CustomTabEntity {
    public String title;

    public TabBean(String title) {
        this.title = title;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
