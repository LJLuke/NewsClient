package com.example.luke.newsclient.view.fragment.recommend;

import android.content.Intent;

import com.example.luke.newsclient.R;
import com.example.luke.newsclient.base.BaseTabFragment;
import com.example.luke.newsclient.service.KeyWordService;

public class RecommendTabFragment extends BaseTabFragment {
    @Override
    public int bindLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
        mActivity.startService(new Intent(mActivity, KeyWordService.class));
    }

    @Override
    public void onRefreshFailure() {

    }

    @Override
    public void onLoadMoreFailure() {

    }
}
