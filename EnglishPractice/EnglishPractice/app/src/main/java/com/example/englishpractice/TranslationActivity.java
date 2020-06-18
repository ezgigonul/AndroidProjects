package com.example.englishpractice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TranslationActivity extends AppCompatActivity {
    String lang;
    Spinner spinner_first;
    ArrayAdapter<CharSequence> adapter_first;
    Spinner spinner_second;
    ArrayAdapter<CharSequence> adapter_second;
    EditText sentence_text;
    TextView result_text;
    Button translate;
    ImageView sentence_hop, result_hop, transfer_img;
    String YandexKey = "trnsl.1.1.20200330T093451Z.5d63ebf10d3673dd.ce1c00cd78a7879d487c5361fb2eb466769e2e99";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translation);

        spinner_first = (Spinner) findViewById(R.id.language_first);
        adapter_first = ArrayAdapter.createFromResource(this,
                R.array.languages, android.R.layout.simple_spinner_item);
        adapter_first.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_first.setAdapter(adapter_first);

        spinner_second = (Spinner) findViewById(R.id.language_second);
        adapter_second = ArrayAdapter.createFromResource(this,
                R.array.languages, android.R.layout.simple_spinner_item);
        adapter_second.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_second.setAdapter(adapter_second);

        sentence_text = (EditText) findViewById(R.id.sentence);
        result_text = (TextView) findViewById(R.id.result);
        translate = (Button) findViewById(R.id.translate_button);
        transfer_img = (ImageView) findViewById(R.id.transfer);
        spinner_first.setSelection(1);
        spinner_second.setSelection(0);
        lang = "en-tr";

        transfer_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transfer();
            }
        });

        translate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lang = language();
                System.out.println(lang);
                String text = sentence_text.getText().toString();
                String query = null;
                try {
                    query = URLEncoder.encode(text, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String urlString = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + YandexKey + "&text=" + query + "&lang=" + lang;
                System.out.println(urlString);
                new TranslatorBackgroundTask().execute(urlString);
            }
        });

        //Receive implicit Intent to translate some text
        Intent receivedIntent = getIntent();
        String receivedText = receivedIntent.getStringExtra(Intent.EXTRA_TEXT);
        sentence_text.setText(receivedText);
    }


    public void transfer() {
        String language_first = spinner_first.getSelectedItem().toString();
        String language_second = spinner_second.getSelectedItem().toString();
        int position_first = adapter_first.getPosition(language_first);
        int position_second = adapter_second.getPosition(language_second);
        spinner_first.setSelection(position_second);
        spinner_second.setSelection(position_first);
    }

    public String language() {
        if (spinner_first.getSelectedItemPosition() == 0) {
            if (spinner_second.getSelectedItemPosition() == 0) {
                lang = "tr-tr";
            } else if (spinner_second.getSelectedItemPosition() == 1) {
                lang = "tr-en";
            } else {
                lang = "tr-de";
            }
        } else if (spinner_first.getSelectedItemPosition() == 1) {
            if (spinner_second.getSelectedItemPosition() == 0) {
                lang = "en-tr";
            } else if (spinner_second.getSelectedItemPosition() == 1) {
                lang = "en-en";
            } else {
                lang = "en-de";
            }
        } else {
            if (spinner_second.getSelectedItemPosition() == 0) {
                lang = "de-tr";
            } else if (spinner_second.getSelectedItemPosition() == 1) {
                lang = "de-en";
            } else {
                lang = "de-de";
            }
        }
        return lang;
    }

    class TranslatorBackgroundTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];
            StringBuilder jsonString = new StringBuilder();
            try {
                URL yandexUrl = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) yandexUrl.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    jsonString.append(line);
                }
                inputStream.close();
                bufferedReader.close();
                httpURLConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonString.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            JsonObject jsonobj = new JsonParser().parse(s).getAsJsonObject();
            String result = jsonobj.get("text").getAsString();
            result_text.setText(result);
        }
    }

}
