package com.example.xihad_task;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class database extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "demo_bd";
    private static final int DATABASE_VERSION = 2;

    public database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("CREATE TABLE  user_data (id INTEGER PRIMARY KEY AUTOINCREMENT,user_id INTEGER ,demoID INTEGER,title TEXT,body TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP  TABLE IF EXISTS user_data");
        onCreate(sqLiteDatabase);
    }

    public  void instertData(UserDataModel userDataModel){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("user_id",userDataModel.userID);
        contentValues.put("demoID",userDataModel.id);
        contentValues.put("title",userDataModel.title);
        contentValues.put("body",userDataModel.body);
        sqLiteDatabase.insert("user_data",null,contentValues);

        try {
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.insertOrThrow("user_data", null, contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("insertData", "Error inserting data", e);
        } finally {
            sqLiteDatabase.endTransaction();
        }



    }


}
