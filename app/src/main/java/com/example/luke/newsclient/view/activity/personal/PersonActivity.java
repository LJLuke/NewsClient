package com.example.luke.newsclient.view.activity.personal;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luke.newsclient.R;
import com.example.luke.newsclient.entity.UserInfo;
import com.example.luke.newsclient.presenter.personal.PersonPre;
import com.githang.statusbar.StatusBarCompat;

import de.hdodenhof.circleimageview.CircleImageView;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class PersonActivity extends SwipeBackActivity implements IPersonView{

    private CircleImageView userAvatar;
    private TextView userNameText;
    private ImageView back;

    private RelativeLayout collectLayout;
    private RelativeLayout settingLayout;

    private PersonPre personPre;

    private SharedPreferences sharedPreferences;

    private String userName;
    private String userAvatarBaseUrl = "http://47.112.27.122:8080/fucaixia/headimage/";

    private SwipeBackLayout mSwipeBackLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"));
        userAvatar = (CircleImageView) findViewById(R.id.user_avatar);
        userNameText = (TextView) findViewById(R.id.user_name);
        back = (ImageView) findViewById(R.id.back_image);
        collectLayout = (RelativeLayout) findViewById(R.id.collect_layout);
        settingLayout = (RelativeLayout) findViewById(R.id.setting_layout);

        setSwipeBackEnable(true);
        mSwipeBackLayout = getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        personPre = new PersonPre();
        personPre.attachView(this);

        sharedPreferences =  getSharedPreferences("login", Context.MODE_PRIVATE);
        userName = sharedPreferences.getString("username",null);
        personPre.getUserInfo(userName);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collectLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onSuccess(UserInfo userInfo) {
        Glide.with(this).load(userAvatarBaseUrl+userInfo.getImagePath()).into(userAvatar);
        userNameText.setText(userName);
    }

    @Override
    public void onFailure() {

    }
}
