package com.example.getapp.api;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.getapp.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private List<Result> dat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gam();
        //hom();
    }

    public void gam() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        APIService apis = retrofit.create(APIService.class);
        Call<VeriListem> call = apis.verilerimilistele();
        call.enqueue(new Callback<VeriListem>() {
            @Override
            public void onResponse(Call<VeriListem> call, Response<VeriListem> response) {
                dat = response.body().getInfo();
                for (int i = 0; i < dat.size(); i++) {
                    System.out.println(dat.get(i).toString());
                }
            }

            @Override
            public void onFailure(Call<VeriListem> call, Throwable t) {
                Log.d("snow", t.getMessage().toString());
            }
        });

    }

    public void hom() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        APIService apis = retrofit.create(APIService.class);
        Call<PostListem> call = apis.at(false, "2020-02-17T22:51:00.573624+03:00",1,4);
        call.enqueue(new Callback<PostListem>() {
            @Override
            public void onResponse(Call<PostListem> call, Response<PostListem> response) {
                Log.d("snow", response.body().getClosedDate());
            }

            @Override
            public void onFailure(Call<PostListem> call, Throwable t) {

            }
        });
    }

}
