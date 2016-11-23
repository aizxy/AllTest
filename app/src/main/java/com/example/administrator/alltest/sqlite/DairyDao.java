package com.example.administrator.alltest.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/11/23 0023.
 * 对于数据库的操作，封装我们所有的业务方法
 */

public class DairyDao {

    private SQliteDBHelper sQliteDBHelper;
    private SQLiteDatabase db;

    //构造方法
    public DairyDao(Context context){
        this.sQliteDBHelper=new SQliteDBHelper(context);
        db=sQliteDBHelper.getWritableDatabase();
    }

    //数据库的读
    public String execQuery(final String sql){
        Cursor cursor=db.rawQuery(sql,null);
        //将cursor指向数据库表的第一行记录
        cursor.moveToFirst();
        //定义一个stringBuffer的对象，动态拼接字符串
        StringBuffer sb=new StringBuffer();
        //循环游标
        while(!cursor.isAfterLast()){
            sb.append(cursor.getInt(0) + "/" + cursor.getString(1) + "/"
                    + cursor.getString(2) + "/" + cursor.getString(3) + "/"
                    + cursor.getString(4)+"#");
            //游标移动
            cursor.moveToNext();
        }
        //关闭查询
        db.close();
        return sb.deleteCharAt(sb.length()-1).toString();
    }

    //数据库的写
    public boolean execWrite(final String sql){
        //开始事务
        db.beginTransaction();
        //执行语句
        db.execSQL(sql);
        //设置事务成功完成
        db.setTransactionSuccessful();
        //关闭db
        db.close();
        return true;
    }
}
