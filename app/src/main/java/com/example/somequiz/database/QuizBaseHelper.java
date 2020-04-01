package com.example.somequiz.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.somequiz.database.QuizDbSchema.QuizQuestionTable;

public class QuizBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "quizBase.db";

    public QuizBaseHelper (Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + QuizQuestionTable.NAME +
                "(" +
                "_id integer primary key autoincrement, " +
                QuizQuestionTable.Cols.UUID + ", " +
                QuizQuestionTable.Cols.QuestionText +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
