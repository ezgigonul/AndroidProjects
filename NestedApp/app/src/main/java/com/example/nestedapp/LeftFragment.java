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
public class LeftFragment extends Fragment{

    private LeftFragmentListener callLeftChildFragment;
    TextView text_left;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        Button left_lauch = (Button) view.findViewById(R.id.button_left_launched);
        text_left=(TextView)view.findViewById(R.id.left_txt);
        left_lauch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callLeftChildFragment.openLeftChildFragment();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LeftFragmentListener) {
            callLeftChildFragment = (LeftFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LeftFragmentListener");
        }
    }

    public interface LeftFragmentListener {
        void openLeftChildFragment();
    }

    public void chanceLeftText() {
        text_left.setText(R.string.left_child_text);
    }
}
