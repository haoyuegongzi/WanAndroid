//package com.wanandroid.logic.firehotdetail;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.TextView;
//
//import com.wanandroid.R;
//import com.wanandroid.logic.firehot.moudle.FireHotSearchBean;
//
///**
// * @author chen1
// */
//public class FireHotDetailActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fire_hot_detail);
//
//        FireHotSearchBean.DataBean fireHotBean = getIntent().getParcelableExtra("fireHotBean");
//        TextView tvfireHotBean = findViewById(R.id.tvfireHotBean);
//        if (tvfireHotBean != null) {
//            tvfireHotBean.setText(fireHotBean.toString());
//        }
//    }
//}
