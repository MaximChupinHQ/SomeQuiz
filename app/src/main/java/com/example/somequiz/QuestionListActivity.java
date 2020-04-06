package com.example.somequiz;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class QuestionListActivity extends SingleFragmentActivity {

    public static Intent newIntent (Context packageContext){
        return new Intent(packageContext, QuestionListActivity.class);
    }

    @Override
    protected Fragment createFragment() {
        return new QuestionListFragment();
    }
}
