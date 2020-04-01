package com.example.somequiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.somequiz.database.QuizBaseHelper;

public class QuestionLab {
    private static QuestionLab sQuestionLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private QuestionLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new QuizBaseHelper(mContext).getWritableDatabase();
    }

    public static QuestionLab get(Context context) {
        sQuestionLab = new QuestionLab(context);
        return sQuestionLab;
    }
}
