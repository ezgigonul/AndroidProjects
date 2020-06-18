package com.example.nestedapp;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class RightFragment extends Fragment{

    private RightFragmentListener callRightChildFragment;
    TextView text_right;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_right, container, false);
        Button left_lauch = (Button) view.findViewById(R.id.button_right_launched);
        text_right=(TextView)view.findViewById(R.id.right_txt);
        left_lauch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRightChildFragment.openRightChildFragment();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RightFragmentListener) {
            callRightChildFragment = (RightFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement RightFragmentListener");
        }
    }

    public interface RightFragmentListener {
        void openRightChildFragment();
    }

    public void chanceRightText() {
        text_right.setText(R.string.right_child_text);
    }
}
