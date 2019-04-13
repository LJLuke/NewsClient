package com.example.luke.newsclient.model.searchResult;

import com.example.luke.newsclient.base.BaseModel;
import com.example.luke.newsclient.entity.Search;
import com.example.luke.newsclient.netWork.SearchWrapper;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SearchResultModel extends BaseModel{
    public void getSearchResult(String word, Observer<List<Search>> observer){
        getRetrofitService().getSearchList("http://api.baiyue.baidu.com/sn/api/searchnews",word)
                .map(new Function<SearchWrapper<SearchWrapper.DataBean<List<Search>>>, List<Search>>() {
                    @Override
                    public List<Search> apply(SearchWrapper<SearchWrapper.DataBean<List<Search>>> dataBeanSearchWrapper) throws Exception {
                        return dataBeanSearchWrapper.getData().getNews();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
