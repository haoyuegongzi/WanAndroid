package com.wanandroid.logic.home.view;

import com.wanandroid.logic.home.moudle.ArticleListBean;
import com.wanandroid.logic.home.moudle.BannerBean;

/**
 * 作者：chen1 on 2018/3/21 11
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public interface ViewHomeInterface {

    void reuqestBannerDataSeccess(int requestCode, BannerBean bannerBean);
    void reuqestBannerDataFailed(int requestCode, String msg);

    void requestArticleSuccess(ArticleListBean articleListBean);
    void requestArticleFailed(String msg);
}
