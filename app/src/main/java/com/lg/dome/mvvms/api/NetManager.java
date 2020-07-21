package com.lg.dome.mvvms.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by lqj on 2020/7/20.
 */
public class NetManager extends BaseApi {

    /**
     * 静态内部类单例
     */
    private static class ApiHolder {
        private static NetManager api = new NetManager();
        private final static ApiService apiService = api.initRetrofit(ApiService.BASE_URL)
                .create(ApiService.class);
        private final static ApiService toApiService = api.initRetrofit(ApiService.JUE_JIN_BASE_URL)
                .create(ApiService.class);
    }

    public static ApiService getInstance() {
        return ApiHolder.apiService;
    }

    public static ApiService getToInstance() {
        return ApiHolder.toApiService;
    }

    /**
     * 做自己需要的操作
     */
    @Override
    protected OkHttpClient setClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
               // .addInterceptor(interceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }
}
