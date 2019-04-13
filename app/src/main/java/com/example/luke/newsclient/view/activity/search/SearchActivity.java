package com.example.luke.newsclient.view.activity.search;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luke.newsclient.R;
import com.example.luke.newsclient.database.RecordSqlHelper;
import com.example.luke.newsclient.view.diyView.FlowLayout;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class SearchActivity extends SwipeBackActivity implements ISearchView {

    private RecordSqlHelper mSqlHelper;
    private SQLiteDatabase mDatabase;

    private EditText searchText;
    private TextView search;
    private RelativeLayout recordLayout;
    private ImageView deleteRecord;
    private FlowLayout flowLayout;

    private SwipeBackLayout mSwipeBackLayout;
    private String searchWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mSqlHelper = new RecordSqlHelper(this, "record.db", null, 1);
        searchText = (EditText) findViewById(R.id.search_text);
        search = (TextView) findViewById(R.id.search);
        recordLayout = (RelativeLayout) findViewById(R.id.record_layout);
        deleteRecord = (ImageView) findViewById(R.id.delete_all);
        flowLayout = (FlowLayout) findViewById(R.id.record_flowLayout);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#e98f36"));
        mSwipeBackLayout = getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        flowLayout.removeAllViews();
        if (getHistoricRecord().size() == 0){
            recordLayout.setVisibility(View.INVISIBLE);
        }else {
            recordLayout.setVisibility(View.VISIBLE);
            setTextView(getHistoricRecord());
        }
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchWord = searchText.getText().toString();
                if (TextUtils.isEmpty(searchWord)) {
                    Toast.makeText(SearchActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("searchWord", searchWord);
                    intent.setClass(SearchActivity.this,SearchResultActivity.class);
                    if (!getHistoricRecord().contains(searchWord)){
                        saveRecord(searchWord);
                    }
                    startActivity(intent);
                }
            }
        });
        deleteRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowLayout.removeAllViews();
                recordLayout.setVisibility(View.INVISIBLE);
                deleteRecord();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public List<String> getHistoricRecord() {
        List<String> list = new ArrayList<>();
        Cursor cursor = mSqlHelper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + "" + "%' order by id desc ", null);
        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex("name")));
        }
        return list;
    }

    @Override
    public void saveRecord(String name) {
        mDatabase = mSqlHelper.getWritableDatabase();
        mDatabase.execSQL("insert into records(name) values('" + name + "')");
        mDatabase.close();
    }

    @Override
    public void deleteRecord() {
        mDatabase = mSqlHelper.getWritableDatabase();
        mDatabase.execSQL("delete from records");
        mDatabase.close();
    }

    private void setTextView(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            final TextView textView = new TextView(this);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("searchWord", textView.getText());
                    startActivity(intent);
                }
            });
            textView.setText(list.get(i));
            textView.setTextSize(15);
            textView.setBackgroundResource(R.drawable.shape_search_record);
            textView.setTextColor(Color.parseColor("#ffffff"));
            flowLayout.addView(textView);
        }
    }

    private void addSingleTextView(String text){
        final TextView textView = new TextView(this);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                intent.putExtra("key", textView.getText());
                startActivity(intent);
            }
        });
        textView.setText(text);
        textView.setTextSize(15);
        textView.setBackgroundResource(R.drawable.shape_search_record);
        textView.setTextColor(Color.parseColor("#ffffff"));
        flowLayout.addView(textView);
    }
}
