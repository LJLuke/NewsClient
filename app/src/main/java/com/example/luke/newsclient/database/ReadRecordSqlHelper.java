package com.example.luke.newsclient.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReadRecordSqlHelper extends SQLiteOpenHelper {
    private static String name = "readRecords.db";
    private static Integer version = 1;
    public ReadRecordSqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table readRecords(id integer primary key autoincrement,title varchar(200),url varchar(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
