package com.example.luke.newsclient.presenter.personal;

import okhttp3.MultipartBody;

public interface IRegisterPre {
    void postUserInfo(String username, String password, MultipartBody.Part file);
}
