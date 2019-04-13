package com.example.luke.newsclient.base;

public interface IBaseFragment {
    int bindLayout();
    void initView();
    void onRefreshFailure();
    void onLoadMoreFailure();
}
