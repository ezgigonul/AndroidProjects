package com.example.multipleactivitiesandintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE="message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent=getIntent();
        String string=intent.getStringExtra(EXTRA_MESSAGE);
        TextView tx=(TextView)findViewById(R.id.message);
        tx.setText(string);
    }
}
