package com.example.somequiz;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    private Button mNewGameButton;
    private Button mQuestionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mNewGameButton = findViewById(R.id.new_game_button);
        mQuestionList = findViewById(R.id.list_of_questions);
        mNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = QuizActivity.newIntent(getApplicationContext(),0);
                startActivity(intent);
            }
        });
        mQuestionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = QuestionListActivity.newIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }
}
