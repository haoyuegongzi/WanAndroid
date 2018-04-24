package com.wanandroid.common.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.wanandroid.common.CommonVariable;
import com.wanandroid.logic.login.moudle.LogicGreenDaoBean;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：chen1 on 2018/3/16 16
 * E—Mail：chen126jie@163.com
 * TODO：
 *
 * @author chen1
 */

public class BaseApplication extends Application {

    public static Context context;
    public static LogicGreenDaoBean logicBean;
    public static ClearableCookieJar cookieJar;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //崩溃日志
        ImproveCrashHandler.getInstance().initVariable(this);
    }


    public static Retrofit getRetrofitObject(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonVariable.BASEURL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public static OkHttpClient getOkHttpClient() {
        /**
         * 日志显示级别
         * HttpLoggingInterceptor提供了4中控制打印信息类型的等级，分别是:
         * NONE:没有任何日志信息
         * BASIC:打印请求类型，URL，请求体大小，返回值状态以及返回值的大小
         * HEADERS:打印返回请求和返回值的头部信息，请求类型，URL以及返回值状态码
         * BODY:打印请求和返回值的头部和body信息
         */
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("TAG", "OkHttpClient ==== Message ===== " + message);
            }
        }).setLevel(level);

        //缓存目录
        final File baseDir = context.getCacheDir();
        final File cacheDir = new File(baseDir, "HttpResponseCache");
        //缓存可用大小为10M
        Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);

        cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));


        //定制OkHttp
        OkHttpClient client = new OkHttpClient.Builder()
                //OkHttp进行添加拦截器loggingInterceptor
                .addInterceptor(loggingInterceptor)
                //stetho,可以在chrome中查看请求(需要梯子翻墙)
                .addNetworkInterceptor(new StethoInterceptor())
                .cache(cache)
                .connectTimeout(CommonVariable.CONNECTTIMEOUT, TimeUnit.SECONDS)
                .readTimeout(CommonVariable.READTIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(CommonVariable.WRITETIMEOUT, TimeUnit.SECONDS)
                .cookieJar(cookieJar)
                .build();

        return client;
    }
}
