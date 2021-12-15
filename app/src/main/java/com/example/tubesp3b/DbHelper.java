package com.example.tubesp3b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.CpuUsageInfo;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;

    private static final String DB_NAME = "TravellyGo_db";

    private static final String TABLE_TOKEN = "token_table";
    private static final String COL1_TOKEN = "token";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        String createTableFilm = "CREATE TABLE " + TABLE_TOKEN + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL1_TOKEN + " TEXT)";
        db.execSQL(createTableFilm);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        this.db = sqLiteDatabase;
        db.execSQL("DROP TABLE IF  EXISTS "+TABLE_TOKEN);
        onCreate(db);
    }

    public boolean addToken(String token){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1_TOKEN, token);

        long res = db.insert(TABLE_TOKEN, null, cv);

        if(res==-1){
            return false;
        }else{
            return true;
        }
    }

    public boolean deleteToken(String token){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_TOKEN, "token=?",new String[]{ token });
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

    public String getToken(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_TOKEN,null);
        String temp = null;
        while(res.moveToNext()){
            temp = res.getString(0);
        }
        return temp;
    }
}
