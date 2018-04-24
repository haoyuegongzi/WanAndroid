package com.wanandroid.logic.knowledge.presenter;

import com.wanandroid.common.CommonVariable;
import com.wanandroid.logic.knowledge.moudle.KnowledgeBean;
import com.wanandroid.logic.knowledge.moudle.MoudleKnowledgeImpl;
import com.wanandroid.logic.knowledge.moudle.MoudleKnowledgeInterface;
import com.wanandroid.logic.knowledge.view.ViewKnowledgeInterface;

import io.reactivex.disposables.Disposable;

/**
 * 作者：chen1 on 2018/3/23 17
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class KnowledgePresenter {
    MoudleKnowledgeInterface moudleKnowledge;
    ViewKnowledgeInterface viewKnowledge;

    public KnowledgePresenter(ViewKnowledgeInterface viewKnowledge){
        this.moudleKnowledge = new MoudleKnowledgeImpl();
        this.viewKnowledge = viewKnowledge;
    }

    public void doRequestKnowledgeData(Disposable disposableKnowledge){
        moudleKnowledge.doRequestKnowledgeData(CommonVariable.KNOWLEDGE, disposableKnowledge, knowledgeCallBack);
    }

    KnowledgeCallBack knowledgeCallBack = new KnowledgeCallBack(){
        @Override
        public void requestDataResult(int requestCode, KnowledgeBean knowledgeBean, Disposable disposableKnowledge) {
            switch (requestCode){
                case 0:
                    viewKnowledge.requestDataSuccess(requestCode, knowledgeBean, disposableKnowledge);
                    break;
                case 1:
                    viewKnowledge.requestDataFailed(requestCode, CommonVariable.REQUEST_MSG_FAILED, disposableKnowledge);
                    break;
                case 2:
                    viewKnowledge.requestDataFailed(requestCode, CommonVariable.REQUEST__PARAMS_ERROR, disposableKnowledge);
                    break;
                default:
                    break;
            }
        }
    };
}
