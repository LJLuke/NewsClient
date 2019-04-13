package com.example.luke.newsclient.netWork;

public class GhApiWrapper<T>{

    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getData() {
        return results;
    }

    public void setData(T data) {
        this.results = data;
    }
}
