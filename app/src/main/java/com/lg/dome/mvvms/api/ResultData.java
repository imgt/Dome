package com.lg.dome.mvvms.api;

import androidx.lifecycle.MutableLiveData;

import java.io.Serializable;

/**
 * Created by lqj on 2020/7/21.
 */
public class ResultData<T> implements Serializable {
    private T data;
    private int status=-111;
    private int timestamp;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}

