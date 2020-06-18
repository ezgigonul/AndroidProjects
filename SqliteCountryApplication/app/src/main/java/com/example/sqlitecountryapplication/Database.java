package com.example.sqlitecountryapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "sqllite_country_database";//database adı

    private static final String TABLE_NAME = "currency_list";
    private static String COUNTRY = "country";
    private static String ID = "id";
    private static String CURRENCY = "currency";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COUNTRY + " TEXT,"
                + CURRENCY + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }

    public void deleteRecord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void addRecord(String country_name, String currency_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNTRY, country_name);
        values.put(CURRENCY, currency_name);
        db.insert(TABLE_NAME, null, values);
        db.close(); //Database Bağlantısını kapattık*/
    }

    public void updateRecord(String country_name, String currency_name,int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNTRY, country_name);
        values.put(CURRENCY, currency_name);

        // updating row
        db.update(TABLE_NAME, values, ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    public List<Data> ListData(){
        List<Data> info=new ArrayList<Data>();
        SQLiteDatabase db=this.getWritableDatabase();
        String[] sutunlar={ID,COUNTRY,CURRENCY};
        Cursor cr=db.query(TABLE_NAME,sutunlar,null,null,null,null,null);
        while(cr.moveToNext()){
            Data data=new Data(Integer.parseInt(cr.getString(0)),cr.getString(1),cr.getString(2));
            info.add(data);

        }
        return info;
    }

    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        // return row count
        return rowCount;
    }

}

