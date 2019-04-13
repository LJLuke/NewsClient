package com.example.luke.newsclient.base;

import com.example.luke.newsclient.netWork.RequestManager;
import com.example.luke.newsclient.netWork.RetrofitService;

public class BaseModel {
    protected RetrofitService mRetrofitService;
    protected RetrofitService getRetrofitService(){
        return mRetrofitService = RequestManager.getInstance().getService();
    }
}
