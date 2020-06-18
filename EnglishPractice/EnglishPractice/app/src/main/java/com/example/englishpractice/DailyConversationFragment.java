package com.example.englishpractice;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DailyConversationFragment extends Fragment {

    private ViewPager viewpager;
    private LinearLayout liner;
    private SlideAdapter myadapter;
    private TextView[] mdots;
    private Button next,back;
    private int mCureentPage;

    public DailyConversationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_daily_conversation, container, false);

        liner=(LinearLayout)view.findViewById(R.id.dots);
        next=(Button)view.findViewById(R.id.nextBtn);
        back=(Button)view.findViewById(R.id.backBtn);
        myadapter=new SlideAdapter(getContext());

        //ViewPager implementation
        viewpager=(ViewPager)view.findViewById(R.id.viewpager);
        viewpager.setAdapter(myadapter);
        adddots(0);
        viewpager.addOnPageChangeListener(viewlistener);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(mCureentPage+1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(mCureentPage-1);
            }
        });
        return view;
    }

    public void adddots(int i){

        mdots=new TextView[3];
        liner.removeAllViews();

        for (int x=0;x<mdots.length;x++){
            mdots[x]=new TextView(getContext());
            mdots[x].setText(Html.fromHtml("&#8226;"));
            mdots[x].setTextSize(35);
            mdots[x].setTextColor(getResources().getColor(R.color.purple));
            liner.addView(mdots[x]);
        }
        if (mdots.length>0){
            mdots[i].setTextColor(getResources().getColor(R.color.white));
        }

    }

    ViewPager.OnPageChangeListener viewlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            adddots(position);
            mCureentPage = position;

            if (position==0){
                next.setEnabled(true);
                back.setEnabled(false);
                back.setVisibility(View.INVISIBLE);

                next.setText("NEXT");
                back.setText("");
            }
            else if(position==mdots.length-1){

                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);

                next.setText("");
                back.setText("BACK");

            }
            else {
                next.setEnabled(true);
                back.setEnabled(true );
                back.setVisibility(View.VISIBLE);

                next.setText("NEXT");
                back.setText("BACK");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
