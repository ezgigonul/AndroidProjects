package com.example.englishpractice;

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
    private static final String DATABASE_NAME = "sqllite_database";

    private static final String TABLE_NAME = "user_info";
    private static String USER_ID = "id";
    private static String USER_NAME = "username";
    private static String USER_EMAIL = "email";
    private static String USER_PASSWORD = "password";
    private static String USER_SCORE = "score";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_NAME + " TEXT,"
                + USER_EMAIL + " TEXT,"
                + USER_PASSWORD + " TEXT,"
                + USER_SCORE + " INTEGER DEFAULT 0" + ")";
        db.execSQL(CREATE_TABLE);
    }

    public boolean insert(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, username);
        contentValues.put(USER_EMAIL, email);
        contentValues.put(USER_PASSWORD, password);
        db.replace(TABLE_NAME, null, contentValues);
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void resetTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public boolean checkUserExist(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME + " WHERE " + USER_NAME + "='" + username + "' LIMIT 1)";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        // cursor.getInt(0) is 1 if column with value exists
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return false;
        } else {
            cursor.close();
        }
        return true;
    }

    public Cursor getPassword(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + USER_PASSWORD + " FROM " + TABLE_NAME + " WHERE " + USER_NAME + "='" + username + "'";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public Cursor getScore(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + USER_SCORE + " FROM " + TABLE_NAME + " WHERE " + USER_NAME + "='" + username + "'";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public void updateScore(int Score,String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE " + TABLE_NAME +" SET "+ USER_SCORE +" = "+ Score + " WHERE "+ USER_NAME + "='" + username + "'";
        db.execSQL(strSQL);
    }

    public ArrayList<User> scoreList(){
        ArrayList<User> score_list=new ArrayList<User>();
        SQLiteDatabase db=this.getWritableDatabase();
        String[] columns={USER_NAME,USER_SCORE};
        Cursor cr=db.query(TABLE_NAME,columns,null,null,null,null,null);
        while(cr.moveToNext()){
                score_list.add(new User(cr.getString(0),cr.getString(1)));

        }
        return score_list;
    }
}
