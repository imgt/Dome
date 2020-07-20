package com.lg.dome.mvvms.api;

import com.lg.dome.mvvms.viewmodel.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by lqj on 2020/7/20.
 */
public interface ApiService {
    String BASE_URL = "https://news-at.zhihu.com/api/4/";

    String JUE_JIN_BASE_URL = "http://timeline-merger-ms.juejin.im/v1/";

    /**
     * 测试接口
     */
    @GET("news/latest")
    Observable<NewsBean> news();



}
