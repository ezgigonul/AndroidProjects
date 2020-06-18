package com.example.fragmentapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/*
EZGİ GÖNÜL 15070001022
 */

public class MainActivity extends AppCompatActivity implements MenuListFragment.Listener {
    public final static String INTENY_KEY="KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View someView = findViewById(R.id.fragment);
        View root = someView.getRootView();
        root.setBackground(getResources().getDrawable(R.drawable.background)); //activity_main background

    }

    @Override
    public void itemClicked(long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(INTENY_KEY,(int) id);
        startActivity(intent);
    }
}
