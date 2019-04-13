package com.example.luke.newsclient.presenter.inland;

import com.example.luke.newsclient.base.BasePresenter;
import com.example.luke.newsclient.entity.News;
import com.example.luke.newsclient.model.inland.InlandNewsModel;
import com.example.luke.newsclient.view.fragment.common.INewsFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class InlandNewsPre extends BasePresenter<INewsFragment> implements IInlandNewsPre {

    private InlandNewsModel inlandNewsModel;
    public InlandNewsPre(){
        inlandNewsModel = new InlandNewsModel();
    }
    @Override
    public void getInlandNews(String type) {
        inlandNewsModel.getInlandNews(type, new Observer<List<News>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<News> newsList) {
                if (type.equals("guonei")) {
                    getView().onNewsArrived(newsList);
                } else {
                    getView().onMoreNewsArrived(newsList);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (type.equals("guonei")) {
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
