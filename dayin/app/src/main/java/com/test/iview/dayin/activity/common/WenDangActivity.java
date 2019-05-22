package com.test.iview.dayin.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.activity.MainActivity;
import com.test.iview.dayin.utils.ResourceUtils;
import com.test.iview.dayin.view.word.ShowWordActivity;
import com.test.iview.dayin.view.word.WordAdapter;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class WenDangActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.main_tab)
    RadioGroup mainTab;
    @BindView(R.id.back)
    ImageView back;

    public static String sdPath = Environment.getExternalStorageDirectory()
            .getAbsolutePath();
    private ListView wordList;
    private WordAdapter wordAdapter;
    private ArrayList<File> fileList;

    private Intent intent;


    String flag = "";
//    private String[] tab_title = {getString(R.string.dy_daoru), getString(R.string.dy_ziti),  getString(R.string.dy_chunwenben),};
    private int[] tab_imgs = { R.drawable.tab_daoru, R.drawable.tab_ziti, R.drawable.tab_chunwenben};

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        flag = getIntent().getStringExtra("flag");
        commonTitle.setText(R.string.dy_wendang);

    }

    @Override
    public void initData() {
        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);


        wordList = (ListView) this.findViewById(R.id.list);

        fileList = new ArrayList<File>();
        getFiles(sdPath);
        for (int i = 0; i < fileList.size(); i++) {
            Log.e("文件名：",
                    fileList.get(i).getName() + "\n文件路径："
                            + fileList.get(i).getAbsolutePath());
        }
        wordAdapter = new WordAdapter(this, fileList);
        wordList.setAdapter(wordAdapter);
        wordList.setOnItemClickListener(this);

        mainTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_tab1:

                        break;
                    case R.id.main_tab2:

                        break;
                    case R.id.main_tab3:

                        break;
                }

            }
        });


    }


    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

        }
    }

    @Override
    public int initLayout() {
        return R.layout.wendang_lay;
    }


    public void getFiles(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            // Log.i("=====", files[i].getName() + "");
            if (files[i].isDirectory()) {
                getFiles(files[i].getAbsolutePath());
            } else {
                if (isWord(files[i])) {
                    fileList.add(files[i]);
                }
            }
        }
    }

    public boolean isWord(File file) {
        String fileName = file.getName();
        if (fileName.endsWith(".doc") || fileName.endsWith(".docx")
                || fileName.endsWith(".xls") || fileName.endsWith(".xlsx")
                || fileName.endsWith(".pptx") || fileName.endsWith(".ppt")) {
            return true;
        }
        return false;
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        String aa = fileList.get(arg2).getAbsolutePath();
        if (aa.endsWith(".pptx")||aa.endsWith(".ppt") ) {
//            intent = new Intent(WenDangActivity.this, PPTActivity.class);
//            intent.putExtra("filePath", fileList.get(arg2).getAbsolutePath());
//            startActivity(intent);
        }if (aa.endsWith(".ppt")  ) {
//			intent = new Intent(MainActivity.this, PPTMainActivity.class);
//			intent.putExtra("filePath", fileList.get(arg2).getAbsolutePath());
//			startActivity(intent);
        }
        else {
            intent = new Intent(WenDangActivity.this, ShowWordActivity.class);
            intent.putExtra("filePath", fileList.get(arg2).getAbsolutePath());
            startActivity(intent);
        }


//		Intent intent = new Intent();
//		intent.setClass(MainActivity.this, ShowWordActivity.class);
//		intent.putExtra("filePath", fileList.get(arg2).getAbsolutePath());
//		startActivity(intent);
    }
}
