package com.example.luke.newsclient.model.picture;

import com.example.luke.newsclient.base.BaseModel;
import com.example.luke.newsclient.entity.Picture;
import com.example.luke.newsclient.netWork.PictureWrapper;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PictureModel extends BaseModel{
    public void getPictures(int page, Observer<List<Picture>> observer){
        getRetrofitService().getPictureList("https://www.apiopen.top/satinApi",3,page)
                .map(new Function<PictureWrapper<List<Picture>>, List<Picture>>() {
                    @Override
                    public List<Picture> apply(PictureWrapper<List<Picture>> listPictureWrapper) throws Exception {
                        return listPictureWrapper.getData();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
