package com.sun.sql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sun on 2018/1/10.
 */

public class MySqlBase extends SQLiteOpenHelper {
    public final static String CREATE_BOOK = "create table Book(id integer primary key autoincrement,"
                                                +"author text,"
                                                +"price real,"
                                                +"pagers integer,"
                                                +"name text)";
    public final static String CREATE_CATEGORY = "create table Category(id integer primary key autoincrement,category_name text,category_code integer)";

    public MySqlBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqlBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Log.d("database", "创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_CATEGORY);
    }
}
