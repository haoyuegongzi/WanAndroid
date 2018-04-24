package com.wanandroid.common.base;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：Created by Administrator on 2018/4/10.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class CustomInterceptor implements Interceptor {

    //静态请求头
    /**
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("Accept", "application/vnd.yourapi.v1.full+json")
                .header("Content-Type", "zhoujian_retrofit")
                .header("Referer", "application/vnd.yourapi.v1.full+json")
                .header("User-Agent", "zhoujian_retrofit")
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}
