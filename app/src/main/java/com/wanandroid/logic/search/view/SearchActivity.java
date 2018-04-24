package com.wanandroid.logic.search.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wanandroid.R;
import com.wanandroid.common.CommonVariable;
import com.wanandroid.common.base.BaseActivity;
import com.wanandroid.logic.firehot.moudle.FireHotSearchBean;
import com.wanandroid.logic.home.view.ArticleListAdapter;
import com.wanandroid.logic.knowledge.detail.detailfragment.moudle.ItemDetail;
import com.wanandroid.logic.search.moudle.SearchBean;
import com.wanandroid.logic.search.moudle.SearchGreenDaoBean;
import com.wanandroid.logic.search.presenter.SearchPresenter;
import com.wanandroid.utils.auxiliary.ConversionData;
import com.wanandroid.utils.auxiliary.GreenDaoUtils;
import com.wanandroid.utils.auxiliary.StringUtils;
import com.wanandroid.utils.flowlayout.FlowLayout;
import com.wanandroid.utils.flowlayout.TagAdapter;
import com.wanandroid.utils.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen1
 */
public class SearchActivity extends BaseActivity implements ViewSearchInterface, View.OnClickListener {

    int page = 0;
    String keyWord;
    String loadFlag = "";
    String flag = "SearchActivity";

    List<SearchGreenDaoBean> searchRecord;
    GreenDaoUtils greenDaoUtils;
    SearchPresenter searchPresenter;
    ArticleListAdapter searchAdapter = null;

    ImageView ivSearchBack, ivSearchClear;
    TextView tvStartSearch;
    TextView tvClearHoistroy;
    EditText etSearch;
    TagFlowLayout tagSearchFlowLayout;
    RecyclerView rvSearchList;
    SmartRefreshLayout smartRefresh;

