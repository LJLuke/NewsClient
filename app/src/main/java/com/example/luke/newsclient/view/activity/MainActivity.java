package com.example.luke.newsclient.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.luke.newsclient.R;
import com.example.luke.newsclient.adapter.TabFragmentAdapter;
import com.example.luke.newsclient.view.activity.personal.LoginActivity;
import com.example.luke.newsclient.view.activity.personal.PersonActivity;
import com.example.luke.newsclient.view.activity.search.SearchActivity;
import com.example.luke.newsclient.view.fragment.android.AndroidTabFragment;
import com.example.luke.newsclient.view.fragment.ios.IosTabFragment;
import com.example.luke.newsclient.view.fragment.zhihu.ZhiHuTabFragment;
import com.example.luke.newsclient.view.fragment.hot.HotTabFragment;
import com.example.luke.newsclient.view.fragment.inland.InlandTabFragment;
import com.example.luke.newsclient.view.fragment.recommend.RecommendTabFragment;
import com.example.luke.newsclient.view.fragment.picture.PictureTabFragment;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private LinearLayout searchLayout;
    private CircleImageView personAvatar;

    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();

    private SharedPreferences sharedPreferences;
    private Boolean isLogin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#e98f36"));
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin",false);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        searchLayout = findViewById(R.id.search_layout);
        personAvatar = findViewById(R.id.person_avatar);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTitleList.add("热点");
        mTitleList.add("推荐");
        mTitleList.add("国内");
        mTitleList.add("知乎");
        mTitleList.add("图片");
        mTitleList.add("Android");
        mTitleList.add("Ios");

        mFragmentList.add(new HotTabFragment());
        mFragmentList.add(new RecommendTabFragment());
        mFragmentList.add(new InlandTabFragment());
        mFragmentList.add(new ZhiHuTabFragment());
        mFragmentList.add(new PictureTabFragment());
        mFragmentList.add(new AndroidTabFragment());
        mFragmentList.add(new IosTabFragment());

        viewPager.setAdapter(new TabFragmentAdapter(getSupportFragmentManager(), mTitleList, mFragmentList));
        tabLayout.setupWithViewPager(viewPager);

        searchLayout.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
            overridePendingTransition(0, 0);
        });

        personAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin){
                    startActivity(new Intent(MainActivity.this, PersonActivity.class));
                }else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        });
    }
}
