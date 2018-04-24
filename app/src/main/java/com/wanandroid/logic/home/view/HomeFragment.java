package com.wanandroid.logic.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.wanandroid.logic.home.moudle.ArticleListBean;
import com.wanandroid.logic.home.moudle.BannerBean;
import com.wanandroid.logic.home.presenter.HomePresenter;
import com.wanandroid.logic.homearticle.LoadArticleActivity;
import com.wanandroid.logic.knowledge.detail.detailfragment.moudle.ItemDetail;
import com.wanandroid.utils.auxiliary.ConversionData;
import com.wanandroid.utils.auxiliary.StringUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：chen1 on 2018/3/20 17
 * E—Mail：chen126jie@163.com
 * TODO：
 *
 * @author chen1
 */

public class HomeFragment extends Fragment implements ViewHomeInterface {
    private View view;
    private HomePresenter homePresenter;
    private ArticleListAdapter articleListAdapter;

    private int raticlePageId = 0;
    private String flag = "HomeFragment";
    private String loadFlag = "";

    private List<BannerBean.DataBean> bannerList;
    private List<String> bannerUrlList = new ArrayList<>();
    private List<String> bannerTitleList = new ArrayList<>();
    private List<ItemDetail> homeArticle = new ArrayList<>();

    Banner vpHomeBanner;
    SmartRefreshLayout smartRefresh;
    RecyclerView rvHomeArticleList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        vpHomeBanner = view.findViewById(R.id.vpHomeBanner);
        rvHomeArticleList = view.findViewById(R.id.rvHomeArticleList);
        smartRefresh = view.findViewById(R.id.smartRefresh);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvHomeArticleList.setLayoutManager(layoutManager);

        homePresenter = new HomePresenter(this);
        homePresenter.requestBannerData(CommonVariable.BANNERURL);
        homePresenter.requestArticleList(CommonVariable.ARTICLELISTURL);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void reuqestBannerDataSeccess(int requestCode, BannerBean bannerBean) {
        if (Integer.parseInt(bannerBean.errorCode) == 0 && StringUtils.isTrimEmpty(bannerBean.errorMsg)) {
            bannerList = bannerBean.data;
            for (int i = 0; i < bannerList.size(); i++) {
                bannerUrlList.add(bannerList.get(i).imagePath);
                bannerTitleList.add(bannerList.get(i).title);
            }
            //设置banner样式:水平+标题模式
            vpHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //设置图片加载器
            vpHomeBanner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            vpHomeBanner.setImages(bannerUrlList);
            //设置标题集合（当banner样式有显示title时）
            vpHomeBanner.setBannerTitles(bannerTitleList);
            //设置自动轮播，默认为true
            vpHomeBanner.isAutoPlay(true);
            //设置轮播时间
            vpHomeBanner.setDelayTime(1500);
            //设置指示器位置（当banner模式中有指示器时）
            vpHomeBanner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            vpHomeBanner.start();
            addBannerListener();
        } else {
            reuqestBannerDataFailed(CommonVariable.REQUESTCODE_PARAMS_ERROR, "数据解析失败，请稍候再试");
        }
    }

    private void addBannerListener() {
        vpHomeBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                BannerBean.DataBean bannerItem = bannerList.get(position);
                Intent intent = new Intent(getActivity(), LoadArticleActivity.class);
                intent.putExtra("bannerItem", bannerItem);
                intent.putExtra("flag", "bannerItem");
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void reuqestBannerDataFailed(int requestCode, String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void requestArticleSuccess(ArticleListBean articleListBean) {
        List<ArticleListBean.DataBean.DatasBean> articledata = articleListBean.data.datas;

        if ("Refresh".equals(loadFlag) || StringUtils.isTrimEmpty(loadFlag)) {
            homeArticle.clear();
            homeArticle = ConversionData.homeArticle(articledata);
        }
        if ("LoadMore".equals(loadFlag)) {
            homeArticle.addAll(ConversionData.homeArticle(articledata));
        }

        if (articleListAdapter == null) {
            articleListAdapter = new ArticleListAdapter(getActivity(), homeArticle, flag);
            rvHomeArticleList.setAdapter(articleListAdapter);
        }else {
            articleListAdapter.refreshAdapter(homeArticle, flag);
        }
        addDmartRefresh();
    }

    @Override
    public void requestArticleFailed(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    private void addDmartRefresh(){
        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadFlag = "Refresh";
                smartRefresh.finishRefresh(1500);
                homePresenter.requestArticleList(CommonVariable.ARTICLELISTURL);
            }
        });
        smartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                raticlePageId++;
                loadFlag = "LoadMore";
                smartRefresh.finishLoadMore(1500);
                homePresenter.requestArticleList(String.valueOf(raticlePageId));
            }
        });
    }

    public HomeFragment() {

    }
}
