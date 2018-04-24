package com.wanandroid.logic.search.moudle;

import com.wanandroid.logic.search.presenter.SearchRequestCallBack;

/**
 *
 * @author Administrator
 * @date 2018/4/9
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public interface MoudleSearchInterface {

    void requestSearchData(String id, String keyWord, SearchRequestCallBack searchCallBack);


}
