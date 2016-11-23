package com.example.administrator.alltest.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/11/23 0023.
 * 继承类，继承数据库使用的帮助类SQLiteOpenHelper
 */

public class SQliteDBHelper extends SQLiteOpenHelper {

    //步骤1：设置常数参量
    private static final String DATABASE_NAME="diary_db";
    private static final int VERSION=1;
    private static final String TABLE_NAME="diary";

    //步骤2：构造方法
    public SQliteDBHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    /** 参数介绍：context 程序上下文环境 即：XXXActivity.this
     * name 数据库名字
     * factory 接收数据，一般情况为null
     * version 数据库版本号
     */
    public SQliteDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //数据库第一次创建时，onCreate()会被调用
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //数据库表的创建
        String sql="create table "+TABLE_NAME+"(id integer primary key autoincrement,title varchar(20),weather varchar(10),context text,publish date)";
        //使用SQLiteDatabase对象创建对象
        sqLiteDatabase.execSQL(sql);
    }

    //数据库版本变化时，会调用onUpgrade方法
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
