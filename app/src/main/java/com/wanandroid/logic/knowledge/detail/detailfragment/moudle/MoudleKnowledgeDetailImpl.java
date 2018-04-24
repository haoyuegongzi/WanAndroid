package com.wanandroid.logic.knowledge.detail.detailfragment.moudle;

import android.util.Log;

import com.wanandroid.common.ApiClient;
import com.wanandroid.common.CommonVariable;
import com.wanandroid.common.base.BaseApplication;
import com.wanandroid.logic.knowledge.detail.detailfragment.presenter.KnowledgeDetailCallBack;
import com.wanandroid.utils.auxiliary.StringUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * 作者：Created by Administrator on 2018/4/3.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class MoudleKnowledgeDetailImpl implements MoudleKnowledgeDetailInterface {

    Disposable disposable;

    @Override
    public void requestKnowledgeDetailData(String page, String id, final KnowledgeDetailCallBack callBack) {
        Retrofit retrofit = BaseApplication.getRetrofitObject(CommonVariable.BASEURL);
        ApiClient.KnowledgeInfo KnowledgeDetail = retrofit.create(ApiClient.KnowledgeInfo.class);

        KnowledgeDetail.wanAndroidKnowledgeDetail(page, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KnowledgeDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(KnowledgeDetailBean knowledgeDetailBean) {
                        if (null != knowledgeDetailBean && Integer.parseInt(knowledgeDetailBean.errorCode) == 0
                                && StringUtils.isTrimEmpty(knowledgeDetailBean.errorMsg)) {
                            callBack.requestDataResult(CommonVariable.REQUESTCODE_SECCESS, knowledgeDetailBean);
                        }else {
                            callBack.requestDataResult(CommonVariable.REQUESTCODE_FAILED, null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.requestDataResult(CommonVariable.REQUESTCODE_PARAMS_ERROR, null);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "onComplete: Over!");
                    }
                });
    }
}
