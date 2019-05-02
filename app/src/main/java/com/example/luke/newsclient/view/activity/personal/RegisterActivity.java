package com.example.luke.newsclient.view.activity.personal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.luke.newsclient.R;
import com.example.luke.newsclient.presenter.personal.RegisterPre;
import com.githang.statusbar.StatusBarCompat;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RegisterActivity extends SwipeBackActivity implements IRegisterView{

    private SwipeBackLayout mSwipeBackLayout;
    private EditText userNameText;
    private EditText passwordText;
    private Button register;
    private CircleImageView userAvatar;

    private String userName;
    private String password;
    private String avatarPath;

    private RegisterPre registerPre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"));
        mSwipeBackLayout = getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        registerPre = new RegisterPre();
        registerPre.attachView(this);

        userNameText = (EditText)findViewById(R.id.user_name);
        passwordText = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        userAvatar = (CircleImageView) findViewById(R.id.user_avatar);

        userAvatar.setOnClickListener(v -> PictureSelector.create(RegisterActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .isCamera(true)
                .compress(true)
                .circleDimmedLayer(true)
                .showCropFrame(false)
                .forResult(PictureConfig.CHOOSE_REQUEST));

        register.setOnClickListener(v -> {
            userName = userNameText.getText().toString();
            password = passwordText.getText().toString();
            if (TextUtils.isEmpty(userName)){
                Toast.makeText(RegisterActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            }
            if (TextUtils.isEmpty(password)){
                Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            }
            if (TextUtils.isEmpty(avatarPath)){
                Toast.makeText(RegisterActivity.this,"请选择图片",Toast.LENGTH_SHORT).show();
            }
            if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(avatarPath)){
                File file = new File(avatarPath);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                registerPre.postUserInfo(userName,password,part);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    avatarPath = selectList.get(0).getPath();
                    Glide.with(this).load(avatarPath).into(userAvatar);
                    break;
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onSuccess() {
        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
        PictureFileUtils.deleteCacheDirFile(RegisterActivity.this);
        finish();
    }

    @Override
    public void onFailure() {
        Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
        PictureFileUtils.deleteCacheDirFile(RegisterActivity.this);
    }
}
