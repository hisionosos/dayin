package com.test.iview.dayin.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.test.iview.dayin.R;
import com.test.iview.dayin.fragment.HomeFragment;
import com.test.iview.dayin.fragment.MyFragment;
import com.test.iview.dayin.utils.ResourceUtils;
import com.test.iview.dayin.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    @BindView(R.id.main_lay)
    FrameLayout mainLay;
    @BindView(R.id.main_tab)
    TabLayout mainTab;

    private HomeFragment homeFragment;
//    private AdContentFragment adContentFragment;
//    private PlayFragment playFragment;
    private MyFragment myFragment;
    private String[] tab_title = {"首页", "个人中心"};
    private int[] tab_imgs = {R.drawable.tab_home,R.drawable.tab_my};
    private String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
    private ArrayList fgs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        if (!Settings.canDrawOverlays(this)) {
//            //若未授权则请求权限
//            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//            intent.setData(Uri.parse("package:" + getPackageName()));
//            startActivityForResult(intent, 2000);
//        }

        //跳转设置通知界面手动打开
//        if (Utils.isNotificationEnabled(this)){
//            Utils.gotoNotificationSetting(this);
//        }

        //检查权限
        if (!EasyPermissions.hasPermissions(this,perms)){
            EasyPermissions.requestPermissions(this,"是否打开需要的权限？",1000,perms);
        }else{

        }

        init();

    }


    private void init() {
        homeFragment = new HomeFragment();
        myFragment = new MyFragment();

        fgs.add(homeFragment);
        fgs.add(myFragment);

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.main_lay, homeFragment)
                .add(R.id.main_lay, myFragment)
                .hide(myFragment);
        transaction.commit();


        //初始化tab
        mainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView= tab.getCustomView().findViewById(R.id.tab_txt);
                textView.setTextColor((ResourceUtils.getColor(R.color.main_tab_color)));
                getSupportFragmentManager().beginTransaction().show((Fragment) fgs.get(tab.getPosition())).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView textView= tab.getCustomView().findViewById(R.id.tab_txt);
                textView.setTextColor((ResourceUtils.getColor(R.color.t32)));
                getSupportFragmentManager().beginTransaction().hide((Fragment) fgs.get(tab.getPosition())).commit();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0;i < 2; i++){
            TabLayout.Tab tab = mainTab.newTab();
            View view = LayoutInflater.from(this).inflate(R.layout.main_tab_item,null);
            ImageView imageView = view.findViewById(R.id.tab_img);
            TextView textView = view.findViewById(R.id.tab_txt);
            imageView.setBackgroundResource(tab_imgs[i]);
            textView.setText(tab_title[i]);
            tab.setCustomView(view);
            mainTab.addTab(tab);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.e("permission",perms.toString());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        ToastUtils.showShort("部分权限未打开，可能影响使用");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

}
