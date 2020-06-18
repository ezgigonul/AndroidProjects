package com.example.englishpractice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBoundService extends Service {

    private final static String TAG = MyBoundService.class.getSimpleName();
    private boolean checkisopen;

    class MyLocalBinder extends Binder {
        public MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    private IBinder myLocalBinder = new MyLocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "Bounded Service onBind");
        return myLocalBinder;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "Bounded Service onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "Bounded Service onDestroy");
        checkisopen = false;
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Bounded Service onStartCommand");

        checkisopen = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                getCurrentTime();
            }
        }).start();
        return START_STICKY;
    }

    public String getCurrentTime() {
        String format = null;
        if (checkisopen) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            format = simpleDateFormat.format(new Date());
            Log.i(TAG, "format : " + format + " Thread Adı: " + Thread.currentThread().getName());
        }
        return format;
    }
}
