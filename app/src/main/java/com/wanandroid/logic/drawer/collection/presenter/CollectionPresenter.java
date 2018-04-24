package com.wanandroid.logic.drawer.collection.presenter;

import com.wanandroid.logic.drawer.collection.moudle.CollectionMoudleImpl;
import com.wanandroid.logic.drawer.collection.moudle.CollectionMoudleInterface;
import com.wanandroid.logic.drawer.collection.view.ViewCollectionInterface;
import com.wanandroid.logic.home.moudle.ArticleListBean;

/**
 * 作者：Created by Administrator on 2018/4/21.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class CollectionPresenter {
    ViewCollectionInterface viewCollectionInterface;
    CollectionMoudleInterface collectionMoudleInterface;

    public CollectionPresenter(ViewCollectionInterface viewCollectionInterface){
        this.viewCollectionInterface = viewCollectionInterface;
        collectionMoudleInterface = new CollectionMoudleImpl();
    }

    public void requestCollectListData(String id){
        collectionMoudleInterface.requestCollectListData(id, collectionListCallBack);
    }

    CollectionListCallBack collectionListCallBack = new CollectionListCallBack(){
        @Override
        public void collectionListSuccess(int requestCode, ArticleListBean collectionBean) {
            viewCollectionInterface.requestCollectArticleSuccess(requestCode, collectionBean);
        }

        @Override
        public void collectionListFailed(int requestCode, String msg) {
            viewCollectionInterface.requestCollectArticleFailed(requestCode, msg);
        }
    };
}
