package com.test.iview.dayin.activity.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.BaseActivity;
import com.test.iview.dayin.adapter.WanggeAdapter;
import com.test.iview.dayin.utils.BitmapUtil;
import com.test.iview.dayin.utils.CameraUtils;
import com.test.iview.dayin.utils.ToastUtils;
import com.test.iview.dayin.view.GridItemDec;
import com.test.iview.dayin.view.SingleTouchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class TuWenActivity extends BaseActivity {
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
    @BindView(R.id.canv)
    RelativeLayout canv;
    @BindView(R.id.main_tab1)
    RadioButton mainTab1;
    @BindView(R.id.main_tab2)
    RadioButton mainTab2;
    @BindView(R.id.main_tab3)
    RadioButton mainTab3;
    @BindView(R.id.main_tab4)
    RadioButton mainTab4;
    @BindView(R.id.main_tab5)
    RadioButton mainTab5;
    @BindView(R.id.hang_mis)
    ImageView hangMis;
    @BindView(R.id.hang_add)
    ImageView hangAdd;
    @BindView(R.id.lie_mis)
    ImageView lieMis;
    @BindView(R.id.lie_add)
    ImageView lieAdd;
    @BindView(R.id.wangge_guding)
    LinearLayout wanggeGuding;
    @BindView(R.id.wangge_lay)
    LinearLayout wanggeLay;
    @BindView(R.id.hang_count)
    TextView hangCount;
    @BindView(R.id.lie_count)
    TextView lieCount;
    @BindView(R.id.grid_lay)
    GridLayout gridLay;
    @BindView(R.id.code_bar)
    RelativeLayout codeBar;
    @BindView(R.id.get_rcode)
    Button getRcode;
    @BindView(R.id.txt_url)
    EditText txtUrl;@BindView(R.id.e_delete)
    TextView eDelete;
//    private String[] tab_title = {getString(R.string.wangge), getString(R.string.dy_photo), getString(R.string.dy_ajust), getString(R.string.dy_biaoqing), getString(R.string.dy_erweima)};
    private int[] tab_imgs = {R.drawable.tab_biaoge, R.drawable.tab_tupian, R.drawable.tab_tiaozheng, R.drawable.tab_biaoqing,
            R.drawable.tab_ercode};


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        commonTitle.setText(R.string.dy_tuwen);

        homeAdd.setVisibility(View.VISIBLE);
        homeAdd.setImageResource(R.drawable.printer);
        commonTxt.setVisibility(View.GONE);


    }

    @Override
    public void initData() {
        mainTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                }

            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.tuwen_lay;
    }

    private int hang_count = 3;
    private int lie_count = 3;

    private int editGrave = 0;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.back, R.id.hang_mis, R.id.hang_add, R.id.lie_mis, R.id.lie_add, R.id.wangge_guding,R.id.home_add,
            R.id.main_tab1,R.id.main_tab2,R.id.main_tab3,R.id.main_tab4,R.id.main_tab5,R.id.get_rcode,R.id.e_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.e_delete:
                eds.clear();
                gridLay.removeAllViews();
                gridLay.setBackgroundColor(Color.WHITE);
                break;
            case R.id.main_tab1:
                if (wanggeLay.getVisibility() == View.VISIBLE){
                    wanggeLay.setVisibility(View.INVISIBLE);
                    Log.e("lay",wanggeLay.getVisibility() + "");
                }else{
                    wanggeLay.setVisibility(View.VISIBLE);
                    Log.e("lay",wanggeLay.getVisibility() + "");
                }
                break;
            case R.id.main_tab2:
                CameraUtils.albumChoose(this,null);
                break;
            case R.id.home_add:
                for (int i = 0; i < arrs.size(); i++) {
                    SingleTouchView singleTouchView = arrs.get(i);
                    if (null != singleTouchView){
                        arrs.get(i).setEditable(false);
                    }
                }
                BitmapUtil.getInstance().getCutImage(canv);
                break;
            case R.id.main_tab3:
                if (editGrave == 0){
                    for (int i = 0; i < eds.size(); i++) {
                        eds.get(i).setGravity(Gravity.CENTER);
                    }
                    editGrave ++;
                }else if (editGrave == 1){
                    for (int i = 0; i < eds.size(); i++) {
                        eds.get(i).setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                    }
                    editGrave ++;
                }else if (editGrave == 2){
                    for (int i = 0; i < eds.size(); i++) {
                        eds.get(i).setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
                    }
                    editGrave --;
                    editGrave --;
                }
                break;
            case R.id.main_tab4:
                Intent intent = new Intent(this,SuCaiKuActivity.class);
                intent.putExtra("sucai","biaoqing");
                startActivityForResult(intent,1000);
                break;
            case R.id.main_tab5:
                if (codeBar.getVisibility() == View.VISIBLE){
                    codeBar.setVisibility(View.GONE);
                }else{
                    codeBar.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.hang_mis:
                if (hang_count > 1){
                    hang_count --;
                    hangCount.setText(hang_count + "");
                }else{
                    ToastUtils.showShort(R.string.dy_to_max);
                }
                break;
            case R.id.hang_add:
                if (hang_count < 10){
                    hang_count ++;
                    hangCount.setText(hang_count + "");
                }else{
                    ToastUtils.showShort(R.string.dy_to_max);
                }

                break;
            case R.id.lie_mis:
                if (lie_count > 1){
                    lie_count --;
                    lieCount.setText(lie_count + "");
                }else{
                    ToastUtils.showShort(R.string.dy_to_max);
                }

                break;
            case R.id.lie_add:
                if (lie_count < 10){
                    lie_count ++;
                    lieCount.setText(lie_count + "");
                }else{
                    ToastUtils.showShort(R.string.dy_to_max);
                }

                break;
            case R.id.wangge_guding:
                wanggeLay.setVisibility(View.GONE);
                gridLay.removeAllViews();
                addGridView();
                gridLay.setBackgroundColor(Color.BLACK);
                break;
            case R.id.get_rcode:
                String str = txtUrl.getText().toString();
                Bitmap bitmap = null;
                if (str.length() > 0){
                    bitmap = QRCodeEncoder.syncEncodeQRCode(str,350,R.color.black);//二维码
                    if (bitmap != null){
                        SingleTouchView singleTouchView = new SingleTouchView(TuWenActivity.this);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                        singleTouchView.setLayoutParams(layoutParams);
                        singleTouchView.setImageBitamp(bitmap);
                        canv.addView(singleTouchView);
                        arrs.add(singleTouchView);
                        if (codeBar.getVisibility() == View.VISIBLE){
                            codeBar.setVisibility(View.INVISIBLE);
                        }else{
                            codeBar.setVisibility(View.VISIBLE);
                        }
                    }else{
                        ToastUtils.showShort("error");
                    }


                }else{
                    ToastUtils.showShort(R.string.input_text);
                }

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000 && null != data) {
            int id = data.getIntExtra("img",0);
            addCusView(id);
        }
        if (requestCode == CameraUtils.CODE_ALBUM_CHOOSE && null != data) {
//              int s = BitmapUtil.getBitmapBytes(MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData()));
            addImage(data.getData());
        }

    }
    private ArrayList<SingleTouchView> arrs = new ArrayList<>();
    private ArrayList<String> arr = new ArrayList<>();
    private void addCusView(int id){
        if (id != 0){
            SingleTouchView singleTouchView = new SingleTouchView(this);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            singleTouchView.setLayoutParams(layoutParams);
            singleTouchView.setImageResource(id);
            canv.addView(singleTouchView);
            arrs.add(singleTouchView);
        }

    }

    private void addImage(Uri uri){
        showLoadingDialog();
        BitmapUtil.createScaledBitmap(this,uri,350, new BitmapUtil.MyCallback() {
            @Override
            public void onPrepare() {

            }

            @Override
            public void onSucceed(final Object object) {
                hideLaodingDialog();
                TuWenActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SingleTouchView singleTouchView = new SingleTouchView(TuWenActivity.this);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                        singleTouchView.setLayoutParams(layoutParams);
                        singleTouchView.setImageBitamp((Bitmap) object);
                        canv.addView(singleTouchView);
                        arrs.add(singleTouchView);
                    }
                });

            }

            @Override
            public void onError() {
                hideLaodingDialog();
            }
        });
    }



    private ArrayList<EditText> eds = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void addGridView(){
        eds.clear();
        for (int i = 0, j = lie_count*hang_count; i < j; i++) {
            EditText edit = new EditText(this);
            edit.setBackgroundColor(Color.rgb(255,255,255));
            eds.add(edit);
            //使用Spec定义子控件的位置和比重
            GridLayout.Spec rowSpec = GridLayout.spec(i / lie_count, 1f);
            GridLayout.Spec columnSpec = GridLayout.spec(i % lie_count, 1f);
            //将Spec传入GridLayout.LayoutParams并设置宽高为0，必须设置宽高，否则视图异常
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
            layoutParams.height = 0;
            layoutParams.width = 0;
            //还可以根据位置动态定义子控件直接的边距，下面的意思是
            //第一行的子控件都有2dp的bottomMargin，中间位置的子控件都有2dp的leftMargin和rightMargin
            layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.dp_1);
            layoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.dp_1);
            layoutParams.leftMargin = getResources().getDimensionPixelSize(R.dimen.dp_1);
            layoutParams.rightMargin = getResources().getDimensionPixelSize(R.dimen.dp_1);
            gridLay.addView(edit, layoutParams);
        }

    }

}
