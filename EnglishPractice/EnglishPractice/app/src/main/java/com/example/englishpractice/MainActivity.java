package com.example.englishpractice;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    MyBoundService myBoundService;
    boolean isBound = false;
    Intent intent,service_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Start Bound Service
        service_intent = new Intent(this, MyBoundService.class);
        startService(service_intent);
}


    public void loginPage(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        stopService(service_intent);

    }

    public void quiz_card_click(View view) {
        //Explicit intent to open QuizActivity
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
        stopService(service_intent);
    }

    public void score_card_click(View view) {
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
        stopService(service_intent);
    }

    public void translation_card_click(View view) {
        Intent intent = new Intent(this, TranslationActivity.class);
        startActivity(intent);
        stopService(service_intent);
    }

    public void tutorial_card_click(View view) {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
        stopService(service_intent);
    }

}