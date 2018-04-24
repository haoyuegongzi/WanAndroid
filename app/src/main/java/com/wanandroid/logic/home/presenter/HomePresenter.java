package com.wanandroid.logic.home.presenter;

import com.wanandroid.common.CommonVariable;
import com.wanandroid.logic.home.moudle.ArticleListBean;
import com.wanandroid.logic.home.moudle.BannerBean;
import com.wanandroid.logic.home.moudle.HomeMoudleImpl;
import com.wanandroid.logic.home.moudle.MoudleHomeInterface;
import com.wanandroid.logic.home.view.ViewHomeInterface;
import com.wanandroid.utils.auxiliary.StringUtils;

/**
 * 作者：chen1 on 2018/3/21 11
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class HomePresenter {

    public MoudleHomeInterface moudleHomeInterface;
    public ViewHomeInterface viewHomeInterface;

    public HomePresenter(ViewHomeInterface homeViewInterface){
        this.viewHomeInterface = homeViewInterface;
        moudleHomeInterface = new HomeMoudleImpl();
    }

    public void requestBannerData(String sonUrl){
        if (StringUtils.isTrimEmpty(sonUrl)) {
            viewHomeInterface.reuqestBannerDataFailed(CommonVariable.REQUESTCODE_PARAMS_ERROR, CommonVariable.REQUEST__PARAMS_ERROR);
            return;
        }
        moudleHomeInterface.reuestHomaeBanner(sonUrl, homeRequestCallBack);
    }

    public void requestArticleList(String id){
        moudleHomeInterface.requestHomeArticleList(id, homeRequestCallBack);
    }

    HomeRequestCallBack homeRequestCallBack = new HomeRequestCallBack(){
        @Override
        public void homeBannerRequestCallBack(BannerBean bannerBean, int requestCode) {
            if (requestCode == 0){
                viewHomeInterface.reuqestBannerDataSeccess(CommonVariable.REQUESTCODE_SECCESS, bannerBean);
            }
            if (requestCode == 1){
                viewHomeInterface.reuqestBannerDataFailed(CommonVariable.REQUESTCODE_FAILED, CommonVariable.REQUEST_MSG_FAILED);
            }
        }

        @Override
        public void homeArticleRequestCallBACK(ArticleListBean articleListBean, int reQuestCode) {
            if (reQuestCode == 0) {
                viewHomeInterface.requestArticleSuccess(articleListBean);
            }else {
                viewHomeInterface.requestArticleFailed(CommonVariable.REQUEST_MSG_FAILED);
            }
        }
    };



//    CollectionCallBack collectionCallBack = new CollectionCallBack(){};

}
