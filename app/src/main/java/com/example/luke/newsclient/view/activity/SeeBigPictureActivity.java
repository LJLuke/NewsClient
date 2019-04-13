package com.example.luke.newsclient.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.luke.newsclient.R;
import com.example.luke.newsclient.adapter.Const;
import com.githang.statusbar.StatusBarCompat;
import com.shizhefei.view.largeimage.LargeImageView;
import com.shizhefei.view.largeimage.factory.FileBitmapDecoderFactory;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class SeeBigPictureActivity extends AppCompatActivity {

    private LargeImageView largeImageView;
    private ImageView normalImageView;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_big_picture);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#000000"));
        largeImageView = findViewById(R.id.large_image);
        normalImageView = findViewById(R.id.normal_image);
        Intent intent = getIntent();
        String url = intent.getStringExtra("imageUrl");
        int type = intent.getIntExtra("type", -1);
        largeImageView.setOnClickListener(v -> finish());
        normalImageView.setOnClickListener(v -> finish());
        if (type == Const.NORMAL) {
            if (normalImageView.getVisibility() == View.INVISIBLE) {
                largeImageView.setVisibility(View.INVISIBLE);
                normalImageView.setVisibility(View.VISIBLE);
                Glide.with(this).load(url).into(normalImageView);
            } else {
                largeImageView.setVisibility(View.INVISIBLE);
                Glide.with(this).load(url).into(normalImageView);
            }
        } else if (type == Const.LONGPICTURE) {
            if (largeImageView.getVisibility() == View.INVISIBLE) {
                normalImageView.setVisibility(View.INVISIBLE);
                largeImageView.setVisibility(View.VISIBLE);
                Glide.with(this).load(url).downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        largeImageView.setImage(new FileBitmapDecoderFactory(resource));
                    }
                });
            } else {
                normalImageView.setVisibility(View.INVISIBLE);
                Glide.with(this).load(url).downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        largeImageView.setImage(new FileBitmapDecoderFactory(resource));
                    }
                });
            }
        }

    }
}
