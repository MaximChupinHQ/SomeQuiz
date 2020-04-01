package com.example.somequiz.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.somequiz.database.QuizDbSchema.QuizQuestionTable;
import com.example.somequiz.database.QuizDbSchema.QuizAnswersTable;

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
                QuizQuestionTable.Cols.ID + " integer primary key autoincrement, " +
                QuizQuestionTable.Cols.QuestionText + " text" +
                ")"
        );

        db.execSQL("create table " + QuizAnswersTable.NAME +
                "(" +
                QuizAnswersTable.Cols.ID + " integer primary key autoincrement, " +
                QuizAnswersTable.Cols.AnswerText + " text, " +
                QuizAnswersTable.Cols.IsRight + " integer, " +
                QuizAnswersTable.Cols.QuestionId + " integer, " +
                "CHECK( " + QuizAnswersTable.Cols.IsRight + " in (0,1)" + " ), " +
                "FOREIGN KEY (" + QuizAnswersTable.Cols.QuestionId + ") REFERENCES " + QuizQuestionTable.NAME + " (" + QuizQuestionTable.Cols.ID + ")" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
