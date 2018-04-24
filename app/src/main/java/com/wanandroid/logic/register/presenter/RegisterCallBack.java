package com.wanandroid.logic.register.presenter;

import com.wanandroid.logic.register.moudle.RegisterResult;

/**
 * 作者：Created by Administrator on 2018/4/21.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public interface RegisterCallBack {

    void registerSuccess(int registerCode, RegisterResult registerResult);

    void registerFailed(int registerCode, String smg);
}
