package com.example.englishpractice;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.ServiceCompat;
import androidx.fragment.app.Fragment;

//Child Fragmend
public class StopwatchFragment extends Fragment {

    private long millisecvalue;
    private OnChildFragmentInteractionListener mParentListener;

    int sec;
    TextView mCountDown;
    CountDownTimer countDownTimer;


    @Override
    public void onDetach() {
        super.onDetach();
        mParentListener = null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof OnChildFragmentInteractionListener) {
            mParentListener = (OnChildFragmentInteractionListener) getParentFragment();
        } else {
            throw new RuntimeException("The parent fragment must implement OnChildFragmentInteractionListener");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            millisecvalue=savedInstanceState.getLong("second",0);
        } else {
            millisecvalue = 10000;
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_stopwatch, container, false);
        mCountDown = view.findViewById(R.id.mCountDown);
        mCountDown.setTextColor(getResources().getColor(R.color.darkgreen));
        mParentListener.messageFromChildToParent("notend");
        startContinueTimer();
        return view;
    }

    private void startContinueTimer() {
        countDownTimer = new CountDownTimer(millisecvalue, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long check = millisUntilFinished;
                sec = (int) check / (1000);
                mCountDown.setText("0"+sec);
                millisecvalue = millisUntilFinished;    //update milisecvalue "VERY IMPORTANT"
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onFinish() {
                mCountDown.setTextColor(Color.RED);
                //sendNotification(getResources().getString(R.string.warning),getResources().getString(R.string.times));
            }
        }.start();
    }


    @Override
    public void onResume() {
        super.onResume();
        startContinueTimer();
    }

    public interface OnChildFragmentInteractionListener {
        void messageFromChildToParent(String myString);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) { //save state of fragment
        super.onSaveInstanceState(outState);
        outState.putLong("second",millisecvalue);
    }
}
