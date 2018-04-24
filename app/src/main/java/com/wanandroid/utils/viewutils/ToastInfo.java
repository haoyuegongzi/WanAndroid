package com.wanandroid.utils.viewutils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者：Created by Administrator on 2018/4/17.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class ToastInfo {

    public static void shortToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
