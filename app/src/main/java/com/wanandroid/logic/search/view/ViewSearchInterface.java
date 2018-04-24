package com.wanandroid.logic.search.view;

import com.wanandroid.logic.search.moudle.SearchBean;

/**
 * 作者：Created by Administrator on 2018/4/9.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public interface ViewSearchInterface {

    String getKeyWord();
    void initiateRequestData(String id, String keyWord);
    void requestDataSuccess(int requestCode, SearchBean searchBean);
    void requestDataFailed(int requestCode, String msg);

    void setFlowLayoutAdapter(String keyWord);
}
