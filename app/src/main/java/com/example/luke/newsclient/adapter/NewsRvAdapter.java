package com.example.luke.newsclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luke.newsclient.R;
import com.example.luke.newsclient.entity.News;
import com.example.luke.newsclient.listener.RvClickListener;

import java.util.ArrayList;
import java.util.List;

public class NewsRvAdapter extends RecyclerView.Adapter{

    private Context context;
    private List<News> newsList = new ArrayList<>();
    private RvClickListener mRvClickListener;

    public NewsRvAdapter(Context context){
        this.context = context;
    }

    public void setOnItemClickListener(RvClickListener mRvClickListener){
        this.mRvClickListener = mRvClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == Const.NORMAL){
            return new NewsVHNormal(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_news_normal,viewGroup,false));
        }else if (i == Const.PICTURE){
            return new NewsVHPicture(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_news_picture,viewGroup,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setOnClickListener(v -> mRvClickListener.onItemClick(newsList.get(i).getUrl(),i));
        if (viewHolder instanceof NewsVHNormal){
            ((NewsVHNormal) viewHolder).newsTitle.setText(newsList.get(i).getTitle());
        }else if (viewHolder instanceof NewsVHPicture){
            ((NewsVHPicture) viewHolder).newsTitle.setText(newsList.get(i).getTitle());
            Glide.with(context).load(newsList.get(i).getThumbnail_pic_s()).into(((NewsVHPicture) viewHolder).newsImage);
        }
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (newsList.get(position).getThumbnail_pic_s() == null){
            return Const.NORMAL;
        }else {
            return Const.PICTURE;
        }
    }

    public void setData(List<News> newsList){
        this.newsList.clear();
        this.newsList.addAll(newsList);
    }

    public void addData(List<News> newsList){
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
