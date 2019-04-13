package com.example.luke.newsclient.view.activity.search;

import com.example.luke.newsclient.entity.Search;

import java.util.List;

public interface ISearchResultView {
    void onSearchResultArrived(List<Search> list);
}
