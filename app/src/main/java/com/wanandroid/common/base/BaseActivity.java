package com.wanandroid.common.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wanandroid.utils.auxiliary.AppManager;

/**
 * 作者：chen1 on 2018/3/16 16
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class BaseActivity extends AppCompatActivity {
    public static final String TAG = "TAG";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        AppManager.getAppManager().finishActivity(this);
    }
}
