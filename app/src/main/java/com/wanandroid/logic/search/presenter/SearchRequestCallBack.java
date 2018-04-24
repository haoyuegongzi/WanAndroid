package com.wanandroid.logic.search.presenter;

import com.wanandroid.logic.search.moudle.SearchBean;

/**
 *
 * @author Administrator
 * @date 2018/4/10
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public interface SearchRequestCallBack {
    void requestDataSuccess(int requestCode, SearchBean searchBean);
    void requestDataFailed(int requestCode, String msg);
}
