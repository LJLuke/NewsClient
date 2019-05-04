package com.example.luke.newsclient.view.activity.search;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.example.luke.newsclient.R;
import com.example.luke.newsclient.adapter.SearchRvAdapter;
import com.example.luke.newsclient.entity.Search;
import com.example.luke.newsclient.presenter.searchResult.SearchResultPre;
import com.example.luke.newsclient.view.activity.NewsDetailActivity;
import com.example.luke.newsclient.view.diyView.LineItemDecoration;
import com.githang.statusbar.StatusBarCompat;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class SearchResultActivity extends SwipeBackActivity implements ISearchResultView {

    private SearchRvAdapter searchRvAdapter;
    private XRecyclerView recyclerView;
    private ImageView back;

    private SwipeBackLayout mSwipeBackLayout;
    private SearchResultPre searchResultPre;

    private String searchWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"));

        searchResultPre = new SearchResultPre();
        searchResultPre.attachView(this);
        recyclerView = (XRecyclerView) findViewById(R.id.recyclerview);
        back = (ImageView) findViewById(R.id.back_image);

        mSwipeBackLayout = getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        searchRvAdapter = new SearchRvAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LineItemDecoration());
        recyclerView.setAdapter(searchRvAdapter);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);

        searchWord = getIntent().getStringExtra("searchWord");
        searchResultPre.getSearchResult(searchWord);

        searchRvAdapter.setOnItemClickListener((url, position) -> {
            Intent intent = new Intent();
            intent.putExtra("url", url);
            intent.putExtra("newsTitle","搜索正文");
            intent.setClass(SearchResultActivity.this,NewsDetailActivity.class);
            startActivity(intent);
        });
        back.setOnClickListener(v -> {
           finish();
        });
    }

    @Override
    public void onSearchResultArrived(List<Search> list) {
        searchRvAdapter.setData(list);
        searchRvAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchResultPre.detachView();
    }
}
