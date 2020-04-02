package com.example.somequiz.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.somequiz.QuestionV2;

import static com.example.somequiz.database.QuizDbSchema.*;

public class QuestionCursor extends CursorWrapper {
    public QuestionCursor (Cursor cursor) {
        super(cursor);
    }

    public QuestionV2 getQuestion(){
        Integer id = getInt(getColumnIndex(QuizQuestionTable.Cols.ID));
        String questionText = getString(getColumnIndex(QuizQuestionTable.Cols.QuestionText));
        QuestionV2 question = new QuestionV2(id);
        question.setQuestionText(questionText);
        return question;
    }
}
