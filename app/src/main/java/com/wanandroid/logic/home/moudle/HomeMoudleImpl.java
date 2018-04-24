package com.wanandroid.logic.home.moudle;

import com.wanandroid.common.ApiClient;
import com.wanandroid.common.CommonVariable;
import com.wanandroid.common.base.BaseApplication;
import com.wanandroid.logic.home.presenter.HomeRequestCallBack;
import com.wanandroid.utils.auxiliary.StringUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 作者：chen1 on 2018/3/21 11
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class HomeMoudleImpl implements MoudleHomeInterface {
//    Disposable disposable;

    @Override
    public void reuestHomaeBanner(String sonUrl, final HomeRequestCallBack homeRequestCallBack) {
        ApiClient.HomeInfo homeInfoService = getHomeInfoService();
        Call<BannerBean> bannerBeanCall = homeInfoService.wanAndroidBanner();
        bannerBeanCall.enqueue(new Callback<BannerBean>() {

            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                BannerBean bannerBean = response.body();
                homeRequestCallBack.homeBannerRequestCallBack(bannerBean, CommonVariable.REQUESTCODE_SECCESS);
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {
                homeRequestCallBack.homeBannerRequestCallBack(null, CommonVariable.REQUESTCODE_FAILED);
            }
        });
    }

    @Override
    public void requestHomeArticleList(String id, final HomeRequestCallBack homeArticleRequestCallBack) {
        ApiClient.HomeInfo homeInfoService = getHomeInfoService();
        CommonVariable.disposableArticle = homeInfoService.wanAndroidArticleList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticleListBean>() {
                    @Override
                    public void accept(ArticleListBean articleListBean) throws Exception {
                        if (articleListBean == null) {
                            homeArticleRequestCallBack.homeArticleRequestCallBACK(null, CommonVariable.REQUESTCODE_FAILED);
                        }
                        if (articleListBean != null && Integer.parseInt(articleListBean.errorCode) == 0 &&
                                StringUtils.isTrimEmpty(articleListBean.errorMsg)) {
                            homeArticleRequestCallBack.homeArticleRequestCallBACK(articleListBean, CommonVariable.REQUESTCODE_SECCESS);
                        } else {
                            homeArticleRequestCallBack.homeArticleRequestCallBACK(null, CommonVariable.REQUESTCODE_FAILED);
                        }
                    }
                });
    }

//    @Override
//    public void requestCollection(String id, CollectionCallBack collectionCallBack) {
//            Retrofit retrofit = BaseApplication.getRetrofit();
//            ApiClient.Collection collectionService = retrofit.create(ApiClient.Collection.class);
//            collectionService.wanAndroidAddCollection(id).enqueue(new Callback<JSONObject>() {
//                @Override
//                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//                    if (response == null) {
//                        throw new NullPointerException("请求结果为空，请稍候或者检查后再试");
//                    }
//                    Log.i("TAG", "Collection → → → → →onResponse: response.toString())" + response.toString());
//                }
//
//                @Override
//                public void onFailure(Call<JSONObject> call, Throwable t) {
//
//                }
//            });
//    }



    private ApiClient.HomeInfo getHomeInfoService(){
        Retrofit retrofit = BaseApplication.getRetrofitObject(CommonVariable.BASEURL);
        return retrofit.create(ApiClient.HomeInfo.class);
    }
}
