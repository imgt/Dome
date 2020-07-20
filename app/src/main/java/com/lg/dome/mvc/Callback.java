package com.lg.dome.mvc;

/**
 * Created by lqj on 2020/7/20.
 */
public interface Callback {
    /**
     * @param resultCode 请求结果返回的标识码
     * @param bitmap     Model层数据中Bitmap对象，用户Control刷新View
     */
    void callback(int resultCode, ImageBean bitmap);
}
