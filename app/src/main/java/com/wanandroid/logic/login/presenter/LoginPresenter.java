package com.wanandroid.logic.login.presenter;

import com.wanandroid.logic.login.moudle.LoginBean;
import com.wanandroid.logic.login.moudle.LoginMoudleImpl;
import com.wanandroid.logic.login.moudle.LoginMoudleInterface;
import com.wanandroid.logic.login.moudle.UserInfo;
import com.wanandroid.logic.login.view.ViewLoginInterface;

/**
 * 作者：Created by Administrator on 2018/4/17.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class LoginPresenter {
    ViewLoginInterface viewLoginInterface;
    LoginMoudleInterface loginMoudleInterface;

    public LoginPresenter(ViewLoginInterface loginInterface){
        viewLoginInterface = loginInterface;
        loginMoudleInterface = new LoginMoudleImpl();
    }

    public void doLoin(UserInfo userInfo){
        loginMoudleInterface.login(userInfo, loginInfoCallBack);
    }

    LoginInfoCallBack loginInfoCallBack = new LoginInfoCallBack(){
        @Override
        public void loginInfoCallBack(int loginCode, LoginBean loginBean) {
            viewLoginInterface.loginSuccess(loginCode, loginBean);
        }
    };
}
