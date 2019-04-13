package com.example.luke.newsclient.netWork;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    public static final String BASE_URL = "http://gank.io/api/xiandu/";
    private static final int TIMEOUT = 10;
    private Retrofit mRetrofit;
    private RetrofitService mService;
    private static RequestManager sRequestManager;

    private RequestManager(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mService = mRetrofit.create(RetrofitService.class);
    }
    public static RequestManager getInstance(){
        if (sRequestManager == null){
            synchronized (RequestManager.class){
                if (sRequestManager == null){
                    sRequestManager = new RequestManager();
                }
            }
        }
        return sRequestManager;
    }
    public RetrofitService getService(){
        return mService;
    }
}
