package com.test.iview.dayin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NickActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.home_add)
    ImageView homeAdd;
    @BindView(R.id.common_txt)
    TextView commonTxt;
    @BindView(R.id.common_title)
    TextView commonTitle;
    @BindView(R.id.nick_edt)
    EditText nickEdt;
    @BindView(R.id.clear_nick)
    ImageView clearNick;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        homeAdd.setVisibility(View.GONE);
        commonTxt.setVisibility(View.VISIBLE);
        commonTxt.setText(R.string.dy_save);
        commonTitle.setText(R.string.dy_setnick);

    }

    @Override
    public void initData() {
        String nick = MyApplication.mCache.getAsString("user_nick");
        if (nick != null){
            nickEdt.setText(nick);
        }
    }

    @Override
    public int initLayout() {
        return R.layout.nick_lay;
    }


    @OnClick({R.id.back,R.id.common_txt, R.id.clear_nick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.common_txt:
                String nick = nickEdt.getText().toString();
                if (nick != null && nick.length() > 0){
                    MyApplication.mCache.put("user_nick",nick);
                    finish();
                }else{
                    ToastUtils.showShort(getString(R.string.dy_put_nick));
                }
                break;
            case R.id.clear_nick:
                nickEdt.setText("");
                break;
        }
    }
}
