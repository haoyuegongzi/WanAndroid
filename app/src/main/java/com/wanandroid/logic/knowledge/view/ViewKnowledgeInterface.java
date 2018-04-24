package com.wanandroid.logic.knowledge.view;

import com.wanandroid.logic.knowledge.moudle.KnowledgeBean;

import io.reactivex.disposables.Disposable;

/**
 * 作者：chen1 on 2018/3/23 17
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public interface ViewKnowledgeInterface {
    void requestDataSuccess(int requestCode, KnowledgeBean knowledgeBean, Disposable disposableKnowledge);

    void requestDataFailed(int requestCode, String msg, Disposable disposableKnowledge);
}
