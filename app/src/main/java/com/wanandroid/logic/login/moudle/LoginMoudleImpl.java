package com.wanandroid.logic.login.moudle;

import android.util.Log;

import com.wanandroid.common.ApiClient;
import com.wanandroid.common.CommonVariable;
import com.wanandroid.common.base.BaseApplication;
import com.wanandroid.logic.login.presenter.LoginInfoCallBack;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：Created by Administrator on 2018/4/17.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class LoginMoudleImpl implements LoginMoudleInterface {
    LoginBean loginBean;
    @Override
    public void login(UserInfo info, final LoginInfoCallBack loginInfoCallBack) {
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("username", info.getUsername());
        loginMap.put("password", info.getPassword());

//        Retrofit retrofit = BaseApplication.getRetrofit();
//        ApiClient.Login loginService = retrofit.create(ApiClient.Login.class);
//        Call<LoginBean> loginCall = loginService.wanAndroidLoginCall(loginMap);
//        loginCall.enqueue(new Callback<LoginBean>() {
//            @Override
//            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
//                loginBean = response.body();
//                if (loginBean == null) {
//                    throw new NullPointerException("LoginBean ObJect is Empty(登录LoginBean对象为空)");
//                }
//                Log.i("TAG", "onResponse: " + loginBean.toString());
//                loginInfoCallBack.loginInfoCallBack(CommonVariable.REQUESTCODE_SECCESS, loginBean);
//            }
//
//            @Override
//            public void onFailure(Call<LoginBean> call, Throwable t) {
//                loginInfoCallBack.loginInfoCallBack(CommonVariable.REQUESTCODE_FAILED, loginBean);
//            }
//        });

        BaseApplication.getRetrofit()
                .create(ApiClient.Login.class)
                .wanAndroidLoginCall(loginMap)
                .enqueue(new Callback<LoginBean>() {
                    @Override
                    public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                        loginBean = response.body();
                        if (loginBean == null) {
                            throw new NullPointerException("LoginBean ObJect is Empty(登录LoginBean对象为空)");
                        }
                        Log.i("TAG", "onResponse: loginBean.toString()===" + loginBean.toString());
                        loginInfoCallBack.loginInfoCallBack(CommonVariable.REQUESTCODE_SECCESS, loginBean);
                    }

                    @Override
                    public void onFailure(Call<LoginBean> call, Throwable t) {
                        loginInfoCallBack.loginInfoCallBack(CommonVariable.REQUESTCODE_FAILED, loginBean);
                    }
        });

//        loginService.wanAndroidLogin(loginMap)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<LoginBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(LoginBean loginBean) {
//                        if (loginBean != null) {
////                            loginInfoCallBack.loginInfoCallBack(1, loginBean);
//                            Log.i("TAG", "onNext: jsonObject ===" + loginBean.toString());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("TAG", "onError: onError ===" + e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.i("TAG", "onComplete: onComplete ===");
//                    }
//                });
    }
}
