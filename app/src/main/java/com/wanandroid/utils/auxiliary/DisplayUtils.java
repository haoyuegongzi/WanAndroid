package com.wanandroid.utils.auxiliary;

import android.content.Context;

/**
 *
 * @author chen1
 * @date 2017/12/8
 * TO DO:
 */

public class DisplayUtils {
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * @return
     */
    public static int pxToDp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * @return
     */
    public static int dpToPx(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @return
     */
    public static int pxToSp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @return
     */
    public static int spToPx(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 舍掉小数取整:
     */
    public static double mathToInt(double value){
        return Math.floor(value);
    }

    /**
     * 四舍五入取整:
     */
    public static double mathRounded(double value){
        return Math.rint(value);
    }

    /**
     * 进位取整:
     */
    public static double mathAddToInt(double value){

        return Math.ceil(value);
    }

    /**
     * 取绝对值：
     */
    public static double MathABS(double value){
        return Math.abs(value);
    }

    /**
     * 获取屏幕的宽度（单位：px）
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的高度（单位：px）
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
