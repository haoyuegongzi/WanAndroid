package com.wanandroid.logic.firehot.presenter;

import com.wanandroid.common.CommonVariable;
import com.wanandroid.logic.firehot.moudle.CommonWebSiteBean;
import com.wanandroid.logic.firehot.moudle.FireHotSearchBean;
import com.wanandroid.logic.firehot.moudle.MoudleFireHotCallBack;
import com.wanandroid.logic.firehot.moudle.MoudleFireHotImpl;
import com.wanandroid.logic.firehot.moudle.MoudleFireHotInterface;
import com.wanandroid.logic.firehot.view.ViewFireHotInterface;

/**
 * 作者：chen1 on 2018/3/23 09
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class FireHotPresenter {
    ViewFireHotInterface viewFireHot;
    MoudleFireHotInterface moudleFireHot;

    public FireHotPresenter(ViewFireHotInterface viewFireHot){
        this.viewFireHot = viewFireHot;
        this.moudleFireHot = new MoudleFireHotImpl();
    }

    public void requestHotSearchData(){
        moudleFireHot.getHotSearchData(CommonVariable.HOT_SEARCH, moudleFireHotCallBack);
    }

    public void requestCommonUseWebSite(){
        moudleFireHot.getFireHotCommonWebSite(CommonVariable.COMMONWEBSITE, moudleFireHotCallBack);
    }

    MoudleFireHotCallBack moudleFireHotCallBack = new MoudleFireHotCallBack() {
        @Override
        public void fireHotSearchCallBack(int requestCode, FireHotSearchBean hotSearchBean) {
            if (requestCode == 0){
                viewFireHot.requestHotSearchDataSuccess(requestCode, hotSearchBean);
            }else if (requestCode == 1){
                viewFireHot.requestHotSearchDataFailed(requestCode, CommonVariable.REQUEST_MSG_FAILED);
            }else {
                viewFireHot.requestHotSearchDataFailed(requestCode, CommonVariable.REQUEST__PARAMS_ERROR);
            }
        }

        @Override
        public void firHotCommonWebSite(int requestCode, CommonWebSiteBean commonWebSiteBean) {
            if (requestCode == 0) {
                viewFireHot.requestCommonWebSiteSuccess(requestCode, commonWebSiteBean);
            }else if(requestCode == 1){
                viewFireHot.requestHotSearchDataFailed(requestCode, CommonVariable.REQUEST_MSG_FAILED);
            }else {
                viewFireHot.requestCommonWebSiteFailed(requestCode, CommonVariable.REQUEST__PARAMS_ERROR);
            }
        }
    };
}
