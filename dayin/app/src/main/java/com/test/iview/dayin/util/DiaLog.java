package com.test.iview.dayin.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.test.iview.dayin.R;


/**
 * Created by Administrator on 2017/10/18.
 */

public class DiaLog {
    public static Dialog dialog1;

    public static RelativeLayout diaLog(Dialog dialog, Context context, int layout1) {
        dialog1 = dialog;
        LayoutInflater inflaterDl = LayoutInflater.from(context);
        dialog1 = new Dialog(context, R.style.Theme_Light_Dialog);
        RelativeLayout layout = (RelativeLayout) inflaterDl.inflate(layout1, null);
        Window window = dialog1.getWindow();
        window.setGravity(Gravity.BOTTOM);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        window.setWindowAnimations(R.style.dialogStyle);
        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为充满全屏       如需贴着底部 设置高度包裹内容 WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        //将设置好的属性set回去
        window.setAttributes(lp);
        //将自定义布局加载到dialog上
        dialog1.setContentView(layout);
        dialog1.show();
        return layout;
    }

    public static void dismiss() {
        if (dialog1 != null) {
            dialog1.dismiss();
        }
    }


}
