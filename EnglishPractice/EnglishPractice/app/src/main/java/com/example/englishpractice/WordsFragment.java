package com.example.englishpractice;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordsFragment extends Fragment {
    Context ctx;
    int[] images_list;
    String[] word_list;
    String[] description_list;
    ListView lView;
    WordListAdapter lAdapter;

    public WordsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ctx = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        {
            // Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.fragment_words, container, false);

            lView = (ListView) v.findViewById(R.id.wordList);
            images_list = new int[]{R.drawable.above,R.drawable.across,R.drawable.act,R.drawable.afraid,R.drawable.against,R.drawable.ahead,R.drawable.air,R.drawable.all,R.drawable.alone,R.drawable.always,R.drawable.animal,R.drawable.answer,R.drawable.apple,R.drawable.army};
            word_list = new String[]{getString(R.string.above),getString(R.string.across),getString(R.string.act),getString(R.string.afraid),getString(R.string.against),getString(R.string.ahead),getString(R.string.air),getString(R.string.all),getString(R.string.alone),getString(R.string.always),getString(R.string.animal),getString(R.string.answer),getString(R.string.apple),getString(R.string.army)};
            description_list = new String[]{getString(R.string.above_desc),getString(R.string.across_desc),getString(R.string.act_desc),getString(R.string.afraid_desc),getString(R.string.against_desc),getString(R.string.ahead_desc),getString(R.string.air_desc),getString(R.string.all_desc),getString(R.string.alone_desc),getString(R.string.always_desc),getString(R.string.animal_desc),getString(R.string.answer_desc),getString(R.string.apple_desc),getString(R.string.army_desc)};


            lAdapter = new WordListAdapter(ctx, word_list, description_list, images_list);
            lView.setAdapter(lAdapter);
            lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Toast.makeText(ctx, word_list[i]+":"+ " " + description_list[i], Toast.LENGTH_SHORT).show();

                }

            });
            return v;
        }

    }
}
