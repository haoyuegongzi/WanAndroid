package com.wanandroid.common.base;

/**
 * 作者：Created by Administrator on 2018/4/18.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class BaseLogicBean {

    public String errorCode;
    public String errorMsg;

    public String getErrorCode() {
        return errorCode == null ? "" : errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg == null ? "" : errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
