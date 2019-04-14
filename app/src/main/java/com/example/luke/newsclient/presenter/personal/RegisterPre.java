package com.example.luke.newsclient.presenter.personal;

import com.example.luke.newsclient.base.BasePresenter;
import com.example.luke.newsclient.model.pensonal.RegisterModel;
import com.example.luke.newsclient.view.activity.personal.IRegisterView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MultipartBody;

public class RegisterPre extends BasePresenter<IRegisterView> implements IRegisterPre{
    private RegisterModel registerModel;
    public RegisterPre(){
        registerModel = new RegisterModel();
    }
    @Override
    public void postUserInfo(String username, String password, MultipartBody.Part file) {
        registerModel.postUserInfo(username, password, file, new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                if ("true".equals(s)){
                    getView().onSuccess();
                }else {
                    getView().onFailure();
                }
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
