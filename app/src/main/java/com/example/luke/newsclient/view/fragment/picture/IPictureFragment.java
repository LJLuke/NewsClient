package com.example.luke.newsclient.view.fragment.picture;

import com.example.luke.newsclient.base.IBaseFragment;
import com.example.luke.newsclient.entity.Picture;

import java.util.List;

public interface IPictureFragment extends IBaseFragment{
    void onPicturesArrived(List<Picture> list);
    void onMorePicturesArrived(List<Picture> list);
}
