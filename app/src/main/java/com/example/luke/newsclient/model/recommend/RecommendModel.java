package com.example.luke.newsclient.model.recommend;

import com.example.luke.newsclient.base.BaseModel;
import com.example.luke.newsclient.entity.News;
import com.example.luke.newsclient.netWork.ApiWrapper;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RecommendModel extends BaseModel{
    public void getRecommendNews(String type,Observer<List<News>> observer){
        getRetrofitService().getNewsList("http://v.juhe.cn/toutiao/index",type,"6e4cc466e6e294410e68955f3c511ec7")
                .map(new Function<ApiWrapper<ApiWrapper.ResultBean<List<News>>>, List<News>>() {
                    @Override
                    public List<News> apply(ApiWrapper<ApiWrapper.ResultBean<List<News>>> resultBeanApiWrapper) throws Exception {
                        return resultBeanApiWrapper.getResult().getData();
                    }
                }).subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
