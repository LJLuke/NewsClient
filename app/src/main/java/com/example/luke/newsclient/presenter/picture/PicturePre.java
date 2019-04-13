package com.example.luke.newsclient.presenter.picture;

import com.example.luke.newsclient.base.BasePresenter;
import com.example.luke.newsclient.entity.Picture;
import com.example.luke.newsclient.model.picture.PictureModel;
import com.example.luke.newsclient.view.fragment.picture.IPictureFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PicturePre extends BasePresenter<IPictureFragment> implements IPicturePre{
    private PictureModel pictureModel;
    public PicturePre(){
        pictureModel = new PictureModel();
    }
    @Override
    public void getPictures(int page) {
        pictureModel.getPictures(page, new Observer<List<Picture>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Picture> pictures) {
                if (page == 1){
                    getView().onPicturesArrived(pictures);
                }else {
                    getView().onMorePicturesArrived(pictures);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (page == 1){
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
