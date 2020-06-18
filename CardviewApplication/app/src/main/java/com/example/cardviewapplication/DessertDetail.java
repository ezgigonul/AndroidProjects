package com.example.cardviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DessertDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert_detail);
        TextView dessert_name=(TextView)findViewById(R.id.dessert_name);
        ImageView dessert_image=(ImageView)findViewById(R.id.dessert_view);
        TextView dessert_ingredient=(TextView)findViewById(R.id.dessert_ingredient);
        TextView dessert_step=(TextView)findViewById(R.id.dessert_step);

        int position = getIntent().getIntExtra("pos", 0);
        Desserts desserts = Desserts.desserts[position];

        dessert_name.setText(desserts.getName());
        dessert_image.setImageResource(desserts.getPhoto_id());
        dessert_ingredient.setText(desserts.getIngredient());
        dessert_step.setText(desserts.getSteps());
    }
}
