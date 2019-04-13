package com.example.luke.newsclient.presenter.hot;

import com.example.luke.newsclient.base.BasePresenter;
import com.example.luke.newsclient.entity.News;
import com.example.luke.newsclient.model.hot.HotNewsModel;
import com.example.luke.newsclient.view.fragment.common.INewsFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HotNewsPre extends BasePresenter<INewsFragment> implements IHotNewsPre {

    private HotNewsModel hotNewsModel;

    public HotNewsPre() {
        hotNewsModel = new HotNewsModel();
    }

    @Override
    public void getHotNews(String type) {
        hotNewsModel.getHotNews(type, new Observer<List<News>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<News> news) {
                if (type.equals("top")) {
                    getView().onNewsArrived(news);
                } else {
                    getView().onMoreNewsArrived(news);
                }

            }

            @Override
            public void onError(Throwable e) {
                if (type.equals("top")) {
                    getView().onRefreshFailure();
                } else {
                    getView().onLoadMoreFailure();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
