package com.wanandroid.logic.knowledge.detail.detailfragment.moudle;

import com.wanandroid.logic.knowledge.detail.detailfragment.presenter.KnowledgeDetailCallBack;

/**
 * 作者：Created by Administrator on 2018/4/3.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public interface MoudleKnowledgeDetailInterface {
    void requestKnowledgeDetailData(String page, String id, KnowledgeDetailCallBack callBack);
}
