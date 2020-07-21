package com.lg.dome.mvvms.api;

/**
 * Created by lqj on 2020/7/21.
 */
public class DataState<T>{
    private T data;
    private Boolean isSuccess;
    private String error;
    private int cod;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return "DataWrapper{" +
                "data=" + data +
                ", isSuccess=" + isSuccess +
                ", error='" + error + '\'' +
                ", cod=" + cod +
                '}';
    }
}
