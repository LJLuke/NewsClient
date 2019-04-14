package com.example.luke.newsclient.presenter.personal;

import com.example.luke.newsclient.base.BasePresenter;
import com.example.luke.newsclient.entity.UserInfo;
import com.example.luke.newsclient.model.pensonal.PersonModel;
import com.example.luke.newsclient.view.activity.personal.LoginActivity;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginPre extends BasePresenter<LoginActivity> implements IPersonPre{
    private PersonModel personModel;
    public LoginPre(){
        personModel = new PersonModel();
    }
    @Override
    public void getUserInfo(String username) {
        personModel.getUserInfo(username, new Observer<UserInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserInfo userInfo) {
                getView().onSuccess(userInfo);
            }

            @Override
            public void onError(Throwable e) {
                getView().onFailure();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
