package com.example.tabsviewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tabsviewpager.R;
import com.xeoh.android.texthighlighter.TextHighlighter;

public class SearchFragment extends Fragment {

    EditText edit;
    Button button;
    TextView txt;
    Boolean isHighLight = false;
    TextHighlighter textHighlighter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_search, container, false);
        edit=view.findViewById(R.id.edit);
        button=view.findViewById(R.id.button);
        txt=view.findViewById(R.id.txt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //resource https://github.com/xeoh/TextHighlighter
                if(!isHighLight)
                {
                    textHighlighter = new TextHighlighter();
                    textHighlighter.setBackgroundColor(Color.parseColor("#ffccff"))
                            .setForegroundColor(Color.BLACK)
                            .addTarget(txt)
                            .highlight(edit.getText().toString(),TextHighlighter.BASE_MATCHER );
                    button.setText("Remove Highlight");
                }
                else
                {
                    textHighlighter.setBackgroundColor(Color.TRANSPARENT)
                            .setForegroundColor(txt.getCurrentTextColor())
                            .invalidate(TextHighlighter.BASE_MATCHER);
                    button.setText("Search");
                }
                isHighLight = !isHighLight;
            }
        });

        return view;
    }

}
