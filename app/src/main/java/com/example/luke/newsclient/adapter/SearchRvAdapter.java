package com.example.luke.newsclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luke.newsclient.R;
import com.example.luke.newsclient.entity.Picture;
import com.example.luke.newsclient.entity.Search;
import com.example.luke.newsclient.listener.RvClickListener;

import java.util.ArrayList;
import java.util.List;

public class SearchRvAdapter extends RecyclerView.Adapter {
    private List<Search> list = new ArrayList<>();
    private RvClickListener mRvClickListener;

    public void setOnItemClickListener(RvClickListener mRvClickListener){
        this.mRvClickListener = mRvClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SearchVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_news_normal,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setOnClickListener(v -> mRvClickListener.onItemClick(list.get(i).getDisplay_url(),i));
        ((SearchVH)viewHolder).textView.setText(list.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<Search> list){
        this.list.clear();
        this.list = list;
    }
    static class SearchVH extends RecyclerView.ViewHolder {
        TextView textView;
        public SearchVH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.news_title_normal);
        }
    }
}
