package com.safecounts.safecounts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper {
    public static final String database_name = "speed_data.db";
    public static final String table_name = "speed_table";
    public static final String col1 = "Time";
    public static final String col2 = "Speed";
    public static final String col3 = "Latitude";
    public static final String col4 = "Longitude";

    public databaseHelper(Context context) {
        super(context,database_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + table_name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,TIME FLOAT,SPEED FLOAT,LATITUDE DOUBLE,LONGITUDE DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table_name);
        onCreate(db);
    }

    public boolean insertData(String time,String speed,String latitude,String longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,time);
        contentValues.put(col2,speed);
        contentValues.put(col3,latitude);
        contentValues.put(col4,longitude);
        long result = db.insert(table_name,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table_name,null);
        return res;
    }

    public boolean updateData(String id,String time,String speed,String latitude,String longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put(col1,time);
        contentValues.put(col2,speed);
        contentValues.put(col3,latitude);
        contentValues.put(col4,longitude);
        db.update(table_name, contentValues, "ID = ?",new String[] { id });
        return true;
    }
}
