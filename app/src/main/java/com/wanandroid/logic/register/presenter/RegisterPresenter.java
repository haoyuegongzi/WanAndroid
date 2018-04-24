package com.wanandroid.logic.register.presenter;

import android.util.Log;

import com.wanandroid.logic.register.moudle.MoudleRegisterImpl;
import com.wanandroid.logic.register.moudle.MoudleRegisterInterface;
import com.wanandroid.logic.register.moudle.RegisterBean;
import com.wanandroid.logic.register.moudle.RegisterResult;
import com.wanandroid.logic.register.view.ViewRegisterInterface;

/**
 * 作者：Created by Administrator on 2018/4/21.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class RegisterPresenter {
    ViewRegisterInterface viewRegisterInterface;
    MoudleRegisterInterface moudleRegisterInterface;

    public RegisterPresenter(ViewRegisterInterface viewRegisterInterface){
        this.viewRegisterInterface = viewRegisterInterface;
        moudleRegisterInterface = new MoudleRegisterImpl();
    }

    public void register(RegisterBean registerBean){
        moudleRegisterInterface.register(registerBean, registerCallBack);
    }

    RegisterCallBack registerCallBack = new RegisterCallBack(){
        @Override
        public void registerSuccess(int registerCode, RegisterResult registerResult) {
            Log.i("TAG", "onResponse: registerResult.toString() ===" + registerResult.toString());
            viewRegisterInterface.registerSuccess(registerCode, registerResult);

        }

        @Override
        public void registerFailed(int registerCode, String smg) {
            viewRegisterInterface.registerFailed(registerCode, smg);
        }
    };
}
