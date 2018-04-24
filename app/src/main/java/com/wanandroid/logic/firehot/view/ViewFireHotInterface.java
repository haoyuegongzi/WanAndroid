package com.wanandroid.logic.firehot.view;

import com.wanandroid.logic.firehot.moudle.CommonWebSiteBean;
import com.wanandroid.logic.firehot.moudle.FireHotSearchBean;

/**
 * 作者：chen1 on 2018/3/23 09
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public interface ViewFireHotInterface {
    void requestHotSearchDataSuccess(int requestCode, FireHotSearchBean searchBean);
    void requestHotSearchDataFailed(int requestCode, String msg);

    void requestCommonWebSiteSuccess(int requestCode, CommonWebSiteBean commonWebSiteBean);
    void requestCommonWebSiteFailed(int requestCode, String msg);
}
