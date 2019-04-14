package com.example.luke.newsclient.model.pensonal;

import com.example.luke.newsclient.base.BaseModel;
import com.example.luke.newsclient.entity.UserInfo;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PersonModel extends BaseModel{
    public void getUserInfo(String username, Observer<UserInfo> observer){
        getRetrofitService().getUserInfo("http://47.112.27.122:8080/fucaixia/userinfo",1,username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
