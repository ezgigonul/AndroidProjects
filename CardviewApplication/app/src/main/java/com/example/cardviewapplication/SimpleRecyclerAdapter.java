package com.example.cardviewapplication;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView card_view;
        public ImageView card_image_view;
        public TextView card_name;
        public TextView card_description;

        public ViewHolder(View view) {
            super(view);
            card_view = (CardView)view.findViewById(R.id.card_view);
            card_image_view=(ImageView) view.findViewById(R.id.card_background);
            card_name = (TextView)view.findViewById(R.id.card_name);
            card_description = (TextView)view.findViewById(R.id.card_description);
        }
    }

    List<Desserts> list_desserts;
    CustomItemClickListener listener;
    public SimpleRecyclerAdapter(List<Desserts> list_desserts, CustomItemClickListener listener) {

        this.list_desserts = list_desserts;
        this.listener = listener;
    }


    @Override
    public SimpleRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, view_holder.getPosition());
            }
        });

        return view_holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.card_name.setText(list_desserts.get(position).getName());
        holder.card_description.setText(list_desserts.get(position).getCard_description());
        holder.card_image_view.setBackgroundResource(list_desserts.get(position).getPhoto_id());
    }

    @Override
    public int getItemCount() {
        return list_desserts.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
