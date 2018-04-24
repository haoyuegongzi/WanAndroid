package com.wanandroid.logic.knowledge.detail.detailfragment.presenter;

import com.wanandroid.common.CommonVariable;
import com.wanandroid.logic.knowledge.detail.detailfragment.moudle.KnowledgeDetailBean;
import com.wanandroid.logic.knowledge.detail.detailfragment.moudle.MoudleKnowledgeDetailImpl;
import com.wanandroid.logic.knowledge.detail.detailfragment.moudle.MoudleKnowledgeDetailInterface;
import com.wanandroid.logic.knowledge.detail.detailfragment.view.ViewKnowledgeDetailInterface;

/**
 * 作者：chen1 on 2018/3/28 12
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class KnowledgeDetailPresenter {
    ViewKnowledgeDetailInterface viewKnowledgeDetailInterface;
    MoudleKnowledgeDetailInterface moudleKnowledgeDetailInterface;


    public KnowledgeDetailPresenter(ViewKnowledgeDetailInterface viewKnowledgeDetailInterface){
        this.viewKnowledgeDetailInterface = viewKnowledgeDetailInterface;
        moudleKnowledgeDetailInterface = new MoudleKnowledgeDetailImpl();

    }

    public void requestKnowledgeDetailData(String page, String id){
        moudleKnowledgeDetailInterface.requestKnowledgeDetailData(page, id, knowledgeDetailCallBack);
    }

    KnowledgeDetailCallBack knowledgeDetailCallBack = new KnowledgeDetailCallBack() {

        @Override
        public void requestDataResult(int requesCode, KnowledgeDetailBean knowledgeDetailBean) {
            switch (requesCode){
                case CommonVariable.REQUESTCODE_SECCESS:
                    viewKnowledgeDetailInterface.requestDataSuccess(CommonVariable.REQUESTCODE_SECCESS, knowledgeDetailBean);
                    break;
                case CommonVariable.REQUESTCODE_FAILED:
                    viewKnowledgeDetailInterface.requestDataFailed(CommonVariable.REQUESTCODE_FAILED, CommonVariable.REQUEST_MSG_FAILED);
                    break;
                case CommonVariable.REQUESTCODE_PARAMS_ERROR:
                    viewKnowledgeDetailInterface.requestDataFailed(CommonVariable.REQUESTCODE_PARAMS_ERROR, CommonVariable.REQUEST__PARAMS_ERROR);
                    break;
                default:
                    break;
            }
        }
    };
    
//    public List<ItemDetail> knowledgeDetailList(List<KnowledgeDetailBean.DataBean.DatasBean> knowledgeDetailList){
//        List<ItemDetail> itemList = new ArrayList<>();
//        for (int i = 0; i < knowledgeDetailList.size(); i++) {
//            ItemDetail itemDetail = new ItemDetail();
//            itemDetail.apkLink = knowledgeDetailList.get(i).apkLink;
//            itemDetail.author = knowledgeDetailList.get(i).author;
//            itemDetail.chapterId = knowledgeDetailList.get(i).chapterId;
//            itemDetail.chapterName = knowledgeDetailList.get(i).chapterName;
//            itemDetail.collect = knowledgeDetailList.get(i).collect;
//            itemDetail.courseId = knowledgeDetailList.get(i).courseId;
//            itemDetail.desc = knowledgeDetailList.get(i).desc;
//            itemDetail.envelopePic = knowledgeDetailList.get(i).envelopePic;
//            itemDetail.fresh = knowledgeDetailList.get(i).fresh;
//            itemDetail.id = knowledgeDetailList.get(i).id;
//            itemDetail.link = knowledgeDetailList.get(i).link;
//            itemDetail.niceDate = knowledgeDetailList.get(i).niceDate;
//            itemDetail.origin = knowledgeDetailList.get(i).origin;
//            itemDetail.projectLink = knowledgeDetailList.get(i).projectLink;
//            itemDetail.publishTime = String.valueOf(knowledgeDetailList.get(i).publishTime);
//            itemDetail.superChapterId = knowledgeDetailList.get(i).superChapterId;
//            itemDetail.superChapterName = knowledgeDetailList.get(i).superChapterName;
//            itemDetail.title = knowledgeDetailList.get(i).title;
//            itemDetail.type = knowledgeDetailList.get(i).type;
//            itemDetail.visible = knowledgeDetailList.get(i).visible;
//            itemDetail.zan = knowledgeDetailList.get(i).zan;
//
//            itemList.add(itemDetail);
//        }
//
//        return itemList;
//    }
}
