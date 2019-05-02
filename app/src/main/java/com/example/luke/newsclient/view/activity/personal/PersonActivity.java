package com.example.luke.newsclient.view.activity.personal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
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


    private static final int REQUEST_CODE_SETTING_ACTIVITY = 100;
    private static final int REQUEST_CODE_LOGIN_ACTIVITY = 100;
    private Boolean isLogin = false;

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
        isLogin = sharedPreferences.getBoolean("isLogin",false);
        if (isLogin){
            userName = sharedPreferences.getString("username",null);
            personPre.getUserInfo(userName);
        }else {
            Glide.with(this).load(R.drawable.icon).into(userAvatar);
            userNameText.setText("请登录");
            userAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(PersonActivity.this,LoginActivity.class),REQUEST_CODE_LOGIN_ACTIVITY);
                }
            });
        }
        back.setOnClickListener(v -> finish());

        collectLayout.setOnClickListener(v -> {
            if (isLogin){
                startActivity(new Intent(PersonActivity.this,CollectActivity.class));
            }else {
                startActivityForResult(new Intent(PersonActivity.this,LoginActivity.class),REQUEST_CODE_LOGIN_ACTIVITY);
            }
        });

        settingLayout.setOnClickListener(v -> {
            if (isLogin){
                startActivityForResult(new Intent(PersonActivity.this, SettingActivity.class), REQUEST_CODE_SETTING_ACTIVITY);
            }else {
                startActivityForResult(new Intent(PersonActivity.this,LoginActivity.class),REQUEST_CODE_LOGIN_ACTIVITY);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_SETTING_ACTIVITY && resultCode == RESULT_OK){
            Glide.with(this).load(R.drawable.icon).into(userAvatar);
            userNameText.setText("请登录");
            userAvatar.setOnClickListener(v -> startActivityForResult(new Intent(PersonActivity.this,LoginActivity.class),REQUEST_CODE_LOGIN_ACTIVITY));
        }
        if (requestCode == REQUEST_CODE_LOGIN_ACTIVITY && resultCode == RESULT_OK){
            isLogin = sharedPreferences.getBoolean("isLogin",false);
            userName = sharedPreferences.getString("username",null);
            personPre.getUserInfo(userName);
        }
        super.onActivityResult(requestCode,resultCode,data);
    }
}
