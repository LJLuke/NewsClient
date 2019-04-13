package com.example.luke.newsclient.model.zhihu;

import com.example.luke.newsclient.base.BaseModel;
import com.example.luke.newsclient.entity.GanHuo.GhNews;
import com.example.luke.newsclient.netWork.RequestManager;
import com.example.luke.newsclient.netWork.RetrofitService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ZhiHuNewsModel extends BaseModel{
    public void getZhiHuNews(String id,int count,int page,Observer<List<GhNews>> observer){
        getRetrofitService().getGhNewsList(id,count,page)
                .map(listGhApiWrapper -> listGhApiWrapper.getData()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