    List<ItemDetail> searchItemDetailList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_search);

        searchPresenter = new SearchPresenter(this);
        greenDaoUtils = GreenDaoUtils.getInstance();
        greenDaoUtils.init(this);
        greenDaoUtils.setUpdataBase();
        greenDaoUtils.initLogInfo();

        ivSearchBack = findViewById(R.id.ivSearchBack);
        etSearch = findViewById(R.id.etSearch);
        ivSearchClear = findViewById(R.id.ivSearchClear);
        tagSearchFlowLayout = findViewById(R.id.tagSearchFlowLayout);
        rvSearchList = findViewById(R.id.rvSearchList);
        rvSearchList.setLayoutManager(getLayoutManager());
        smartRefresh = findViewById(R.id.smartRefresh);
        tvStartSearch = findViewById(R.id.tvStartSearch);
        tvClearHoistroy = findViewById(R.id.tvClearHoistroy);

        ivSearchBack.setOnClickListener(this);
        ivSearchClear.setOnClickListener(this);
        tvStartSearch.setOnClickListener(this);
        tvClearHoistroy.setOnClickListener(this);

        searchRecord = new ArrayList<>();

        //进入搜索页面后，加载初始(默认)数据
        loadInitialData();
    }

    private void loadInitialData(){
        //获取从热门关注————大家都在搜传过来的数据
        FireHotSearchBean.DataBean fireHotBean = getIntent().getParcelableExtra("fireHotBean");
        //从热门关注————大家都在搜传过来的数据不为空，表示界面是从热门关注————大家都在搜跳转过来来的；
        if (fireHotBean != null) {
            Log.i(TAG, "loadInitialData: commonWebSite.name ==="  + fireHotBean.name);
            setFlowLayoutAdapter(fireHotBean.name);
            initiateRequestData(String.valueOf(page), fireHotBean.name);

        //从热门关注————大家都在搜传过来的数据为空，表示界面是点击搜索按钮后跳转过来来的；
        }else {
            loadSearchRecord();
        }
    }

    /**进入界面就查询加载历史搜索记录*/
    private void loadSearchRecord(){
        List<SearchGreenDaoBean> list = greenDaoUtils.querryAll();
        if (list != null && list.size() > 0) {
            setFlowLayoutData(list);
        }
    }

    @Override
    public String getKeyWord() {
        return etSearch.getText().toString().trim();
    }

    @Override
    public void initiateRequestData(String id, final String keyWord) {
        searchPresenter.requestSearchData(id, keyWord);

        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                loadFlag = "Refresh";
                smartRefresh.finishRefresh(1500);
                searchPresenter.requestSearchData(CommonVariable.ARTICLELISTURL, keyWord);
            }
        });
        smartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                loadFlag = "LoadMore";
                smartRefresh.finishLoadMore(1500);
                searchPresenter.requestSearchData(String.valueOf(page), keyWord);
            }
        });
    }

    @Override
    public void requestDataSuccess(int requestCode, SearchBean searchBean) {
        List<SearchBean.DataBean.DatasBean> searchList = searchBean.getData().getDatas();
        //下拉刷新数据
        if ("Refresh".equals(loadFlag) || StringUtils.isTrimEmpty(loadFlag)) {
            searchItemDetailList.clear();
            searchItemDetailList = ConversionData.searchList(searchList);
        }
        //上拉加载更多，新获取的数据与之前的数据合并在一起
        if ("LoadMore".equals(loadFlag)) {
            searchItemDetailList.addAll(ConversionData.searchList(searchList));
        }

        if (searchAdapter == null) {
            searchAdapter = new ArticleListAdapter(this, searchItemDetailList, flag);
            rvSearchList.setAdapter(searchAdapter);
        }else {
            searchAdapter.refreshAdapter(searchItemDetailList, flag);
        }
    }

    @Override
    public void requestDataFailed(int requestCode, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivSearchBack:
                onBackPressed();
                finish();
                break;
            case R.id.ivSearchClear:
                etSearch.setText("");
                break;
            case R.id.tvStartSearch:
                keyWord = getKeyWord();
                Log.i(TAG, "onClick: keyWord ===" + keyWord);
                if (!StringUtils.isTrimEmpty(keyWord)) {
                    /**数据库、历史记录并刷新*/
                    setFlowLayoutAdapter(keyWord);
                    tagSearchFlowLayout.getAdapter().notifyDataChanged();
                    /**网络请求数据*/
                    //第一次搜索，拉取数据都是从第一页（0）开始
                    page = 0;
                    initiateRequestData(String.valueOf(page), keyWord);
                }else {
                    Toast.makeText(this, "您输入的搜索关键字为空", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tvClearHoistroy:
                greenDaoUtils.deleteAll();
                loadSearchRecord();
                tagSearchFlowLayout.getAdapter().notifyDataChanged();
                break;
            default:
                break;
        }
    }

    @Override
    public void setFlowLayoutAdapter(String keyWord){
        List<SearchGreenDaoBean> list = searchPresenter.dealHostory(greenDaoUtils, keyWord);
        setFlowLayoutData(list);
    }

    private void setFlowLayoutData(final List<SearchGreenDaoBean> list){
        tagSearchFlowLayout.setAdapter(new TagAdapter<SearchGreenDaoBean>(list) {
            @Override
            public View getView(FlowLayout parent, int position, SearchGreenDaoBean searchGreenDaoBean) {
                TextView textView = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.adapter_tag_knowledge_item,
                        tagSearchFlowLayout, false);
                textView.setBackground(getResources().getDrawable(R.drawable.home_article_item_shape));
                textView.setText(list.get(position).getName());
                return textView;
            }
        });

        tagSearchFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Log.i(TAG, "onTagClick: list.get(position) ====" + list.get(position).getName());
                keyWord = list.get(position).getName();
                //第一次搜索，拉取数据都是从第一页（0）开始
                page = 0;
                initiateRequestData(String.valueOf(page), keyWord);
                return true;
            }
        });
    }

    private LinearLayoutManager  getLayoutManager(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return linearLayoutManager;
    }
}
