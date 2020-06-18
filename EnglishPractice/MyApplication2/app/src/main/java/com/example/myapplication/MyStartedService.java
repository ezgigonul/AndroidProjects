package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyStartedService extends Service {

    MediaPlayer player;

    private static final String TAG = MyStartedService.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.i(TAG, "OnCreate Çağrıldı " + " " + Thread.currentThread().getName());

        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "OnStartCommand Çağrıldı " + " " + Thread.currentThread().getName());


        player = MediaPlayer.create(MyStartedService.this, R.raw.background_music);
        player.setLooping(true);
        player.start();


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "OnDestroy Çağrıldı " + " " + Thread.currentThread().getName());

        player.stop();
        super.onDestroy();
    }


}
