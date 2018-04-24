package com.wanandroid.logic.login.moudle;

import com.wanandroid.logic.login.presenter.LoginInfoCallBack;

/**
 * 作者：Created by Administrator on 2018/4/17.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public interface LoginMoudleInterface {

    void login(UserInfo info, LoginInfoCallBack loginInfoCallBack);
}
