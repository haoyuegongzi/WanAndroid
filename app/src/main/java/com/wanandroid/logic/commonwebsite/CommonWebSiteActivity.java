package com.wanandroid.logic.commonwebsite;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.wanandroid.R;
import com.wanandroid.common.base.BaseActivity;
import com.wanandroid.logic.firehot.moudle.CommonWebSiteBean;
import com.wanandroid.utils.viewutils.WebViewUtils;

/**
 * @author Administrator
 */
public class CommonWebSiteActivity extends BaseActivity {
    WebView wbCommonWebSite;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_common_web_site);
        wbCommonWebSite = findViewById(R.id.wbCommonWebSite);
        tvTitle = findViewById(R.id.tvTitle);

        CommonWebSiteBean.DataBean commonWebSite = getIntent().getParcelableExtra("fireHotBean");

        WebSettings webSettings = wbCommonWebSite.getSettings();
        WebViewUtils.webViewAttribute(webSettings, wbCommonWebSite);
        WebViewUtils.setViewClient(wbCommonWebSite);
        WebViewUtils.setChromeClient(wbCommonWebSite, this);

        if (wbCommonWebSite != null) {
            String urlLink = commonWebSite.link;
            wbCommonWebSite.loadUrl(urlLink);
            tvTitle.setText(commonWebSite.name);
        }

        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }
}
