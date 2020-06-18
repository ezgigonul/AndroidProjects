package com.example.englishpractice;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class MyStartedService extends Service {

    MediaPlayer player;
    public static int StartValue=0;
    private static final String TAG = MyStartedService.class.getSimpleName();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        Log.i(TAG, "Started Service OnCreate Çağrıldı " + " " + Thread.currentThread().getName());
        super.onCreate();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Started Service OnStartCommand Çağrıldı " + " " + Thread.currentThread().getName());

        player = MediaPlayer.create(MyStartedService.this, R.raw.background_music);
        player.setLooping(true);
        player.start();
        StartValue=1;

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "Started Service OnDestroy Çağrıldı " + " " + Thread.currentThread().getName());
        player.stop();
        StartValue=0;
        super.onDestroy();
    }

}
