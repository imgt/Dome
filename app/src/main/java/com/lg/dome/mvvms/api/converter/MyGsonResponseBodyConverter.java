package com.lg.dome.mvvms.api.converter;

import com.google.gson.Gson;
import com.lg.dome.mvvms.api.ResultData;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by lqj on 2020/7/21.
 */
public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    /**
     * 针对数据返回成功、错误不同类型字段处理
     */
    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            ResultData result = gson.fromJson(response, ResultData.class);
            int code = result.getStatus();
            if (code == -111) {
                return gson.fromJson(response, type);
            } else if (code == 1) {
                return gson.fromJson(response, type);
            } else {
                //Log.d("HttpManager", "err==：" + response);
                ResultData<String> errResponse = gson.fromJson(response, ResultData.class);
                //    Log.d("HttpManager", "err==：" + errResponse.getData());
                return (T) errResponse;
//                if (code == 410) {
//                   throw new Exception(errResponse.getData());
//                } else {
//                   throw new ResultException(errResponse.getErrorMessage(), code);
//                }
            }
        } finally {
            value.close();
        }
    }
}
