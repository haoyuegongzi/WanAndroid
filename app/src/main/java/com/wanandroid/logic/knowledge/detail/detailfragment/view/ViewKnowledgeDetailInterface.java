package com.wanandroid.logic.knowledge.detail.detailfragment.view;

import com.wanandroid.logic.knowledge.detail.detailfragment.moudle.KnowledgeDetailBean;

/**
 * 作者：Created by Administrator on 2018/4/3.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public interface ViewKnowledgeDetailInterface {

    void requestDataSuccess(int requestCode, KnowledgeDetailBean knowledgeDetailBean);
    void requestDataFailed(int requestCode, String msg);

    void doRequestData();

}
