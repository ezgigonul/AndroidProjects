package com.example.englishpractice;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class QuizActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    int duration = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String name= LoginActivity.login_username;

        Database db = db = new Database(this);
        TextView hello = (TextView) findViewById(R.id.hello);
        TextView rules = (TextView) findViewById(R.id.rules);
        TextView username = (TextView) findViewById(R.id.username);
        TextView score = (TextView) findViewById(R.id.score);
        TextView userscore = (TextView) findViewById(R.id.userscore);
        linearLayout=(LinearLayout)findViewById(R.id.linear_layout);
        Cursor cursor = db.getScore(name);

        if( cursor != null && cursor.moveToFirst() ){
            username.setText(name);

            userscore.setText(cursor.getString(0)); //Read user score with Cursor
            cursor.close();

            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/indieflower.ttf");
            hello.setTypeface(custom_font);
            username.setTypeface(custom_font);
            score.setTypeface(custom_font);
            userscore.setTypeface(custom_font);
            rules.setTypeface(custom_font);
        }
    }

    //SnackBar implementation
    public void snackWithCustomTiming(View content, String message, int duration){
        final Intent intent = new Intent(this, QuizQuestions.class);
        final Snackbar snackbar = Snackbar.make(content, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                snackbar.dismiss();
                startActivity(intent);
            }
        },duration);
    }

    //Float Button Action
    public void startQuiz(View view) {
        snackWithCustomTiming(linearLayout,getString(R.string.ready), duration);
    }
}
