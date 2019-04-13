package com.example.luke.newsclient.base;

public class BasePresenter<V> implements IBasePresenter<V>{

    private V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public V getView(){
        return view;
    }
}
