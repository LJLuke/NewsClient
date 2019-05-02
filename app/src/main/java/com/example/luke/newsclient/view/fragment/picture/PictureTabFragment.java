package com.example.luke.newsclient.view.fragment.picture;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.luke.newsclient.R;
import com.example.luke.newsclient.adapter.PictureRvAdapter;
import com.example.luke.newsclient.base.BaseTabFragment;
import com.example.luke.newsclient.entity.Picture;
import com.example.luke.newsclient.presenter.picture.PicturePre;
import com.example.luke.newsclient.view.activity.SeeBigPictureActivity;
import com.example.luke.newsclient.view.diyView.PictureItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mingle.widget.LoadingView;

import java.util.List;

public class PictureTabFragment extends BaseTabFragment implements IPictureFragment{

    private XRecyclerView recyclerView;
    private LoadingView loadingView;
    private PictureRvAdapter pictureRvAdapter;

    private PicturePre picturePre;

    private int page = 2;
    @Override
    public int bindLayout() {
        return R.layout.fragment_picture;
    }

    @Override
    public void initView() {
        picturePre = new PicturePre();
        picturePre.attachView(this);
        recyclerView = mView.findViewById(R.id.picture_recyclerview);
        loadingView = mView.findViewById(R.id.loadView);
        pictureRvAdapter = new PictureRvAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new PictureItemDecoration());
        recyclerView.setAdapter(pictureRvAdapter);
        pictureRvAdapter.setOnPictureClickListener((url, type) -> {
            Intent intent = new Intent();
            intent.putExtra("imageUrl",url);
            intent.putExtra("type",type);
            intent.setClass(getContext(), SeeBigPictureActivity.class);
            startActivity(intent);
        });
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                picturePre.getPictures(1);
            }

            @Override
            public void onLoadMore() {
                picturePre.getPictures(page);
                page++;
            }
        });
    }

    @Override
    public void onPicturesArrived(List<Picture> list) {
        recyclerView.refreshComplete();
        pictureRvAdapter.setData(list);
        pictureRvAdapter.notifyDataSetChanged();
        loadingView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onMorePicturesArrived(List<Picture> list) {
        recyclerView.loadMoreComplete();
        pictureRvAdapter.addData(list);
        pictureRvAdapter.notifyDataSetChanged();
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
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
    }

    @Override
    protected void onFragmentFirstVisible() {
        picturePre.getPictures(1);
        loadingView.setVisibility(View.VISIBLE);
    }
}
