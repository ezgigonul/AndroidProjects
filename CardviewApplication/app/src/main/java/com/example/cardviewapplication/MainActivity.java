package com.example.cardviewapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*EZGİ GONUL 15070001022*/
public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private Context mCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        recycler_view.setLayoutManager(layoutManager);

        mCtx=this;

        List<Desserts> desserts_list;
        desserts_list= Arrays.asList(Desserts.desserts);

        SimpleRecyclerAdapter adapter_items = new SimpleRecyclerAdapter(desserts_list, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) { //sent clicked item position
                Intent intent = new Intent(mCtx, DessertDetail.class);
                intent.putExtra("pos", position);
                System.out.println(position);
                startActivity(intent);
            }
        });
        recycler_view.setHasFixedSize(true);
        recycler_view.setAdapter(adapter_items);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
    }
}
