package com.test.iview.dayin.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.iview.dayin.R;
import com.test.iview.dayin.activity.DeviceListActivity;
import com.test.iview.dayin.activity.LanguageActivity;
import com.test.iview.dayin.activity.MyInfosActivity;
import com.test.iview.dayin.entity.MyBean;
import com.test.iview.dayin.global.MyApplication;
import com.test.iview.dayin.utils.CameraUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2018\4\25 0025.
 */

public class MyFragment extends BaseFragment {

    @BindView(R.id.person_info)
    RelativeLayout personInfo;
    @BindView(R.id.item1)
    RelativeLayout item1;
    @BindView(R.id.item2)
    RelativeLayout item2;
    @BindView(R.id.item3)
    RelativeLayout item3;
    @BindView(R.id.item4)
    RelativeLayout item4;
    @BindView(R.id.my_shezhi)
    CircleImageView myShezhi;
    @BindView(R.id.nick_txt)
    TextView nickTxt;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;


    public MyFragment() {
    }


    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fagment_my, container, false);
    }

    @Override
    public void initData() {
        //显示头像
//        Bitmap bitmap = MyApplication.mCache.getAsBitmap("user_head");
//        if (null != bitmap){
//            myShezhi.setImageBitmap(bitmap);
//        }


    }

    @Override
    public void onResume() {
        super.onResume();
        String nick = MyApplication.mCache.getAsString("user_nick");
        if (nick != null){
            nickTxt.setText(nick);
        }else{
            nickTxt.setText(R.string.app_name);
        }

        Bitmap bitmap = MyApplication.mCache.getAsBitmap("user_head");
        if (null != bitmap){
            myShezhi.setImageBitmap(bitmap);
        }
    }

    @OnClick({R.id.person_info, R.id.item1, R.id.item2, R.id.item3, R.id.item4,R.id.my_shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_info:
                startActivity(new Intent(getActivity(), MyInfosActivity.class));
                break;
            case R.id.item1:

                break;
            case R.id.item2:
                startActivity(new Intent(getActivity(), LanguageActivity.class));
                break;
            case R.id.item3:
                break;
            case R.id.item4:
                startActivity(new Intent(MyFragment.this.getActivity(), DeviceListActivity.class));
                break;
            case R.id.my_shezhi:
                showCusDialog();
                break;
        }
    }
    private Uri uri;//拍照图片的uri
    private void showCusDialog() {
        final Dialog dialog = new Dialog(getContext(),R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.add_content_dialog_lay, null);
        TextView img_btn = (TextView) inflate.findViewById(R.id.choose_img_btn);
        TextView ved_btn = (TextView) inflate.findViewById(R.id.choose_vde_btn);
        TextView cancel_btn = (TextView) inflate.findViewById(R.id.cancel_btn);

        img_btn.setText(R.string.dy_take_photo);
        ved_btn.setText(R.string.dy_from_local);
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = CameraUtils.takePhoto(null,MyFragment.this);
                dialog.dismiss();
            }
        });

        ved_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraUtils.albumChoose(null, MyFragment.this);
                dialog.dismiss();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.y = 18;//
        dialogWindow.setAttributes(lp);
        dialog.show();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CameraUtils.CODE_TAKE_PHOTO) {//获取系统照片上传

            Bitmap bm = null;
            try {
                if (null!= uri){
                    bm = getBitmapFormUri(getContext(),uri);
                    myShezhi.setImageBitmap(bm);
                    MyApplication.mCache.put("user_head",bm);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        if (requestCode == CameraUtils.CODE_ALBUM_CHOOSE && resultCode == RESULT_OK && data != null) {
            Bitmap bm = null;
            try {
                if (null != data.getData()){
                    bm = getBitmapFormUri(getContext(),data.getData());
                    myShezhi.setImageBitmap(bm);
                    MyApplication.mCache.put("user_head",bm);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public Bitmap getBitmapFormUri(Context context, Uri uri) throws FileNotFoundException, IOException {
        InputStream input = context.getContentResolver().openInputStream(uri);

        //这一段代码是不加载文件到内存中也得到bitmap的真是宽高，主要是设置inJustDecodeBounds为true
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;//不加载到内存
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.RGB_565;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;

        //图片分辨率以480x800为标准
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比，由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例压缩
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;
        bitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        input = context.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return compressImage(bitmap);//再进行质量压缩
    }

    public Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
            if (options<=0)
                break;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }
}
