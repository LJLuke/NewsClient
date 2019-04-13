package com.example.luke.newsclient.presenter.android;

import com.example.luke.newsclient.base.BasePresenter;
import com.example.luke.newsclient.entity.GanHuo.GhNews;
import com.example.luke.newsclient.model.android.AndroidNewsModel;
import com.example.luke.newsclient.view.fragment.common.IGhNewsFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AndroidNewsPre extends BasePresenter<IGhNewsFragment> implements IAndroidNewsPre{
    private AndroidNewsModel androidNewsModel;

    public AndroidNewsPre(){
        androidNewsModel = new AndroidNewsModel();
    }
    @Override
    public void getAndroidNews(int page) {
        androidNewsModel.getAndroidNews("hongyang", 20, page, new Observer<List<GhNews>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<GhNews> ghNews) {
                if (1 == page){
                    getView().onNewsArrived(ghNews);
                }else {
                    getView().onMoreNewsArrived(ghNews);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (1 == page){
                    getView().onRefreshFailure();
                }else {
                    getView().onLoadMoreFailure();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
