package com.example.somequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private static final String EXTRA_COUNT_OF_QUESTIONS = "com.example.somequiz.countOfQuestions";
    private static final String EXTRA_COUNT_OF_TRUE_ANSWERS = "com.example.somequiz.countOfTrueAnswers";

    public static Intent newIntent(Context packageContext, Integer countOfQuestions, Integer countOfTrueAnswers){
        Intent intent = new Intent(packageContext, ResultActivity.class);
        intent.putExtra(EXTRA_COUNT_OF_QUESTIONS, countOfQuestions);
        intent.putExtra(EXTRA_COUNT_OF_TRUE_ANSWERS, countOfTrueAnswers);
        return intent;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView resultTextView = (TextView) findViewById(R.id.result_text_view);
        int countOfQuestions = getIntent().getIntExtra(EXTRA_COUNT_OF_QUESTIONS, 0);
        int countOfTrueAnswers = getIntent().getIntExtra(EXTRA_COUNT_OF_TRUE_ANSWERS, 0);
        resultTextView.setText(countOfTrueAnswers + " из " + countOfQuestions + " правильных ответов");
    }
}
