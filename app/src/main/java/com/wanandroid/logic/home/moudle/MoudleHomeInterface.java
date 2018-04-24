package com.wanandroid.logic.home.moudle;

import com.wanandroid.logic.home.presenter.HomeRequestCallBack;

/**
 * 作者：chen1 on 2018/3/21 11
 * E—Mail：chen126jie@163.com
 * TODO：
 *
 * @author chen1
 */

public interface MoudleHomeInterface {
    void reuestHomaeBanner(String sonUrl, HomeRequestCallBack homeRequestCallBack);

    void requestHomeArticleList(String id, HomeRequestCallBack homeArticleRequestCallBack);

//    void requestCollection(String id, CollectionCallBack collectionCallBack);

}
