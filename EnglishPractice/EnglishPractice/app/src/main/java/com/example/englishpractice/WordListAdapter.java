package com.example.englishpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WordListAdapter extends BaseAdapter {

    Context context;
    private final String [] words_list;
    private final String [] descriptions_list;
    private final int [] images_list;

    public WordListAdapter(Context context, String[] words_list, String[] descriptions_list, int[] images_list) {
        this.context = context;
        this.words_list = words_list;
        this.descriptions_list = descriptions_list;
        this.images_list = images_list;
    }

    @Override
    public int getCount() {
        return words_list.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.custom_list_item_words, parent, false);
            viewHolder.txtWord = (TextView) convertView.findViewById(R.id.word_text);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.description_text);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.word_images);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtWord.setText(words_list[position]);
        viewHolder.txtDescription.setText(descriptions_list[position]);
        viewHolder.icon.setImageResource(images_list[position]);

        return convertView;
    }

    private static class ViewHolder {

        TextView txtWord;
        TextView txtDescription;
        ImageView icon;

    }

}
