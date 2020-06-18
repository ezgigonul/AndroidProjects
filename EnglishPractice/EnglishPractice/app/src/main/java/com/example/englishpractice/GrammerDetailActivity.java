package com.example.englishpractice;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GrammerDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammer_detail);
        TextView grammer_name = (TextView) findViewById(R.id.grammer_name);
        ImageView grammer_image = (ImageView) findViewById(R.id.grammer_view);
        TextView grammer_description = (TextView) findViewById(R.id.grammer_desc);
        TextView grammer_example = (TextView) findViewById(R.id.grammer_ex);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/indieflower.ttf");
        grammer_description.setTypeface(custom_font);
        grammer_example.setTypeface(custom_font);

        int position = getIntent().getIntExtra("pos", 0);
        Grammer grammer = GrammerFragment.grammerList.get(position);

        grammer_name.setText(grammer.getGrammer_name());
        grammer_image.setImageResource(grammer.getGrammer_photo_id());
        grammer_description.setText(grammer.getGrammer_description());
        grammer_example.setText(grammer.getGrammer_example());

    }
}

