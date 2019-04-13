package com.example.luke.newsclient.base;

public interface IBasePresenter<V> {
    void attachView(V view);
    void detachView();
}
