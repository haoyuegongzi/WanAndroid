package com.wanandroid.logic.home.presenter;

import com.wanandroid.logic.home.moudle.ArticleListBean;
import com.wanandroid.logic.home.moudle.BannerBean;

/**
 * 作者：chen1 on 2018/3/21 14
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public interface HomeRequestCallBack {

    void homeBannerRequestCallBack(BannerBean bannerBean, int requestCode);

    void homeArticleRequestCallBACK(ArticleListBean articleListBean, int reQuestCode);
}
