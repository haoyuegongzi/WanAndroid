package com.wanandroid.logic.firehot.moudle;

/**
 * 作者：chen1 on 2018/3/23 09
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public interface MoudleFireHotInterface {
    void getHotSearchData(String url, MoudleFireHotCallBack hotCallBack);
    void getFireHotCommonWebSite(String url, MoudleFireHotCallBack hotCallBack);
}
