package com.example.multipleactivitiesandintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String KEY="message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSendMessage(View view) {
        EditText edit=(EditText)findViewById(R.id.edt);
        String str=edit.getText().toString();
        //Intent intent=new Intent(this,Main2Activity.class);
        //intent.putExtra(Main2Activity.EXTRA_MESSAGE,str);
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,str); //Intent.EXTRA_TEXT yazmak gerekıyot
        Intent createChooser=Intent.createChooser(intent,"Select Activity");
        startActivity(intent);
    }
}
