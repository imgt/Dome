package com.lg.dome.mvc;

import android.graphics.Bitmap;

/**
 * Created by lqj on 2020/7/20.
 */
public class ImageBean {
    // 网络图片地址
    private String requestPath;
    // 结果返回bitmap对象
    private Bitmap bitmap;

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}