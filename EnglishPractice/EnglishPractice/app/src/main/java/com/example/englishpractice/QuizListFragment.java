package com.example.englishpractice;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

//Static Fragment

public class QuizListFragment extends ListFragment {
    static interface Listener {
        void itemClicked(long id);
    };

    private Listener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] names = new String[Question.questions.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = Question.questions[i].getQuestion();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),
                R.layout.mytextview, names);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (Listener)context;
    }

    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        if (listener != null) {
            listener.itemClicked(position);
        }
    }


}
