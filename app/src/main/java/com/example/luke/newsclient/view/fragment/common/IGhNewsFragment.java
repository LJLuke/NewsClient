package com.example.luke.newsclient.view.fragment.common;

import com.example.luke.newsclient.base.IBaseFragment;
import com.example.luke.newsclient.entity.GanHuo.GhNews;

import java.util.List;

public interface IGhNewsFragment extends IBaseFragment{
    void onNewsArrived(List<GhNews> newsList);
    void onMoreNewsArrived(List<GhNews> newsList);

}
