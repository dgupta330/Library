package com.mrocks.mukul.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Er.Deepak_kumar on 05-12-2017.
 */

public class CustomerDataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";

    CustomerDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = this.getWritableDatabase();
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT,MOBILE_NUMBER TEXT,EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db = this.getReadableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insert(String name, String mobile,String email) {
    SQLiteDatabase db = this.getReadableDatabase();
        ContentValues content = new ContentValues();
        content.put("NAME",name);
        content.put("MOBILE_NUMBER",mobile);
        content.put("EMAIL",email);

        long result= db.insert(TABLE_NAME,null,content);

        if(result!=-1)
        {
            return true;
        }
        else
            return false;
    }

    public Cursor getData(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME+" WHERE NAME = '"+name+"'";
        Cursor cursor= db.rawQuery(query,null);
        return cursor;
    }

    public void deleteData(String name)
    {
        SQLiteDatabase db= this.getReadableDatabase();
        db.delete(TABLE_NAME,"NAME='"+name+"'",null);
        db.close();
    }

    public void updateData(String name,String mobile,String email)
    {
        SQLiteDatabase db= this.getReadableDatabase();
        ContentValues content = new ContentValues();
        content.put("NAME",name);
        content.put("MOBILE_NUMBER",mobile);
        content.put("EMAIl",email);

        db.update(TABLE_NAME,content,"NAME ='"+name+"'",null);
        db.close();
    }


}

