package com.example.luke.newsclient.model.pensonal;

import com.example.luke.newsclient.base.BaseModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class RegisterModel extends BaseModel{
    public void postUserInfo(String username, String password, MultipartBody.Part file, Observer<String> observer){
        getRetrofitService().postUserInfo("http://47.112.27.122:8080/fucaixia/userinfo",0,username,password,"哈哈哈",file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
