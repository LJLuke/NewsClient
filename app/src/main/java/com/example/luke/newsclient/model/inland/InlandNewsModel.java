package com.example.luke.newsclient.model.inland;

import com.example.luke.newsclient.base.BaseModel;
import com.example.luke.newsclient.entity.News;
import com.example.luke.newsclient.netWork.ApiWrapper;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class InlandNewsModel extends BaseModel {
    public void getInlandNews(String type,Observer<List<News>> observer){
        getRetrofitService().getNewsList("http://v.juhe.cn/toutiao/index",type,"6e4cc466e6e294410e68955f3c511ec7")
                .map(resultBeanApiWrapper -> resultBeanApiWrapper.getResult().getData()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
