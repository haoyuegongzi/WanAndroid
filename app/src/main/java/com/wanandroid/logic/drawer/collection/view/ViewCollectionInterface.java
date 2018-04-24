package com.wanandroid.logic.drawer.collection.view;

import com.wanandroid.logic.home.moudle.ArticleListBean;

/**
 * 作者：Created by Administrator on 2018/4/21.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public interface ViewCollectionInterface {
    void requestCollectArticleSuccess(int requestCode, ArticleListBean collectionBean);
    void requestCollectArticleFailed(int requestCode, String msg);
}
