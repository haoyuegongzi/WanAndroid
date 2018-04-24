package com.wanandroid.logic.firehot.moudle;

import android.util.Log;

import com.wanandroid.common.ApiClient;
import com.wanandroid.common.CommonVariable;
import com.wanandroid.common.base.BaseApplication;
import com.wanandroid.utils.auxiliary.StringUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * 作者：chen1 on 2018/3/23 09
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class MoudleFireHotImpl implements MoudleFireHotInterface {

    ApiClient.FireHotInfo fireHotService = getRetrofitObject();
    @Override
    public void getHotSearchData(String url, final MoudleFireHotCallBack hotCallBack) {

        CommonVariable.disposableFireHot = fireHotService.wanAndroidFireHot()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FireHotSearchBean>() {
                    @Override
                    public void accept(FireHotSearchBean hotSearchBean) throws Exception {
//                        Log.i("TAG", "accept: hotSearchBean===" + hotSearchBean.toString() + "\n" +
//                                                "hotSearchBean.data.toString()" + hotSearchBean.data.toString());
                        if (hotSearchBean == null) {
                            hotCallBack.fireHotSearchCallBack(CommonVariable.REQUESTCODE_FAILED, null);
                        }
                        if (hotSearchBean.errorCode == 0 && StringUtils.isTrimEmpty(hotSearchBean.errorMsg)) {
                            hotCallBack.fireHotSearchCallBack(CommonVariable.REQUESTCODE_SECCESS, hotSearchBean);
                        }else {
                            hotCallBack.fireHotSearchCallBack(CommonVariable.REQUESTCODE_FAILED, null);
                        }
                    }
                });
    }

    @Override
    public void getFireHotCommonWebSite(String url, final MoudleFireHotCallBack hotCallBack) {
        CommonVariable.disposableCommonWebSite = fireHotService.wanAndroidCommon()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommonWebSiteBean>() {
                    @Override
                    public void accept(CommonWebSiteBean commonWebSiteBean) throws Exception {
                        Log.i("TAG", "accept: commonWebSiteBean ===" + commonWebSiteBean.toString());
                        if (commonWebSiteBean == null) {
                            hotCallBack.firHotCommonWebSite(CommonVariable.REQUESTCODE_FAILED, null);
                        }
                        if (Integer.parseInt(commonWebSiteBean.errorCode) == 0 && StringUtils.isTrimEmpty(commonWebSiteBean.errorMsg)) {
                            hotCallBack.firHotCommonWebSite(CommonVariable.REQUESTCODE_SECCESS, commonWebSiteBean);
                        }else {
                            hotCallBack.firHotCommonWebSite(CommonVariable.REQUESTCODE_FAILED, null);
                        }
                    }
                });
    }


    private ApiClient.FireHotInfo getRetrofitObject(){
        Retrofit retrofit = BaseApplication.getRetrofitObject(CommonVariable.BASEURL);
        return retrofit.create(ApiClient.FireHotInfo.class);
    }
}
