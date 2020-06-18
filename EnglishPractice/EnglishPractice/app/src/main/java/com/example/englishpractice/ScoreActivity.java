package com.example.englishpractice;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {
    Database db = new Database(this);
    ArrayList<User> user_list = new ArrayList<User>();
    ArrayList<User> user_list_clone = new ArrayList<User>();
    ListView score_list_view;
    Toolbar toolbar;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    ScoreListAdapter scoreAdapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        //up navigation implementation
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user_list = db.scoreList();
        scoreAdapter = new ScoreListAdapter(this, user_list);
        score_list_view = (ListView) findViewById(R.id.list);

        //Delete user selected item from ListView
        score_list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                ModifyListView(pos);
                return true;
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.score_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] ArrayValue = getResources().getStringArray(R.array.score_array);
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals(ArrayValue[0])) {
                   Unsort();
                } else {
                    Sort();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //Modify ListView
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ModifyListView(int pos)
    {
            user_list.remove(pos);
            score_list_view.setAdapter(new ScoreListAdapter(this, user_list));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menunew, menu);

        MenuItem shareItem = menu.findItem(R.id.action_share_score);

        ShareActionProvider myShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        //Share score with implicit intent
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, user_list.toString());
        myShareActionProvider.setShareIntent(sharingIntent);

        //Toolbar search(filter) user's name
        MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                scoreAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    //Create sorted list with custom Adapter
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Sort() {
        user_list_clone = (ArrayList<User>) user_list.clone();
        user_list_clone.sort(new ScoreSorter());

        scoreAdapter = new ScoreListAdapter(this, user_list_clone);
        ListView score_list_view = (ListView) findViewById(R.id.list);
        score_list_view.setAdapter(scoreAdapter);
    }

    //Create unsorted list with custom Adapter
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Unsort() {
        user_list_clone = (ArrayList<User>) user_list.clone();
        user_list_clone.sort(new ScoreSorter().reversed());

        scoreAdapter = new ScoreListAdapter(this, user_list_clone);
        ListView score_list_view = (ListView) findViewById(R.id.list);
        score_list_view.setAdapter(scoreAdapter);
    }


}
