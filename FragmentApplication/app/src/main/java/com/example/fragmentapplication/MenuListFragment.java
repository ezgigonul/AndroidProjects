package com.example.fragmentapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class MenuListFragment extends ListFragment {
    /** An interface for defining the callback method */
    static interface Listener {
        /** This method will be invoked when an item in the ListFragment is clicked */
        void itemClicked(long id);
    }

    private Listener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] food_names = new String[Menu.foods.length];
        for (int i = 0; i < food_names.length; i++) {
            food_names[i] = Menu.foods[i].getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(),
                android.R.layout.simple_list_item_1, food_names);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /** A callback function, executed when this fragment is attached to an activity */
    @Override
    public void onAttach(Context context) { //onAttach(Activity) called once the fragment is associated with its activity.
        super.onAttach(context);
        this.listener = (Listener) context;
    }

    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        if (listener != null) {
            listener.itemClicked(id);
            for (int i = 0; i < listView.getChildCount(); i++) {
                if(position == i ){
                    listView.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.red));
                }else{
                    listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
            }
        }
    }
}
