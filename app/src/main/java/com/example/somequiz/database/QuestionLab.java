package com.example.somequiz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.somequiz.Answer;
import com.example.somequiz.QuestionV2;
import com.example.somequiz.database.QuizDbSchema.QuizQuestionTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionLab {
    private static QuestionLab sQuestionLab;
    private static final String TAG = "QuestionLab";
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
        System.out.println("QuestionContextValues.QuestionText = " + question.getQuestionText());
        return values;
    }

    public static QuestionLab get(Context context) {
        sQuestionLab = new QuestionLab(context);
        return sQuestionLab;
    }
    public void addQuestion (QuestionV2 question) {
        Log.i(TAG, "addQuestion called");
        ContentValues values = getQuestionContentValues(question);
        mDatabase.insert(QuizQuestionTable.NAME, null, values);
    }

    public void updateQuestion (QuestionV2 question) {
        String id = question.getId().toString();
        ContentValues values = getQuestionContentValues(question);
        System.out.println(values);
        mDatabase.update(QuizQuestionTable.NAME, values,
                QuizQuestionTable.Cols.ID + " = ?",
                new String[] {id});
    }

    public Integer maxQuestionId(){
        String sqlQuery = "select max(" + QuizQuestionTable.Cols.ID + ") as max_id from " + QuizQuestionTable.NAME;
        Cursor cursor = mDatabase.rawQuery(sqlQuery,null);
        cursor.moveToFirst();
        Integer maxId = cursor.getInt(cursor.getColumnIndex("max_id"));
        return maxId;
    }

    public void addQAnswer (Answer answer) {

    }

    private QuestionCursor queryQuestions (String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                QuizQuestionTable.NAME,
                null, //columns с null выдает все (*)
                whereClause,
                whereArgs,
                null, //group by
                null,//having
                null //order by
        );
        return new QuestionCursor(cursor);
    }

    public List<QuestionV2> getQuestions(){
        List<QuestionV2> questions = new ArrayList<>();
        QuestionCursor cursor = queryQuestions(null,null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                questions.add(cursor.getQuestion());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return questions;
    }

    public QuestionV2 getQuestion(Integer id){
        QuestionCursor cursor = queryQuestions(QuizQuestionTable.Cols.ID + "= ?",
                new String[]{ id.toString() });

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getQuestion();
        } finally {
            cursor.close();
        }
    }

}
