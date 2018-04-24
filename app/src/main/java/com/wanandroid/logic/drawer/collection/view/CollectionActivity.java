package com.wanandroid.logic.drawer.collection.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wanandroid.R;
import com.wanandroid.common.CommonVariable;
import com.wanandroid.common.base.BaseActivity;
import com.wanandroid.logic.drawer.collection.presenter.CollectionPresenter;
import com.wanandroid.logic.home.moudle.ArticleListBean;
import com.wanandroid.logic.home.view.ArticleListAdapter;
import com.wanandroid.logic.knowledge.detail.detailfragment.moudle.ItemDetail;
import com.wanandroid.utils.auxiliary.AppManager;
import com.wanandroid.utils.auxiliary.ConversionData;
import com.wanandroid.utils.auxiliary.StringUtils;
import com.wanandroid.utils.viewutils.ToastInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author chen1
 */
public class CollectionActivity extends BaseActivity implements ViewCollectionInterface {
    int id = 0;
    String flag = "CollectionActivity";
    String loadFlag = "";
    ArticleListAdapter collectionAdapter;
    CollectionPresenter collectionPresenter;
    List<ItemDetail> collectionArticle = new ArrayList<>();

    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rvHomeArticleList)
    RecyclerView rvHomeArticleList;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout smartRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvHomeArticleList.setLayoutManager(layoutManager);
        collectionPresenter = new CollectionPresenter(this);
        collectionPresenter.requestCollectListData(String.valueOf(id));
        setSmartRefreshLayoutListener();
    }

    private void setSmartRefreshLayoutListener() {
        smartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                id++;
                loadFlag = "LoadMore";
                refreshLayout.finishLoadMore(1500);
                collectionPresenter.requestCollectListData(String.valueOf(id));
            }
        });
        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadFlag = "Refresh";
                refreshLayout.finishRefresh(1500);
                collectionPresenter.requestCollectListData(CommonVariable.ARTICLELISTURL);
            }
        });
    }

    @OnClick(R.id.tvBack)
    public void onViewClicked() {
        onBackPressed();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void requestCollectArticleSuccess(int requestCode, ArticleListBean collectionBean) {
        List<ArticleListBean.DataBean.DatasBean> collectionList = collectionBean.data.datas;
        if ("Refresh".equals(loadFlag) || StringUtils.isTrimEmpty(loadFlag)) {
            collectionArticle.clear();
            collectionArticle = ConversionData.homeArticle(collectionList);
        }
        if ("LoadMore".equals(loadFlag)) {
            collectionArticle.addAll(ConversionData.homeArticle(collectionList));
        }

        if (collectionAdapter == null) {
            collectionAdapter = new ArticleListAdapter(this, collectionArticle, flag);
            rvHomeArticleList.setAdapter(collectionAdapter);
        }else {
            collectionAdapter.refreshAdapter(collectionArticle, flag);
        }
    }

    @Override
    public void requestCollectArticleFailed(int requestCode, String msg) {
        ToastInfo.longToast(this, msg);
    }
}
