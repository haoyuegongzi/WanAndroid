package com.wanandroid.logic.login.view;

import com.wanandroid.logic.login.moudle.LoginBean;
import com.wanandroid.logic.login.moudle.UserInfo;

/**
 * 作者：Created by Administrator on 2018/4/17.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public interface ViewLoginInterface {

    UserInfo getUserInfo(String username, String password);
    void loginSuccess(int loginCode, LoginBean loginBean);
    void loginFailed(int lofinCode, LoginBean loginBean);
}
