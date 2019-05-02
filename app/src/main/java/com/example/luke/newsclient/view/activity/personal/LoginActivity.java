package com.example.luke.newsclient.view.activity.personal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luke.newsclient.R;
import com.example.luke.newsclient.entity.UserInfo;
import com.example.luke.newsclient.presenter.personal.LoginPre;
import com.githang.statusbar.StatusBarCompat;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class LoginActivity extends SwipeBackActivity implements ILoginView{
    private EditText userNameText;
    private EditText passwordText;
    private TextView register;
    private Button login;

    private String userName;
    private String password;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private LoginPre loginPre;

    private SwipeBackLayout mSwipeBackLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"));
        userNameText = (EditText) findViewById(R.id.user_name);
        passwordText = (EditText) findViewById(R.id.password);
        register = (TextView) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);

        setSwipeBackEnable(true);
        mSwipeBackLayout = getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        loginPre = new LoginPre();
        loginPre.attachView(this);
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        login.setOnClickListener(v -> {
            userName = userNameText.getText().toString();
            password = passwordText.getText().toString();
            if (TextUtils.isEmpty(userName)){
                Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            }
            if (TextUtils.isEmpty(password)){
                Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            }
            if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)){
                loginPre.getUserInfo(userName);
            }
        });
        register.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            overridePendingTransition(0, 0);
        });
    }

    @Override
    public void onSuccess(UserInfo userInfo) {
        if (userName.equals(userInfo.getUserName()) && password.equals(userInfo.getPassWord())){
            Toast.makeText(this,"登录成功!",Toast.LENGTH_SHORT).show();
            editor.putBoolean("isLogin",true);
            editor.putString("username",userName);
            editor.commit();
            setResult(RESULT_OK);
            finish();
        }else {
            Toast.makeText(this,"登录失败!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure() {
        Toast.makeText(this,"登录失败!",Toast.LENGTH_SHORT).show();
    }
}
