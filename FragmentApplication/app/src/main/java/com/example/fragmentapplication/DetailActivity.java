package com.example.fragmentapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        MenuDetailFragment fragment = (MenuDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        int menuId = (int) getIntent().getExtras().get(MainActivity.INTENY_KEY); //clicked item
        fragment.setMenu(menuId);
    }
}