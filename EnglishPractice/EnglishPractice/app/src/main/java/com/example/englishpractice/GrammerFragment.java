package com.example.englishpractice;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GrammerFragment extends Fragment {

    private RecyclerView recycler_view;
    private Context mCtx;
    private View view;
    public static List<Grammer> grammerList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_grammer, container, false);
        recycler_view = (RecyclerView)view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);
        mCtx=getContext();

        grammerList.removeAll(grammerList);
        grammerList.add(new Grammer(getString(R.string.simple_present_tense).toString(),getString(R.string.simple_present_tense_desc).toString(),"I smoke\nI work in London\nYour exam starts at 09.00\nI do not clean the room.\nHe doesn't clean the room.\nDo I play football?\nDoes he play football?",R.drawable.simple_present_tense));
        grammerList.add(new Grammer(getString(R.string.present_continuous).toString(),getString(R.string.present_continuous_desc).toString(),"Peter is reading a book now.\nWe are going to Basel on Saturday.\nI am not playing football.\nHe isn't playing football.\nIs he playing football?\nAm I playing football?\nAndrew is always coming late.",R.drawable.present_continuous_tense));
        grammerList.add(new Grammer(getString(R.string.simple_past).toString(),getString(R.string.simple_past_desc).toString(),"I sometimes walked home at lunchtime.\nWe saw a good film last week.\nShe finished her work atseven o'clock\nThey weren't in Rio last summer.\nDid you have a bicycle when you were young?\nWe didn't come because it was raining.",R.drawable.simple_past_tense));
        grammerList.add(new Grammer(getString(R.string.future).toString(),getString(R.string.future_desc).toString(),"The sun will shine tomorrow.\nYou will win the match.\nHe will not win the match.\nWill he win the match?\nWe are going to sing at the party.\nI'm not going to play handball.\nAm I going to play handball?\nHe's going to read the book.\n",R.drawable.future_tense));

        //Attach data to RecyclerView
        SimpleRecyclerAdapter adapter_items = new SimpleRecyclerAdapter(grammerList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) { //sent clicked item position
                Intent intent = new Intent(mCtx, GrammerDetailActivity.class);
                System.out.println(position);
                startActivity(intent);
            }
        });
        recycler_view.setHasFixedSize(true);
        recycler_view.setAdapter(adapter_items);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        return view;
    }
}


