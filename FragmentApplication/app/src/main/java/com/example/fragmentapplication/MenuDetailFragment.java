package com.example.fragmentapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MenuDetailFragment extends Fragment {
    private long foodId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            foodId = savedInstanceState.getLong("foodId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            Menu menu = Menu.foods[(int) foodId];
            ImageView image = (ImageView) view.findViewById(R.id.imageView);
            image.setImageResource(menu.getImageResourceId());
            TextView title = (TextView) view.findViewById(R.id.textTitle);
            title.setText(menu.getName());
            TextView description = (TextView) view.findViewById(R.id.textDescription);
            description.setText(menu.getDescription());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("foodId", foodId);
    }

    public void setMenu(long id) {
        this.foodId = id;
    }
}
