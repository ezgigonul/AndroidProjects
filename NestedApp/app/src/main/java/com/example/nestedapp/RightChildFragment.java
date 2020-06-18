package com.example.nestedapp;


import android.content.Context;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class RightChildFragment extends Fragment {
    private RightChildFragment.RightChildFragmentListener callRightParentFragment;

    public RightChildFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_right_child, container, false);
        ImageButton right_child_button = (ImageButton) view.findViewById(R.id.arrowButton);
        final TextView current_time_txt = (TextView) view.findViewById(R.id.current_time_right);
        current_time_txt.setText(getTime());


        right_child_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_time_txt.setText(getTime());
                callRightParentFragment.openRightParentFragment();
            }
        });

        return view;
    }

    public interface RightChildFragmentListener {
        void openRightParentFragment();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RightChildFragment.RightChildFragmentListener) {
            callRightParentFragment = (RightChildFragment.RightChildFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LeftChildFragmentListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        callRightParentFragment = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getTime() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm");
        // you can get seconds by adding  "...:ss" to it
        date.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
        String localTime = date.format(currentLocalTime);
        return localTime;
    }

}
