package com.example.somequiz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.somequiz.database.QuestionLab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_RESULT = 0;
    private static final String EXTRA_QUESTION_INDEX = "com.example.somequiz.questionIndex";
    private ArrayList<Integer> indexList = new ArrayList<>();
    private HashMap<Integer, Boolean> questionsAnswers = new HashMap<>();
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_africa, false),
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    private int mCurrentIndex;

    public static Intent newIntent (Context packageContext, Integer questionIndex){
        Intent intent = new Intent(packageContext, QuizActivity.class);
        intent.putExtra(EXTRA_QUESTION_INDEX,questionIndex);
        return intent;
    }
    @SuppressLint("SetTextI18n")
    private void endGame(){
        if (questionsAnswers.size() == mQuestionBank.length) {
            int i = 0;
            Intent intent;
            /*mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
            mNextButton.setEnabled(false);
            mPrevButton.setEnabled(false);*/

            for (Map.Entry pair: questionsAnswers.entrySet()) {
                if ((boolean) pair.getValue()) i++;
            }

            intent = ResultActivity.newIntent(getApplicationContext(),mQuestionBank.length, i);
            //startActivity(intent);
            startActivityForResult(intent, REQUEST_CODE_RESULT);
            //mQuestionTextView.setText(String.valueOf(i)+" из "+String.valueOf(mQuestionBank.length)+" правильных ответов");
        }
    }

    private void updateQuestion(){
        Log.i(TAG, "Updating question text");
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void calcIndex(ImageButton button){
        if (button == mNextButton) {
            indexList.add(mCurrentIndex);
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        }else if (button == mPrevButton){
            if (indexList.isEmpty()) {
                mCurrentIndex = 0;
            }else{
                mCurrentIndex = indexList.get(indexList.size()-1);
                indexList.remove(indexList.size()-1);
            }
        }
    }

    private void checkAnswer (boolean userPressedTrue){
        Toast mToast;
        int messageResId;
        if (questionsAnswers.containsKey(mCurrentIndex)){
            messageResId = R.string.not_again;
        }else {
            boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
                questionsAnswers.put(mCurrentIndex, true);
            } else {
                messageResId = R.string.incorrect_toast;
                questionsAnswers.put(mCurrentIndex, false);
            }
        }
        mToast = Toast.makeText(getApplicationContext(), messageResId, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.BOTTOM,0 ,0 );
        mToast.show();
        Log.i(TAG,questionsAnswers.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        // R - это класс, с помощью которого можно достать идентификаторы всех ресурсов из каталога
        // res/
        QuestionLab.get(getApplicationContext());
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }
        mCurrentIndex = getIntent().getIntExtra(EXTRA_QUESTION_INDEX,0);
        //(Button) - преобразование к типу Button
        //findViewById - метод, с помощью которого получаем ссылку на виджет
        mTrueButton  = findViewById(R.id.true_button);
        //View.OnClickListener() - готовый интерфейс для реализации слушателя на клик
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                endGame();

            }
        });
        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                endGame();
            }
        });
        //Отрисуем на виджете TextView в файле activity_main с id = question_text_view вопрос
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();
        /*mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcIndex(mNextButton);
                updateQuestion();
            }
        });*/
        //Настройка кнопки Next
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcIndex(mNextButton);
                updateQuestion();
            }
        });
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcIndex(mPrevButton);
                updateQuestion();
            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
    @Override
    protected void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG,"onActivityResult(called)");
        if (resultCode == Activity.RESULT_CANCELED) {
            Log.i(TAG,"pressed Back Button");
            Intent intent = new Intent(getApplicationContext(),FirstActivity.class);
            questionsAnswers.clear();
            indexList.clear();
            startActivity(intent);

        }
    }
}
