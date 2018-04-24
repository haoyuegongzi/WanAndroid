package com.wanandroid.logic.knowledge.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanandroid.R;
import com.wanandroid.common.base.BaseActivity;
import com.wanandroid.logic.knowledge.moudle.KnowledgeBean;
import com.wanandroid.logic.search.view.SearchActivity;
import com.wanandroid.utils.auxiliary.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：chen1 on 2018/3/23 17
 * E—Mail：chen126jie@163.com
 * TODO：
 *
 * @author chen1
 */
public class KnowledgeDetailActivity extends BaseActivity {
    KnowledgeDetailAdapter knowledgeDetailAdapter;
    KnowledgeBean.DataBean dataBean = null;

    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.IvKnowledgeSearch)
    ImageView IvKnowledgeSearch;
    @BindView(R.id.tvKnowledgeShared)
    ImageView tvKnowledgeShared;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_knowledge_detail);
        ButterKnife.bind(this);

        receiveData();
    }

    private void receiveData() {
        //总
        dataBean = getIntent().getParcelableExtra("dataBean");
        String sTitle = dataBean.name;
        tvTitle.setText(StringUtils.isTrimEmpty(sTitle) ? "" : sTitle);

        setTabLayoutAdapter();
    }

    private void setTabLayoutAdapter() {
        tabLayout.removeAllTabs();
        /**
         * TabMode:布局中Tab的行为模式，有两种值：MODE_FIXED 和 MODE_SCROLLABLE。
         * MODE_FIXED:固定tabs，并同时显示所有的tabs。
         * MODE_SCROLLABLE：可滚动tabs，显示一部分tabs。
         */
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        knowledgeDetailAdapter = new KnowledgeDetailAdapter(fragmentManager);
        knowledgeDetailAdapter.refreshData(dataBean);

        /** 注意:如果设置了setOnTabSelectedListener,则setupWithViewPager不会生效因为setupWithViewPager
         *  内部也是通过设置该监听来触发ViewPager的切换的.**/
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(knowledgeDetailAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(1);

        /**只需要修改TabLayout的addTab方法即可支持Drawable,以及自定义View**/
//        for (int i = 0; i < 20; i++) {
//            /**1.支持添加字符串文本tab:**/
//            tabLayout.addTab(tabLayout.newTab().setText("TAB" + i));
//            /**2.支持添加图片tab:**/
//            tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
//            /**3.支持添加View**/
//            View tabView = View.inflate(this, R.layout.adapter_tag_item, null);
//
//            ((TextView) tabView.findViewById(R.id.tvFlowLayout)).setText("TAB" + i);
//            tabLayout.addTab(tabLayout.newTab().setCustomView(tabView));
//        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // tab未选中
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // 再次选中tab的逻辑
            }
        });
    }

    /** 要替换默认的选中效果,只能通过添加自定义View作为tab.这样可以很方便的处理View的选中和未选中状态.
        同时我们还要隐藏默认的下划线,这个怎么处理呢,这里有个很好的建议.我们可以通过设置下滑线的颜色和tab的背景
        颜色一致,这样就看不出来了.例如下面我们要实现选中tab的效果是将tab放大1.3倍,没有选中则恢复原来比例 **/
//    private void isSelected(TabLayout.Tab tab, boolean isSelected) {
//        View view = tab.getCustomView();
//        if (null != view) {
//            view.setScaleX(isSelected ? 1.3f : 1.0f);
//            view.setScaleY(isSelected ? 1.3f : 1.0f);
//        }
//    }

    @OnClick({R.id.tvBack, R.id.IvKnowledgeSearch, R.id.tvKnowledgeShared})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvBack:
                onBackPressed();
                finish();
                break;
            case R.id.IvKnowledgeSearch:
                startActivity(new Intent(KnowledgeDetailActivity.this, SearchActivity.class));
                break;
            case R.id.tvKnowledgeShared:

                break;
            default:
                break;
        }
    }
}
