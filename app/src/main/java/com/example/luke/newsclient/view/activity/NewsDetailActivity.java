package com.example.luke.newsclient.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luke.newsclient.R;
import com.example.luke.newsclient.database.ReadRecordSqlHelper;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class NewsDetailActivity extends SwipeBackActivity {

    private TextView newsTitleText;
    private WebView webView;
    private ImageView backImage;
    private ImageView collect;

    private String url;
    private String newsTitle;
    private Boolean isCollected;
    private Boolean isSpCollected = false;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private SwipeBackLayout mSwipeBackLayout;

    private ReadRecordSqlHelper sqlHelper;
    private SQLiteDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ffffff"));
        sqlHelper = new ReadRecordSqlHelper(this, "readRecords.db", null, 1);
        webView = (WebView) findViewById(R.id.web_view);
        backImage = (ImageView) findViewById(R.id.back_image);
        newsTitleText = (TextView) findViewById(R.id.news_title);
        collect = (ImageView) findViewById(R.id.collect);
        Intent intent = getIntent();
        newsTitle = intent.getStringExtra("newsTitle");
        url = intent.getStringExtra("url");
        setSwipeBackEnable(true);

        sharedPreferences =  getSharedPreferences("collect", Context.MODE_PRIVATE);
        isSpCollected = sharedPreferences.getBoolean(url,false);
        isCollected = isSpCollected;
        editor = sharedPreferences.edit();
        if (isSpCollected){
            collect.setImageDrawable(getResources().getDrawable(R.drawable.collect_choosed));
        }else {
            collect.setImageDrawable(getResources().getDrawable(R.drawable.collect_normal));
        }
        mSwipeBackLayout = getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        backImage.setOnClickListener(v -> finish());
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCollected){
                    Toast.makeText(NewsDetailActivity.this,"取消收藏",Toast.LENGTH_SHORT).show();
                    isCollected = false;
                    collect.setImageDrawable(getResources().getDrawable(R.drawable.collect_normal));
                    if (isSpCollected){
                        editor.putBoolean(url,false);
                        editor.apply();
                    }
                }else {
                    Toast.makeText(NewsDetailActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
                    isCollected = true;
                    collect.setImageDrawable(getResources().getDrawable(R.drawable.collect_choosed));
                    if (!isSpCollected){
                        editor.putBoolean(url,true);
                        editor.apply();
                    }
                }
            }
        });
        newsTitleText.setText(newsTitle);
        initWebView();
    }

    private void initWebView(){
        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                saveReadRecord(title,url);
            }
        });
        getReadRecordTitle();
    }

    private void saveReadRecord(String title,String url) {
        mDatabase = sqlHelper.getWritableDatabase();
        mDatabase.execSQL("insert or ignore into readRecords(title,url) values('" + title + "','" + url + "')");
        mDatabase.close();
    }

    private List<String> getReadRecordTitle() {
        List<String> list = new ArrayList<>();
        Cursor cursor = sqlHelper.getReadableDatabase().rawQuery(
                "select title from readRecords where title like '%" + "" + "%' order by id desc ", null);
        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex("title")));
        }
        return list;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (webView != null){
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
