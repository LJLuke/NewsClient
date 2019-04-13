package com.example.luke.newsclient.presenter.zhihu;

import com.example.luke.newsclient.base.BasePresenter;
import com.example.luke.newsclient.entity.GanHuo.GhNews;
import com.example.luke.newsclient.model.zhihu.ZhiHuNewsModel;
import com.example.luke.newsclient.view.fragment.common.IGhNewsFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ZhiHuNewsPre extends BasePresenter<IGhNewsFragment>implements IZhiHuNewsPre {
    private ZhiHuNewsModel zhiHuNewsModel;
    public ZhiHuNewsPre(){
        zhiHuNewsModel = new ZhiHuNewsModel();
    }
    @Override
    public void getZhiHuNews(int page) {
        zhiHuNewsModel.getZhiHuNews("zhihu", 20, page, new Observer<List<GhNews>>() {
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
