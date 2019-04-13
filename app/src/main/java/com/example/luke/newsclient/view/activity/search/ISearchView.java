package com.example.luke.newsclient.view.activity.search;

import java.util.List;

public interface ISearchView {
    List<String> getHistoricRecord();
    void saveRecord(String name);
    void deleteRecord();
}
