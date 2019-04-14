package com.example.luke.newsclient.view.activity.personal;

import com.example.luke.newsclient.entity.UserInfo;

public interface ILoginView {
    void onSuccess(UserInfo userInfo);
    void onFailure();
}
