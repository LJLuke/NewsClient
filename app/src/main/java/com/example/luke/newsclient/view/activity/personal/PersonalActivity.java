package com.example.luke.newsclient.view.activity.personal;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.luke.newsclient.R;
import com.example.luke.newsclient.view.fragment.personal.LoginFragment;
import com.example.luke.newsclient.view.fragment.personal.PersonFragment;
import com.githang.statusbar.StatusBarCompat;

public class PersonalActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Boolean isLogin = false;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private TextView personTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#e98f36"));
        personTitle = findViewById(R.id.person_title);
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin",false);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        if (isLogin){
            personTitle.setText("个人");
            fragmentTransaction.replace(R.id.fragment_container,new PersonFragment());
            fragmentTransaction.commit();
        }else {
            personTitle.setText("登陆");
            fragmentTransaction.replace(R.id.fragment_container,new LoginFragment());
            fragmentTransaction.commit();
        }
    }
}
