package com.wanandroid.logic.homearticle;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.wanandroid.R;
import com.wanandroid.common.base.BaseActivity;
import com.wanandroid.logic.home.moudle.BannerBean;
import com.wanandroid.logic.knowledge.detail.detailfragment.moudle.ItemDetail;
import com.wanandroid.utils.auxiliary.StringUtils;
import com.wanandroid.utils.viewutils.WebViewUtils;

/**
 * @author chen1
 */
public class LoadArticleActivity extends BaseActivity {

    WebView wbArticleDetail;
    String urlLink;
    ItemDetail articleInfo = null;
    BannerBean.DataBean bannerItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_load_article);

        wbArticleDetail = findViewById(R.id.wbArticleDetail);
        String flag = getIntent().getStringExtra("flag");
        Log.i("TAG", "LoadArticleActivity → → → → → onCreate: flag ===" + flag);
        switch (flag) {
            case "HomeFragment":
                articleInfo = getIntent().getParcelableExtra("HomeFragment");
                urlLink = articleInfo.link;
                break;
            case "DetailFragment":
                articleInfo = getIntent().getParcelableExtra("DetailFragment");
                urlLink = articleInfo.link;
                break;
            case "SearchActivity":
                articleInfo = getIntent().getParcelableExtra("SearchActivity");
                urlLink = articleInfo.link;
                break;
            case "bannerItem":
                bannerItem = getIntent().getParcelableExtra("bannerItem");
                urlLink = bannerItem.url;
                break;
            default:
                break;
        }
        if (StringUtils.isTrimEmpty(urlLink)) {
            urlLink = "http://www.wanandroid.com/";
            Toast.makeText(this, "参数异常， url连接为空", Toast.LENGTH_SHORT).show();
        }

        WebSettings webSettings = wbArticleDetail.getSettings();
        WebViewUtils.webViewAttribute(webSettings, wbArticleDetail);
        WebViewUtils.setViewClient(wbArticleDetail);
        WebViewUtils.setChromeClient(wbArticleDetail, this);
        /**url为https格式的时候，无法加载，后面优化**/
        wbArticleDetail.loadUrl(urlLink);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
