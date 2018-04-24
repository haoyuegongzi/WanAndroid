package com.wanandroid.logic.register.view;

import com.wanandroid.logic.register.moudle.RegisterResult;

/**
 * 作者：Created by Administrator on 2018/4/21.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public interface ViewRegisterInterface {
    String getUsername();
    String getPassword();
    String getRepassword();

    void initiateRegister();

    void registerSuccess(int registerCode, RegisterResult registerResult);

    void registerFailed(int registerCode, String smg);
}
