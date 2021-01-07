package com.example.douyinapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CreateDB extends SQLiteOpenHelper {
     private static final String DATABASE_NAME = "test.db";  //数据库名字

    public CreateDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

         db.execSQL("CREATE TABLE IF NOT EXISTS user(zhanghao TEXT PRIMARY KEY ," +
               " mima TEXT)");
        Log.i("TAG:","创建User数据库表！");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
