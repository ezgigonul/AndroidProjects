package com.example.sqliteapplication;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/*EZGİ GONUL 15070001022 */

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    DataBase myDb = null;
    EditText writer_name;
    EditText quote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DataBase(this); //create DB
        myDb.addData("Stephen King","If you don't have time to read, you don't have the time to write. Simple as that.");
        myDb.addData("Anais Nin","We write to taste life twice, in the moment and in retrospect.");
        myDb.addData("Jack Kerouac King","One day I will find the right words, and they will be simple.");

    }


    public void AddQuotes(View view) { //Add quotes to DB
        writer_name = findViewById(R.id.writer);
        quote = findViewById(R.id.quote);
        String writer = writer_name.getText().toString();
        String quotes = quote.getText().toString();
        myDb.addData(writer,quotes);
        Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
    }

    public void ShowRandomQuotes(View view) {
        Cursor res = myDb.getRandomData();
        if (res.getCount() == 0) { //If there is no record in Table
            showMessage("Error", "Nothing Found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) { //get Random quotes to DB
            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Author :" + res.getString(1) + "\n");
            buffer.append("Quote :" + res.getString(2) + "\n\n");
        }
        showMessage("Data", buffer.toString());
    }

    public void showMessage(String title, String Message) { //Show Random Data in AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
