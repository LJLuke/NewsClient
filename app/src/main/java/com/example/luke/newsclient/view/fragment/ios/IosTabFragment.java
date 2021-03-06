package com.example.luke.newsclient.view.fragment.ios;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.luke.newsclient.R;
import com.example.luke.newsclient.adapter.GhNewsRvAdapter;
import com.example.luke.newsclient.base.BaseTabFragment;
import com.example.luke.newsclient.entity.GanHuo.GhNews;
import com.example.luke.newsclient.presenter.ios.IosNewsPre;
import com.example.luke.newsclient.view.activity.NewsDetailActivity;
import com.example.luke.newsclient.view.diyView.LineItemDecoration;
import com.example.luke.newsclient.view.fragment.common.IGhNewsFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mingle.widget.LoadingView;

import java.util.List;

public class IosTabFragment extends BaseTabFragment implements IGhNewsFragment {
    private XRecyclerView recyclerView;
    private LoadingView loadingView;
    private GhNewsRvAdapter newsRvAdapter;

    private int page = 2;

    private IosNewsPre iosNewsPre;
    @Override
    public int bindLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
        iosNewsPre = new IosNewsPre();
        iosNewsPre.attachView(this);
        recyclerView = mView.findViewById(R.id.x_recyclerview);
        loadingView = mView.findViewById(R.id.loadView);
        newsRvAdapter = new GhNewsRvAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsRvAdapter);
        recyclerView.addItemDecoration(new LineItemDecoration());

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                iosNewsPre.getIosNews(1);
            }

            @Override
            public void onLoadMore() {
                iosNewsPre.getIosNews(page);
                page++;
            }
        });

        newsRvAdapter.setOnItemClickListener((url, position) -> {
            Intent intent = new Intent();
            intent.putExtra("url",url);
            intent.putExtra("newsTitle","IOS");
            intent.setClass(getActivity(),NewsDetailActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onFragmentFirstVisible() {
        iosNewsPre.getIosNews(1);
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
    }

    @Override
    public void onNewsArrived(List<GhNews> newsList) {
        recyclerView.refreshComplete();
        newsRvAdapter.setData(newsList);
        newsRvAdapter.notifyDataSetChanged();
        loadingView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onMoreNewsArrived(List<GhNews> newsList) {
        recyclerView.loadMoreComplete();
        newsRvAdapter.addData(newsList);
        newsRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefreshFailure() {
        recyclerView.refreshComplete();
        loadingView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoadMoreFailure() {
        recyclerView.loadMoreComplete();
    }
}
