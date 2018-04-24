package com.wanandroid.logic.knowledge.presenter;

import com.wanandroid.logic.knowledge.moudle.KnowledgeBean;

import io.reactivex.disposables.Disposable;

/**
 * 作者：chen1 on 2018/3/23 17
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public interface KnowledgeCallBack {
    void requestDataResult(int requestCode, KnowledgeBean knowledgeBean, Disposable disposableKnowledge);
}
