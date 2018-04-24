package com.wanandroid.logic.drawer.collection.presenter;

import com.wanandroid.logic.home.moudle.ArticleListBean;

/**
 * 作者：Created by Administrator on 2018/4/21.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public interface CollectionListCallBack {
    void collectionListSuccess(int requestCode, ArticleListBean collectionBean);
    void collectionListFailed(int requestCode, String msg);
}
