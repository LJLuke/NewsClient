package com.example.luke.newsclient.view.activity.personal;

import com.example.luke.newsclient.entity.UserInfo;

public interface IPersonView {
    void onSuccess(UserInfo userInfo);
    void onFailure();
}
