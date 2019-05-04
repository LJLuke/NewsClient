package com.example.luke.newsclient.view.fragment.recommend;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.luke.newsclient.R;
import com.example.luke.newsclient.adapter.NewsRvAdapter;
import com.example.luke.newsclient.base.BaseTabFragment;
import com.example.luke.newsclient.entity.News;
import com.example.luke.newsclient.listener.RvClickListener;
import com.example.luke.newsclient.presenter.recommend.RecommendPre;
import com.example.luke.newsclient.service.KeyWordService;
import com.example.luke.newsclient.view.activity.NewsDetailActivity;
import com.example.luke.newsclient.view.diyView.LineItemDecoration;
import com.example.luke.newsclient.view.fragment.common.INewsFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mingle.widget.LoadingView;

import java.util.List;

public class RecommendTabFragment extends BaseTabFragment implements IRecommendFragment,INewsFragment {
    private RecommendPre recommendPre;
    private XRecyclerView recyclerView;
    private LoadingView loadingView;
    private NewsRvAdapter newsRvAdapter;

    @Override
    public int bindLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
        recommendPre = new RecommendPre();
        recommendPre.attachView(this);
        mActivity.startService(new Intent(mActivity, KeyWordService.class));
        recyclerView = mView.findViewById(R.id.x_recyclerview);
        loadingView = mView.findViewById(R.id.loadView);
        newsRvAdapter = new NewsRvAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LineItemDecoration());
        recyclerView.setAdapter(newsRvAdapter);
        recommendPre.initDb();

        newsRvAdapter.setOnItemClickListener(new RvClickListener() {
            @Override
            public void onItemClick(String url, int position) {
                Intent intent = new Intent();
                intent.putExtra("url",url);
                intent.putExtra("newsTitle","推荐");
                intent.setClass(getContext(), NewsDetailActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                recommendPre.refreshNews();
                mActivity.startService(new Intent(mActivity, KeyWordService.class));
            }

            @Override
            public void onLoadMore() {
                recommendPre.loadMoreNews();
            }
        });
    }

    @Override
    public void onRefreshFailure() {

    }

    @Override
    public void onLoadMoreFailure() {

    }

    @Override
    protected void onFragmentFirstVisible() {
        recommendPre.refreshNews();
        loadingView.setVisibility(View.INVISIBLE);
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
}
