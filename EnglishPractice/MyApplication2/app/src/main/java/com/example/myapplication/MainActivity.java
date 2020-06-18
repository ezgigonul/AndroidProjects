package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startMyBoundService(View view) {
        Intent intent = new Intent(this, MyStartedService.class);
        startService(intent);
    }

    public void stopMyBoundService(View view) {
        Intent intent = new Intent(this, MyStartedService.class);
        stopService(intent);
    }

}
