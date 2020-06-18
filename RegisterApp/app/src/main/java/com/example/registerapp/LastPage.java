package com.example.registerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LastPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);
        Intent intent = getIntent();
        String n = intent.getStringExtra("name");
        String e = intent.getStringExtra("email");
        String p = intent.getStringExtra("password");
        String r = intent.getStringExtra("radioChosen");
        ImageView img=(ImageView)findViewById(R.id.image);

        if (r.equals("female")) {
            img.setImageResource(R.drawable.female);
        } else {
            img.setImageResource(R.drawable.male);
        }

        TextView textname = (TextView) findViewById(R.id.names);
        textname.setText(n);

        TextView textmail = (TextView) findViewById(R.id.emails);
        textmail.setText(e);

        TextView textpassword = (TextView) findViewById(R.id.passwords);
        textpassword.setText(p);

    }
}
