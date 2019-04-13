package com.example.luke.newsclient.view.fragment.inland;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.example.luke.newsclient.R;
import com.example.luke.newsclient.adapter.NewsRvAdapter;
import com.example.luke.newsclient.base.BaseTabFragment;
import com.example.luke.newsclient.entity.News;
import com.example.luke.newsclient.presenter.inland.InlandNewsPre;
import com.example.luke.newsclient.view.activity.NewsDetailActivity;
import com.example.luke.newsclient.view.diyView.LineItemDecoration;
import com.example.luke.newsclient.view.fragment.common.INewsFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InlandTabFragment extends BaseTabFragment implements INewsFragment {

    private InlandNewsPre inlandNewsPre;
    private XRecyclerView recyclerView;
    private NewsRvAdapter newsRvAdapter;

    private int page = 1;
    private List<String> newsTypeList = new ArrayList<>();
    @Override
    public int bindLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
        inlandNewsPre = new InlandNewsPre();
        inlandNewsPre.attachView(this);
        recyclerView = mView.findViewById(R.id.x_recyclerview);
        newsRvAdapter = new NewsRvAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LineItemDecoration());
        recyclerView.setAdapter(newsRvAdapter);

        newsTypeList.add("guonei");
        newsTypeList.add("yule");
        newsTypeList.add("shishang");
        newsRvAdapter.setOnItemClickListener((url, position) -> {
            Intent intent = new Intent();
            intent.putExtra("url",url);
            intent.putExtra("tabName","国内");
            intent.setClass(getContext(), NewsDetailActivity.class);
            startActivity(intent);
        });
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                inlandNewsPre.getInlandNews(newsTypeList.get(0));
            }

            @Override
            public void onLoadMore() {
                if (page < newsTypeList.size()){
                    inlandNewsPre.getInlandNews(newsTypeList.get(page));
                    page++;
                }
            }
        });
    }

    @Override
    public void onRefreshFailure() {
        recyclerView.refreshComplete();
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
    }

    @Override
    public void onMoreNewsArrived(List<News> newsList) {
        recyclerView.loadMoreComplete();
        newsRvAdapter.addData(newsList);
        newsRvAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onFragmentFirstVisible() {
        inlandNewsPre.getInlandNews(newsTypeList.get(0));
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        inlandNewsPre.detachView();
    }
}
