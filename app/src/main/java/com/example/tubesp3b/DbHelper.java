package com.example.tubesp3b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.CpuUsageInfo;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;

    private static final String DB_NAME = "TravellyGo_db";

    private static final String TABLE_TOKEN = "token_table";
    private static final String COL1_TOKEN = "token";
    private static final String COL2_USERNAME = "username";
    private static final String COL3_JUMLAHORDER = "jumlah_order";
    private static final String COL4_JUMLAHPOINT = "jumlah_point";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        String createTableFilm = "CREATE TABLE " + TABLE_TOKEN + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL1_TOKEN + " TEXT, "+ COL2_USERNAME +" TEXT, "+COL3_JUMLAHORDER+" INTEGER, "+COL4_JUMLAHPOINT+" INTEGER)";
        db.execSQL(createTableFilm);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        this.db = sqLiteDatabase;
        db.execSQL("DROP TABLE IF  EXISTS "+TABLE_TOKEN);
        onCreate(db);
    }

//    Simpan token, username, jumlah order, jumlah point user yang baru login ke dalam db
    public boolean addUser(String token, String username, int jumlahOrder, int jumlahPoint){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1_TOKEN, token);
        cv.put(COL2_USERNAME, username);
        cv.put(COL3_JUMLAHORDER,jumlahOrder);
        cv.put(COL4_JUMLAHPOINT,jumlahPoint);

        long res = db.insert(TABLE_TOKEN, null, cv);

        if(res==-1){
            return false;
        }else{
            return true;
        }
    }

//    Method untuk delete token dari db
    public boolean deleteToken(String token){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_TOKEN, "token=?",new String[]{ token });
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

//    Method untuk get token dari db
    public String getToken(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_TOKEN,null);
        String temp = null;
        while(res.moveToNext()){
            temp = res.getString(1);
        }
        return temp;
    }

//    method untuk delete username dari db
    public boolean deleteUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_TOKEN, "username=?",new String[]{ username });
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

//    method untuk get username dari db
    public String getUsername(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT *  FROM "+TABLE_TOKEN,null);
        String temp = null;
        while(res.moveToNext()){
            temp = res.getString(2);
        }
        return temp;
    }

//  method untuk get jumlah point dari db
    public int getJumlahPoint(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT *  FROM "+TABLE_TOKEN,null);
        int temp=0;
        while(res.moveToNext()){
            temp = res.getInt(4);
        }
        return temp;
    }

//    method untuk get jumlah order dari db
    public int getJumlahOrder(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT *  FROM "+TABLE_TOKEN,null);
        int temp=0;
        while(res.moveToNext()){
            temp = res.getInt(3);
        }
        return temp;
    }

//    method untuk update jumlah order, jumlah poin berdasarkan token tertentu. Setiap user punya token unik yang didapat pada saat login
    public boolean updatePoinAndOrder(int jumlahOrder, int jumlahPoint, String token){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL3_JUMLAHORDER, jumlahOrder);
        cv.put(COL4_JUMLAHPOINT, jumlahPoint);

        long res = db.update(TABLE_TOKEN, cv, "token=?", new String[]{ token });

        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }
}


