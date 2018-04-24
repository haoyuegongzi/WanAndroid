package com.wanandroid.logic.register.moudle;

import com.wanandroid.common.ApiClient;
import com.wanandroid.common.CommonVariable;
import com.wanandroid.common.base.BaseApplication;
import com.wanandroid.logic.register.presenter.RegisterCallBack;
import com.wanandroid.utils.auxiliary.StringUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 作者：Created by Administrator on 2018/4/21.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class MoudleRegisterImpl implements MoudleRegisterInterface{

    @Override
    public void register(RegisterBean registerBean, final RegisterCallBack registerCallBack) {
        Map<String, String> registerMap = new HashMap<>();
        registerMap.put("username", registerBean.getUserName());
        registerMap.put("password", registerBean.getPassWord());
        registerMap.put("repassword", registerBean.getRepassWord());

        Retrofit retrofit = BaseApplication.getRetrofit();
        ApiClient.Register registerService = retrofit.create(ApiClient.Register.class);
        registerService.wanAndroidRegister(registerMap)
                .enqueue(new Callback<RegisterResult>() {
                        @Override
                        public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                            RegisterResult registerResult = response.body();
                            if (response == null) {
                                throw new NullPointerException("注册结果返回信息为空");
                            }
                            /**用户名已经被注册！*/
                            if ("-1".equals(registerResult.getErrorCode())) {
                                registerCallBack.registerFailed(CommonVariable.REQUESTCODE_PARAMS_ERROR, registerResult.getErrorMsg());
                            }
                            /**注册成功**/
                            if ("0".equals(registerResult.getErrorCode()) && StringUtils.isTrimEmpty(registerResult.getErrorMsg())) {
                                registerCallBack.registerFailed(CommonVariable.REQUESTCODE_SECCESS, registerResult.getErrorMsg());
                                registerCallBack.registerSuccess(CommonVariable.REQUESTCODE_SECCESS, registerResult);
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResult> call, Throwable t) {
                            registerCallBack.registerFailed(CommonVariable.REQUESTCODE_FAILED, "注册失败，请稍候再试");
                        }
        });

    }
}
