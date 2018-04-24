package com.wanandroid.logic.knowledge.moudle;

import com.wanandroid.logic.knowledge.presenter.KnowledgeCallBack;

import io.reactivex.disposables.Disposable;

/**
 * 作者：chen1 on 2018/3/23 17
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public interface MoudleKnowledgeInterface {

    void doRequestKnowledgeData(String url, Disposable disposableKnowledge,  KnowledgeCallBack callBack);
}
