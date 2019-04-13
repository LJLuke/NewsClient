package com.example.luke.newsclient.presenter.searchResult;

import com.example.luke.newsclient.base.BasePresenter;
import com.example.luke.newsclient.entity.Search;
import com.example.luke.newsclient.model.searchResult.SearchResultModel;
import com.example.luke.newsclient.view.activity.search.ISearchResultView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchResultPre extends BasePresenter<ISearchResultView> implements ISearchResultPre{
    private SearchResultModel searchResultModel;

    public SearchResultPre(){
        searchResultModel = new SearchResultModel();
    }
    @Override
    public void getSearchResult(String word) {
        searchResultModel.getSearchResult(word, new Observer<List<Search>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Search> searches) {
                getView().onSearchResultArrived(searches);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
