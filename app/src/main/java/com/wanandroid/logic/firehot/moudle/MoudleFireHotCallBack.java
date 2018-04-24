package com.wanandroid.logic.firehot.moudle;

/**
 * 作者：chen1 on 2018/3/23 11
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public interface MoudleFireHotCallBack {
    void fireHotSearchCallBack(int requestCode, FireHotSearchBean hotSearchBean);

    void firHotCommonWebSite(int requestCode, CommonWebSiteBean commonWebSiteBean);
}
