package com.example.luke.newsclient.presenter.recommend;

import com.example.luke.newsclient.entity.News;

import java.util.List;

public interface IRecommendPre {
    void refreshNews();
    void loadMoreNews();
    void initDb();
}
