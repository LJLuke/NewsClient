package com.example.luke.newsclient.presenter.ios;

import com.example.luke.newsclient.base.BasePresenter;
import com.example.luke.newsclient.entity.GanHuo.GhNews;
import com.example.luke.newsclient.model.ios.IosNewsModel;
import com.example.luke.newsclient.view.fragment.ios.IosTabFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class IosNewsPre extends BasePresenter<IosTabFragment> implements IIosNewsPre{
    private IosNewsModel iosNewsModel;

    public IosNewsPre(){
        iosNewsModel = new IosNewsModel();
    }
    @Override
    public void getIosNews(int page) {
        iosNewsModel.getIosNews("onevcat", 20, page, new Observer<List<GhNews>>() {
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
