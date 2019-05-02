package com.example.luke.newsclient.view.fragment.hot;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.luke.newsclient.R;
import com.example.luke.newsclient.adapter.NewsRvAdapter;
import com.example.luke.newsclient.base.BaseTabFragment;
import com.example.luke.newsclient.entity.News;
import com.example.luke.newsclient.listener.RvClickListener;
import com.example.luke.newsclient.presenter.hot.HotNewsPre;
import com.example.luke.newsclient.view.activity.NewsDetailActivity;
import com.example.luke.newsclient.view.diyView.LineItemDecoration;
import com.example.luke.newsclient.view.fragment.common.INewsFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mingle.widget.LoadingView;

import java.util.ArrayList;
import java.util.List;

public class HotTabFragment extends BaseTabFragment implements INewsFragment {

    private XRecyclerView recyclerView;
    private LoadingView loadingView;
    private NewsRvAdapter newsRvAdapter;
    private HotNewsPre hotNewsPre;

    private int page = 1;
    private List<String> newsTypeList = new ArrayList<>();
    @Override
    public int bindLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
        hotNewsPre = new HotNewsPre();
        hotNewsPre.attachView(this);
        recyclerView = mView.findViewById(R.id.x_recyclerview);
        loadingView = mView.findViewById(R.id.loadView);
        newsRvAdapter = new NewsRvAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LineItemDecoration());
        recyclerView.setAdapter(newsRvAdapter);
        newsTypeList.add("top");
        newsTypeList.add("shehui");
        newsTypeList.add("caijing");
        newsRvAdapter.setOnItemClickListener(new RvClickListener() {
            @Override
            public void onItemClick(String url, int position) {
                Intent intent = new Intent();
                intent.putExtra("url",url);
                intent.putExtra("newsTitle","热点");
                intent.setClass(getContext(), NewsDetailActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                hotNewsPre.getHotNews(newsTypeList.get(0));
            }

            @Override
            public void onLoadMore() {
                if (page < newsTypeList.size()){
                    hotNewsPre.getHotNews(newsTypeList.get(page));
                    page++;
                }
            }
        });
    }

    @Override
    protected void onFragmentFirstVisible() {
        hotNewsPre.getHotNews(newsTypeList.get(0));
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
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

    @Override
    public void onNewsArrived(List<News> newsList) {
        recyclerView.refreshComplete();
        newsRvAdapter.setData(newsList);
        newsRvAdapter.notifyDataSetChanged();
        loadingView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onMoreNewsArrived(List<News> newsList) {
        recyclerView.loadMoreComplete();
        newsRvAdapter.addData(newsList);
        newsRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hotNewsPre.detachView();
    }
}
