package com.example.englishpractice;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//Custom List Adapter
public class ScoreListAdapter extends BaseAdapter implements Filterable {

    private LayoutInflater mInflater;
    private ArrayList<User> userArrayList;
    private List<User> mOriginalValues;

    public ScoreListAdapter(Activity activity, ArrayList<User> userArrayList) {
        this.mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.userArrayList = userArrayList;
    }


    @Override
    public int getCount() {
        return userArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return userArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.custom_list_view_score, null);
        TextView username = (TextView) convertView.findViewById(R.id.list_row_textview_username);
        TextView score = (TextView) convertView.findViewById(R.id.list_row_textview_score);
        User user = userArrayList.get(position);
        username.setText(user.getUsername());
        score.setText(user.getScore());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                userArrayList = (ArrayList<User>) results.values; // has

                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<User> FilteredArrList = new ArrayList<User>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<User>(userArrayList); // saves

                }
                /********
                 *
                 * If constraint(CharSequence that is received) is null returns
                 * the mOriginalValues(Original) values else does the Filtering
                 * and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    Locale locale = Locale.getDefault();
                    constraint = constraint.toString().toLowerCase(locale);
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        User user = mOriginalValues.get(i);
                        String data = user.getUsername();
                        if (data.toLowerCase(locale).contains(constraint.toString())) {
                            FilteredArrList.add(user);
                        }
                    }
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

}

