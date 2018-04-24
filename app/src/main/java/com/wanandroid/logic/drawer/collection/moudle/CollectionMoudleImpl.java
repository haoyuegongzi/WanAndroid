package com.wanandroid.logic.drawer.collection.moudle;

import android.util.Log;

import com.wanandroid.common.ApiClient;
import com.wanandroid.common.CommonVariable;
import com.wanandroid.common.base.BaseApplication;
import com.wanandroid.logic.drawer.collection.presenter.CollectionListCallBack;
import com.wanandroid.logic.home.moudle.ArticleListBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 作者：Created by Administrator on 2018/4/21.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class CollectionMoudleImpl implements CollectionMoudleInterface {
    @Override
    public void requestCollectListData(String id, final CollectionListCallBack collectionListCallBack) {
        Retrofit retrofit = BaseApplication.getRetrofit();
        ApiClient.Collection collectionListService = retrofit.create(ApiClient.Collection.class);
        collectionListService.wanAndroidCollectionlist(id)
                .enqueue(new Callback<ArticleListBean>() {
                    @Override
                    public void onResponse(Call<ArticleListBean> call, Response<ArticleListBean> response) {
                        ArticleListBean collectionList = response.body();
                        if (response == null) {
                            collectionListCallBack.collectionListFailed(CommonVariable.REQUESTCODE_FAILED, CommonVariable.REQUEST_MSG_FAILED);
                            throw new NullPointerException("请求结果为空，请稍候再试");
                        }
                        collectionListCallBack.collectionListSuccess(CommonVariable.REQUESTCODE_SECCESS, collectionList);
                        Log.i("TAG", "onResponse: collectionList.toString() ==" + collectionList.toString());
                    }

                    @Override
                    public void onFailure(Call<ArticleListBean> call, Throwable t) {
                        collectionListCallBack.collectionListFailed(CommonVariable.REQUESTCODE_PARAMS_ERROR, CommonVariable.REQUEST__PARAMS_ERROR);
                    }
                });



    }
}
