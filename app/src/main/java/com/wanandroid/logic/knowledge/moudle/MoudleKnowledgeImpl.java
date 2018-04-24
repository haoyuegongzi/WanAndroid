package com.wanandroid.logic.knowledge.moudle;

import android.util.Log;

import com.wanandroid.common.ApiClient;
import com.wanandroid.common.CommonVariable;
import com.wanandroid.common.base.BaseApplication;
import com.wanandroid.logic.knowledge.presenter.KnowledgeCallBack;
import com.wanandroid.utils.auxiliary.StringUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * 作者：chen1 on 2018/3/23 17
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class MoudleKnowledgeImpl implements MoudleKnowledgeInterface {
    Disposable knowledgedisposable;
    @Override
    public void doRequestKnowledgeData(String url, Disposable disposableKnowledge, final KnowledgeCallBack callBack) {
        Retrofit retrofit = BaseApplication.getRetrofitObject(CommonVariable.BASEURL);
        this.knowledgedisposable = disposableKnowledge;
        ApiClient.KnowledgeInfo knowledgeService = retrofit.create(ApiClient.KnowledgeInfo.class);
//        this.knowledgedisposable = knowledgeService.wanAndroidKnowledge()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<KnowledgeBean>() {
//                    @Override
//                    public void accept(KnowledgeBean knowledgeBean) throws Exception {
//                        Log.i("TAG", "accept: knowledgeBean.toString()===" + knowledgeBean.toString());
//                        if (knowledgeBean != null && Integer.parseInt(knowledgeBean.errorCode) == 0 &&
//                                                        StringUtils.isTrimEmpty(knowledgeBean.errorMsg)) {
//                            callBack.requestDataResult(CommonVariable.REQUESTCODE_SECCESS, knowledgeBean, knowledgedisposable);
//                        }else {
//                            callBack.requestDataResult(CommonVariable.REQUESTCODE_FAILED, null, knowledgedisposable);
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        callBack.requestDataResult(CommonVariable.REQUESTCODE_PARAMS_ERROR, null, knowledgedisposable);
//                    }
//                });
        knowledgeService.wanAndroidKnowledge()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KnowledgeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("TAG", "onSubscribe: ");
                        knowledgedisposable = d;
                    }

                    @Override
                    public void onNext(KnowledgeBean knowledgeBean) {
                        if (knowledgeBean != null && Integer.parseInt(knowledgeBean.errorCode) == 0 &&
                                StringUtils.isTrimEmpty(knowledgeBean.errorMsg)) {
                            callBack.requestDataResult(CommonVariable.REQUESTCODE_SECCESS, knowledgeBean, knowledgedisposable);
                        }else {
                            callBack.requestDataResult(CommonVariable.REQUESTCODE_FAILED, null, knowledgedisposable);
                        }
                        Log.d("TAG", "onNextonNextonNextonNextonNextonNextonNextonNextonNextonNext:  \n\n\n\n" + knowledgeBean.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.requestDataResult(CommonVariable.REQUESTCODE_PARAMS_ERROR, null, knowledgedisposable);
                        Log.e("TAG", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "onComplete: Over!");
                    }
                });
    }
}
