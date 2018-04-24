package com.wanandroid.logic.search.presenter;

import com.wanandroid.logic.search.moudle.MoudleSearchImpl;
import com.wanandroid.logic.search.moudle.MoudleSearchInterface;
import com.wanandroid.logic.search.moudle.SearchBean;
import com.wanandroid.logic.search.moudle.SearchGreenDaoBean;
import com.wanandroid.logic.search.view.ViewSearchInterface;
import com.wanandroid.utils.auxiliary.GreenDaoUtils;

import java.util.List;

/**
 *
 * @author Administrator
 * @date 2018/4/9
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class SearchPresenter {
    MoudleSearchInterface moudleSearchInterface;
    ViewSearchInterface viewSearchInterface;

    public SearchPresenter(ViewSearchInterface viewSearchInterface){
        moudleSearchInterface = new MoudleSearchImpl();
        this.viewSearchInterface = viewSearchInterface;
    }

    public void requestSearchData(String id, String keyWord){
        moudleSearchInterface.requestSearchData(id, keyWord, searchRequestCallBack);
    }

    SearchRequestCallBack searchRequestCallBack = new SearchRequestCallBack(){
        @Override
        public void requestDataSuccess(int requestCode, SearchBean searchBean) {
            viewSearchInterface.requestDataSuccess(requestCode, searchBean);
        }

        @Override
        public void requestDataFailed(int requestCode, String msg) {
            viewSearchInterface.requestDataFailed(requestCode, msg);
        }
    };

    public List<SearchGreenDaoBean> dealHostory(GreenDaoUtils greenDaoUtils, String keyWord){
        List<SearchGreenDaoBean> list = greenDaoUtils.querryByCharacter(keyWord);
        if (null == list || list.size() == 0) {
            SearchGreenDaoBean searchGreenDaoBean = new SearchGreenDaoBean();
            searchGreenDaoBean.setName(keyWord);
            greenDaoUtils.insertOne(searchGreenDaoBean);
            list.add(searchGreenDaoBean);
        }else {
            int position = -1;
            for (int i = 0; i < list.size(); i++) {
                if(keyWord.equalsIgnoreCase(list.get(i).getName())){
                    position = i;
                }
            }
            list.remove(position);
            SearchGreenDaoBean searchGreenDaoBean = new SearchGreenDaoBean();
            searchGreenDaoBean.setName(keyWord);
            list.add(0, searchGreenDaoBean);
        }
        list = greenDaoUtils.querryAll();
        return list;
    }

}
