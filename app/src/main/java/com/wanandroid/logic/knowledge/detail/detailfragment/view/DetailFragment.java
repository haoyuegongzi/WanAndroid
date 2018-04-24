package com.wanandroid.logic.knowledge.detail.detailfragment.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wanandroid.R;
import com.wanandroid.common.CommonVariable;
import com.wanandroid.common.base.BaseFragment;
import com.wanandroid.logic.home.view.ArticleListAdapter;
import com.wanandroid.logic.knowledge.detail.detailfragment.moudle.ItemDetail;
import com.wanandroid.logic.knowledge.detail.detailfragment.moudle.KnowledgeDetailBean;
import com.wanandroid.logic.knowledge.detail.detailfragment.presenter.KnowledgeDetailPresenter;
import com.wanandroid.logic.knowledge.moudle.KnowledgeBean;
import com.wanandroid.utils.auxiliary.ConversionData;
import com.wanandroid.utils.auxiliary.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：chen1 on 2018/3/23 17
 * E—Mail：chen126jie@163.com
 * TODO：
 *
 * @author chen1
 */
public class DetailFragment extends BaseFragment implements ViewKnowledgeDetailInterface {
    String TAG = "TAG";
    String id;
    String loadFlag = "";
    String flag = "DetailFragment";

    int page = 0;

    KnowledgeDetailPresenter knowledgeDetailPresenter;
    ArticleListAdapter articleListAdapter;
    KnowledgeBean.DataBean.ChildrenBean childrenBean;
    List<ItemDetail> itemDetailList = new ArrayList<>();
    Bundle bundle;

    View view;
    SmartRefreshLayout smartRefresh;
    RecyclerView rvHomeArticleList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        knowledgeDetailPresenter = new KnowledgeDetailPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail, container, false);

        smartRefresh = view.findViewById(R.id.smartRefresh);
        rvHomeArticleList = view.findViewById(R.id.rvHomeArticleList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvHomeArticleList.setLayoutManager(layoutManager);

        bundle = getArguments();
        childrenBean = bundle.getParcelable("childrenBean");
        id = childrenBean.id;

        return view;
    }

    @Override
    public void doRequestData(){
        knowledgeDetailPresenter.requestKnowledgeDetailData(CommonVariable.ARTICLELISTURL, id);

        smartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                loadFlag = "LoadMore";
                smartRefresh.finishLoadMore(1500);
                knowledgeDetailPresenter.requestKnowledgeDetailData(String.valueOf(page),id);
            }
        });
        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadFlag = "Refresh";
                smartRefresh.finishRefresh(1500);
                knowledgeDetailPresenter.requestKnowledgeDetailData(CommonVariable.ARTICLELISTURL,id);
            }
        });
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            //更新界面数据，如果数据还在下载中，就显示加载框
            doRequestData();
        } else {
            //关闭加载框
            smartRefresh.finishRefresh();
        }
    }

    @Override
    protected void onFragmentFirstVisible() {
        //去服务器下载数据
        doRequestData();
    }

    @Override
    public void requestDataSuccess(int requestCode, KnowledgeDetailBean knowledgeDetailBean) {
        List<KnowledgeDetailBean.DataBean.DatasBean> knowledgeDetailList = knowledgeDetailBean.data.datas;
        //刷新数据（界面已经加载过一次数据）
        if ("Refresh".equals(loadFlag) || StringUtils.isTrimEmpty(loadFlag)) {
            itemDetailList.clear();
            itemDetailList = ConversionData.ConversionknowledgeDetailBeanList(knowledgeDetailList);
        }
        //加载更多，新获取的数据与之前的数据合并在一起。
        if ("LoadMore".equals(loadFlag)) {
            itemDetailList.addAll(ConversionData.ConversionknowledgeDetailBeanList(knowledgeDetailList));
        }

        if (articleListAdapter == null) {
            articleListAdapter = new ArticleListAdapter(getActivity(), itemDetailList, flag);
            rvHomeArticleList.setAdapter(articleListAdapter);
        }else {
            articleListAdapter.refreshAdapter(itemDetailList, flag);
        }
    }

    @Override
    public void requestDataFailed(int requestCode, String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    public DetailFragment() {

    }
}
