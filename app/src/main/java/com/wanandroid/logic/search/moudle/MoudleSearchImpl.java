package com.wanandroid.logic.search.moudle;

import android.util.Log;

import com.wanandroid.common.ApiClient;
import com.wanandroid.common.CommonVariable;
import com.wanandroid.common.base.BaseApplication;
import com.wanandroid.logic.search.presenter.SearchRequestCallBack;
import com.wanandroid.utils.auxiliary.StringUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 *
 * @author Administrator
 * @date 2018/4/9
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class MoudleSearchImpl implements MoudleSearchInterface{

    @Override
    public void requestSearchData(String id, final String keyWord, final SearchRequestCallBack searchCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("k", keyWord);

        Retrofit retrofit = BaseApplication.getRetrofitObject(CommonVariable.BASEURL);
        ApiClient.Search searchApi = retrofit.create(ApiClient.Search.class);
        Call<SearchBean> call = searchApi.wanAndroidSearchCall(id, map);
        call.enqueue(new Callback<SearchBean>() {
            @Override
            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                SearchBean searchBean = response.body();
                if (null != response && Integer.parseInt(searchBean.getErrorCode()) == 0 &&
                        StringUtils.isTrimEmpty(searchBean.getErrorMsg())) {
                    Log.i("TAG", "onResponse: searchBean.toString() ***keyWord===" + keyWord + "\n" + searchBean.toString());
                    searchCallBack.requestDataSuccess(CommonVariable.REQUESTCODE_SECCESS, searchBean);
                }else {
                    searchCallBack.requestDataFailed(CommonVariable.REQUESTCODE_FAILED, CommonVariable.REQUEST_MSG_FAILED);
                }
            }

            @Override
            public void onFailure(Call<SearchBean> call, Throwable t) {
                searchCallBack.requestDataFailed(CommonVariable.REQUESTCODE_PARAMS_ERROR, CommonVariable.REQUEST__PARAMS_ERROR);
                Log.i("TAG", "onFailure: 发生异常： " + t);
            }
        });

//        searchApi.wanAndroidSearchObservable(id, map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<SearchBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(SearchBean searchBean) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}
