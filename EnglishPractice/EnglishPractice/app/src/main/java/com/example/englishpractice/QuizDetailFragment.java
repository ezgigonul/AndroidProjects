package com.example.englishpractice;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static androidx.core.content.ContextCompat.getSystemService;


/**
 * A simple {@link Fragment} subclass.
 */
//Parent Fragment
public class QuizDetailFragment extends Fragment implements StopwatchFragment.OnChildFragmentInteractionListener {

    private long quizId;
    String check=null;
    Database db;
    Cursor cursor;
    RadioButton rb1,rb2,rb3,rb4,uans;
    RadioGroup radio_g;
    TextView quiz,scr,your_scr,remain_time,user_scr;
    Button cnf;
    int user_score,question_id;
    String score_string,answer;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            StopwatchFragment stopwatch = new StopwatchFragment();
            if(!stopwatch.isAdded())
            {
                //Child Fragment transaction
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.add(R.id.stopwatch_container, stopwatch);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            quizId = savedInstanceState.getLong("workoutId");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            db = new Database(getActivity());
            System.out.println(question_id);
            Question question=Question.questions[question_id];

            answer=question.getAnswer();
            cnf=(Button)view.findViewById(R.id.confirm);
            radio_g=(RadioGroup)view.findViewById(R.id.answersgrp);
            rb1=(RadioButton)view.findViewById(R.id.radioButton);
            rb2=(RadioButton)view.findViewById(R.id.radioButton2);
            rb3=(RadioButton)view.findViewById(R.id.radioButton3);
            rb4=(RadioButton)view.findViewById(R.id.radioButton4);
            quiz = (TextView) view.findViewById(R.id.question);

            user_scr=(TextView)view.findViewById(R.id.user_score);
            your_scr=(TextView) view.findViewById(R.id.your_score);
            remain_time=(TextView) view.findViewById(R.id.rmn);

            cursor = db.getScore(LoginActivity.login_username);
            cursor.moveToFirst();
            user_score=cursor.getInt(0);
            score_string = String.valueOf(user_score);
            user_scr.setText(score_string);

            quiz.setText(question.getQuestion());
            Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(),"fonts/indieflower.ttf");
            your_scr.setTypeface(custom_font);
            remain_time.setTypeface(custom_font);
            quiz.setTypeface(custom_font);


            rb1.setText(question.getChooice1());
            rb2.setText(question.getChooice2());
            rb3.setText(question.getChooice3());
            rb4.setText(question.getChooice4());


            cnf.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                public void onClick(View v) {
                    if(radio_g.getCheckedRadioButtonId()==-1)
                    {
                        Toast.makeText(getContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    uans = (RadioButton)getView().findViewById(radio_g.getCheckedRadioButtonId());
                    String ansText = uans.getText().toString();
                    System.out.println(ansText);
                    System.out.println(answer);

                    if(ansText.equals(answer)) {
                        if(check.equals("notend"))
                        {
                            //Update score when the user answers correctly.
                            cursor = db.getScore(LoginActivity.login_username);
                            cursor.moveToFirst();
                            user_score=cursor.getInt(0);
                            user_score++;
                            db.updateScore(user_score,LoginActivity.login_username);
                            score_string = String.valueOf(user_score);
                            user_scr.setText(score_string);
                            Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();

                        }
                        else{

                        }
                    }
                    else {
                        Toast.makeText(getContext(), "Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_quiz_detail, container, false);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("quizId", quizId);
    }


    public void setQuizId(long id) {
        question_id= (int) id;
    }


    @Override
    public void messageFromChildToParent(String myString) {
        check=myString;
    }
}
