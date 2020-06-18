package com.example.sqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DataBase extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "Quotes"; //Tablo Adı
    private static final String DATABASE_NAME = "SqliteDB";  //Veritabanı Adı
    private static final int DATABASE_VERSION = 1;
    private static final String WRITER_NAME = "WriterName";
    private static final String QUOTES = "Quotes";
    SQLiteDatabase sqLiteDatabase;
    private Cursor mCur;
    private Cursor mCurRandom;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT," + WRITER_NAME + " TEXT," + QUOTES + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE İF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void DeleteAllRecord() { //Delete All records from DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }

    public void DropTable() { //Drop table from DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.close();
    }


    public void addData(String writer, String quote) { //Insert new data to Table
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WRITER_NAME, writer.trim());
        cv.put(QUOTES, quote.trim());
        long r = db.insert(TABLE_NAME, null, cv);
        if (r > -1)
            Log.i("tag", "Successful");
        else
            Log.e("tag", "Unsuccessful");
        db.close();
    }


    public Cursor getRandomData() { //Select Random value from Table
        Cursor mCurRandom;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY RANDOM() LIMIT 1", null);
        try {
            if (res != null) {
                return res;
            }
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
        return res;
    }

}
