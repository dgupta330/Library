package com.mrocks.mukul.library.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mrocks.mukul.library.Models.Customer;

public class CustomerDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "internet.db";
    private static final String TABLE_NAME = "customer";

    public CustomerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db = this.getWritableDatabase();
        db.execSQL("create table " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "NAME TEXT," +
                "MOBILE_NUMBER TEXT," +
                "AREA TEXT," +
                "COMPANY TEXT," +
                "PLANID INTEGER," +
                "PLANDETAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       // db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put("ID",customer.getId());
        content.put("NAME",customer.getName());
        content.put("MOBILE_NUMBER",customer.getMobile());
        content.put("AREA",customer.getArea());
        content.put("COMPANY",customer.getCompany());
        content.put("PLANID",customer.getPlanid());
        content.put("PLANDETAIL",customer.getPlandetail());

        long result= db.insert(TABLE_NAME,null,content);

        if(result!=-1)
        {
            return true;
        }
        else
            return false;
    }

    public Cursor getData(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME+" WHERE ID = '"+Integer.toString(id)+"'";
        Cursor cursor= db.rawQuery(query,null);
        return cursor;
    }

    public void deleteData(int id)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_NAME,"ID='"+Integer.toString(id)+"'",null);
        db.close();
    }

    public void updateData(Customer customer)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("ID",customer.getId());
        content.put("NAME",customer.getName());
        content.put("MOBILE_NUMBER",customer.getMobile());
        content.put("AREA",customer.getArea());
        content.put("COMPANY",customer.getCompany());
        content.put("PLANID",customer.getPlanid());
        content.put("PLANDETAIL",customer.getPlandetail());


        db.update(TABLE_NAME,content,"ID ='"+customer.getId()+"'",null);
        db.close();
    }


}

