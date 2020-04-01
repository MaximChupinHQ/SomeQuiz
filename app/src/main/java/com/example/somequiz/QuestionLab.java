package com.example.somequiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.somequiz.database.QuizBaseHelper;
import com.example.somequiz.database.QuizDbSchema;
import com.example.somequiz.database.QuizDbSchema.QuizQuestionTable;

import java.util.UUID;

public class QuestionLab {
    private static QuestionLab sQuestionLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private QuestionLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new QuizBaseHelper(mContext).getWritableDatabase();
    }

    private static ContentValues getQuestionContentValues (QuestionV2 question){
        ContentValues values = new ContentValues();
        values.put(QuizQuestionTable.Cols.ID, question.getId().toString());
        values.put(QuizQuestionTable.Cols.QuestionText, question.getQuestionText());
        return values;
    }

    public static QuestionLab get(Context context) {
        sQuestionLab = new QuestionLab(context);
        return sQuestionLab;
    }
    public void addQuestion (QuestionV2 question) {
        ContentValues values = getQuestionContentValues(question);
        mDatabase.insert(QuizQuestionTable.NAME, null, values);
    }

    public void updateQuestion (QuestionV2 question) {
        String id = question.getId().toString();
        ContentValues values = getQuestionContentValues(question);

        mDatabase.update(QuizQuestionTable.NAME, values,
                QuizQuestionTable.Cols.ID + " = ?",
                new String[] {id});
    }

    public void addQAnswer (Answer answer) {

    }

    public QuestionV2 getQuestion(UUID id){
        return null;
    }

}
