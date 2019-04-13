package com.example.luke.newsclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luke.newsclient.R;
import com.example.luke.newsclient.entity.GanHuo.GhNews;
import com.example.luke.newsclient.listener.RvClickListener;

import java.util.ArrayList;
import java.util.List;

public class GhNewsRvAdapter extends RecyclerView.Adapter{
    private List<GhNews> newsList = new ArrayList<>();
    private Context context;

    private String noCover = "none";

    private RvClickListener mRvClickListener;

    public GhNewsRvAdapter(Context context){
        this.context = context;
    }

    public void setOnItemClickListener(RvClickListener mRvClickListener){
        this.mRvClickListener = mRvClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == Const.NORMAL){
            return new GhNewsRvAdapter.NewsVHNormal(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_news_normal,viewGroup,false));
        }else if (i == Const.PICTURE){
            return new GhNewsRvAdapter.NewsVHPicture(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_news_picture,viewGroup,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof GhNewsRvAdapter.NewsVHNormal){
            viewHolder.itemView.setOnClickListener(v -> mRvClickListener.onItemClick(newsList.get(i).getUrl(),i));
            ((GhNewsRvAdapter.NewsVHNormal) viewHolder).newsTitle.setText(newsList.get(i).getTitle());
        }else if (viewHolder instanceof GhNewsRvAdapter.NewsVHPicture){
            viewHolder.itemView.setOnClickListener(v -> mRvClickListener.onItemClick(newsList.get(i).getUrl(),i));
            ((GhNewsRvAdapter.NewsVHPicture) viewHolder).newsTitle.setText(newsList.get(i).getTitle());
            Glide.with(context)
                    .load(newsList.get(i).getCover())
                    .into(((NewsVHPicture) viewHolder).newsImage);
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (noCover.equals(newsList.get(position).getCover()) || TextUtils.isEmpty(newsList.get(position).getCover())){
            return Const.NORMAL;
        }else {
            return Const.PICTURE;
        }
    }

    public void setData(List<GhNews> newsList){
        this.newsList.clear();
        this.newsList.addAll(newsList);
    }

    public void addData(List<GhNews> newsList){
        this.newsList.addAll(newsList);
    }

    static class NewsVHNormal extends RecyclerView.ViewHolder{

        TextView newsTitle;
        public NewsVHNormal(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.news_title_normal);
        }
    }
    static class NewsVHPicture extends RecyclerView.ViewHolder{
        TextView newsTitle;
        ImageView newsImage;
        public NewsVHPicture(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.news_title_picture);
            newsImage = itemView.findViewById(R.id.news_picture);
        }
    }
}
