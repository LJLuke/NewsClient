package com.example.luke.newsclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luke.newsclient.R;
import com.example.luke.newsclient.entity.Picture;
import com.example.luke.newsclient.listener.RvClickListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PictureRvAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Picture> list = new ArrayList<>();

    private PictureClickListener pictureClickListener;



    public PictureRvAdapter(Context context){
        this.context = context;
    }

    public void setOnPictureClickListener(PictureClickListener pictureClickListener){
        this.pictureClickListener = pictureClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NormalVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_piture,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Glide.with(context).load(list.get(i).getProfile_image()).into(((NormalVH)viewHolder).userImage);
        ((NormalVH)viewHolder).userName.setText(list.get(i).getName());
        ((NormalVH)viewHolder).pictureTitle.setText(list.get(i).getText());
        int width = Integer.parseInt(list.get(i).getWidth());
        int height = Integer.parseInt(list.get(i).getHeight());
        switch (getPictureType(list.get(i))){
            case Const.NORMARL:
            case Const.GIF:
                RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(px2Dip(width),px2Dip(Math.min(height,Const.defaultHeight)));
                layoutParams1.topMargin = px2Dip(10);
                layoutParams1.bottomMargin = px2Dip(20);
                ((NormalVH)viewHolder).picture.setLayoutParams(layoutParams1);
                Glide.with(context).load(list.get(i).getImage1()).into(((NormalVH)viewHolder).picture);
                viewHolder.itemView.setOnClickListener(v -> pictureClickListener.onClickListener(list.get(i).getImage0(),Const.NORMARL));
                ((NormalVH)viewHolder).pictureType.setText("");
                break;
            case Const.LONGPICTURE:
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,px2Dip(Const.defaultHeight));
                layoutParams2.topMargin = px2Dip(10);
                layoutParams2.bottomMargin = px2Dip(20);
                ((NormalVH)viewHolder).picture.setLayoutParams(layoutParams2);
                Glide.with(context).load(list.get(i).getImage1()).into(((NormalVH)viewHolder).picture);
                ((NormalVH)viewHolder).pictureType.setText("长图");
                viewHolder.itemView.setOnClickListener(v -> pictureClickListener.onClickListener(list.get(i).getImage0(),Const.LONGPICTURE));
                break;
                default:
                    break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<Picture> list){
        this.list.clear();
        this.list = list;
    }

    public void addData(List<Picture> list){
        this.list.addAll(list);
    }

    private int px2Dip(int px) {
        return context.getResources().getDimensionPixelSize(R.dimen.base_dip) * px;
    }

    private int getPictureType(Picture picture){
        if (picture.getImage0().endsWith(".gif")){
            return Const.GIF;
        }else if (Integer.parseInt(picture.getHeight()) > Const.defaultHeight){
            return Const.LONGPICTURE;
        }else {
            return Const.NORMARL;
        }
    }
    static class NormalVH extends RecyclerView.ViewHolder {
        CircleImageView userImage;
        TextView userName;
        TextView pictureTitle;
        ImageView picture;
        TextView pictureType;

        public NormalVH(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.user_image);
            userName = itemView.findViewById(R.id.user_name);
            pictureTitle = itemView.findViewById(R.id.picture_title);
            picture = itemView.findViewById(R.id.picture);
            pictureType = itemView.findViewById(R.id.picture_type);
        }
    }

    public interface PictureClickListener{
        void onClickListener(String url,int type);
    }
}
