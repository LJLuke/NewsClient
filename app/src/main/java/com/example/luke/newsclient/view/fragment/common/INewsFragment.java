package com.example.luke.newsclient.view.fragment.common;

import com.example.luke.newsclient.base.IBaseFragment;
import com.example.luke.newsclient.entity.News;

import java.util.List;

//作为获取聚合数据News数据接口的fragment通用接口
public interface INewsFragment extends IBaseFragment{
    void onNewsArrived(List<News> newsList);
    void onMoreNewsArrived(List<News> newsList);
}
