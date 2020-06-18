package com.example.englishpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_QUIZ_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        QuizDetailFragment frag = (QuizDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        int quizId = (int) getIntent().getExtras().get(EXTRA_QUIZ_ID);
        frag.setQuizId(quizId);
    }
}
