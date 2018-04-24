//package com.wanandroid.logic.bannerdetail;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//
//import com.wanandroid.R;
//import com.wanandroid.logic.home.moudle.BannerBean;
//import com.wanandroid.utils.viewutils.WebViewUtils;
//
//import butterknife.ButterKnife;
//
///**
// * @author chen1
// */
//public class BannerDetailActivity extends AppCompatActivity {
//
//    WebView wbBannerDetail;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_banner_detail);
//        ButterKnife.bind(this);
//        BannerBean.DataBean bannerItem = getIntent().getParcelableExtra("bannerItem");
//
//        wbBannerDetail = findViewById(R.id.wbBannerDetail);
//
//        WebSettings webSettings = wbBannerDetail.getSettings();
//        WebViewUtils.webViewSetting(webSettings, true, false, this);
//        WebViewUtils.setViewClient(wbBannerDetail);
//        WebViewUtils.setChromeClient(wbBannerDetail, this);
//
//        /**url为https格式的时候，无法加载，后面优化**/
//        wbBannerDetail.loadUrl(bannerItem.url);
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }
//}
