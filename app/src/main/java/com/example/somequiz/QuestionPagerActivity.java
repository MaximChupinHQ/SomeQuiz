package com.example.somequiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.somequiz.database.QuestionLab;

import java.util.List;

public class QuestionPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<QuestionV2> mQuestions;
    private static final String EXTRA_QUESTION_ID = "questionId";

    public static Intent newIntent(Context packageContext, Integer questionId){
        Intent intent = new Intent(packageContext, QuestionPagerActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_pager);
        Integer questionId = (Integer) getIntent().getSerializableExtra(EXTRA_QUESTION_ID);
        mViewPager = findViewById(R.id.question_view_pager);
        mQuestions = QuestionLab.get(getApplicationContext()).getQuestions();
        FragmentManager fm = getSupportFragmentManager();
        //FragmentStatePagerAdapter - это агент, управляющий взаимодействием с ViewPager.
        //он добавляет возвращаемые фрагменты в активность и помогает ViewPager идентифицировать представления фрагментов для их правильного размещения.
        //FragmentStatePagerAdapter нуждается в экземпляре FragmentManager, чтобы он был способен добавлять фрагменты в активность
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            //getItem получает экземпляр QuestionV2 для заданной позиции в наборе данных,
            // после чего использует его идентификатор для создания и возвращения правильно настроенного экземпляра QuestionFragment.
            public Fragment getItem(int position) {
                QuestionV2 question = mQuestions.get(position);
                return QuestionFragment.newInstance(question.getId());
            }

            @Override
            public int getCount() {
                return mQuestions.size();
            }
        });

        for (int i = 0; i < mQuestions.size(); i++) {
            if (mQuestions.get(i).getId().equals(questionId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
