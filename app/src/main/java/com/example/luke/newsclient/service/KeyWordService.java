package com.example.luke.newsclient.service;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import com.example.luke.newsclient.database.KeyWordHelper;
import com.example.luke.newsclient.database.ReadRecordSqlHelper;
import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;

import java.util.ArrayList;
import java.util.List;

public class KeyWordService extends IntentService{
    private ReadRecordSqlHelper readRecordSqlHelperHelper;
    private SQLiteDatabase mDatabase;

    private KeyWordHelper keyWordHelper;

    public KeyWordService() {
        super("KeyWordService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        readRecordSqlHelperHelper = new ReadRecordSqlHelper(this, "readRecords.db", null, 1);

        keyWordHelper = new KeyWordHelper(this, "keyWords.db", null, 1);
        mDatabase = keyWordHelper.getWritableDatabase();
        computeAndSaveKeyWord(getReadRecordTitle());
    }

    private List<String> getReadRecordTitle() {
        List<String> list = new ArrayList<>();
        Cursor cursor = readRecordSqlHelperHelper.getReadableDatabase().rawQuery(
                "select distinct title from readRecords where title like '%" + "" + "%' order by id desc ", null);
        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex("title")));
        }
        return list;
    }

    private void computeAndSaveKeyWord(List<String> list){
        KeyWordComputer kwc = new KeyWordComputer(1);
        for (int i = 0;i < list.size();i++){
            Keyword keyword = (Keyword) kwc.computeArticleTfidf(list.get(i)).get(0);
            String name =  keyword.getName();
            double score = keyword.getScore();
            if (score > 5){
                mDatabase.execSQL("insert into keyWords(keyWord) values('" + name + "')");
            }
        }
        mDatabase.close();
    }
}
