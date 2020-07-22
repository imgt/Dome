package com.lg.dome.mvvms.api;

import com.lg.dome.mvvms.viewmodel.NewsBean;
import com.lg.dome.mvvms.viewmodel.DataBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by lqj on 2020/7/20.
 */
public interface ApiService {
    String BASE_URL = "https://news-at.zhihu.com/api/4/";

    String JUE_JIN_BASE_URL = "https://news-at.zhihu.com/api/4/";

    /**
     * 测试接口
     */
    @GET("news/latest")
    Observable<NewsBean> news();

    @GET("Mapi/Art/artIndex")
 Observable<ResultData<List<DataBean>>> art();

    @GET("news/latest")
    Observable<String> newstr();
}
