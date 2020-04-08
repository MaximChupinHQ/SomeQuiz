package com.example.somequiz.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.somequiz.QuestionV2;

import java.util.UUID;

import static com.example.somequiz.database.QuizDbSchema.*;

public class QuestionCursor extends CursorWrapper {
    public QuestionCursor (Cursor cursor) {
        super(cursor);
    }

    public QuestionV2 getQuestion(){
        String uuidString = getString(getColumnIndex(QuizQuestionTable.Cols.UUID));
        String questionText = getString(getColumnIndex(QuizQuestionTable.Cols.QuestionText));
        QuestionV2 question = new QuestionV2(UUID.fromString(uuidString));
        question.setQuestionText(questionText);
        return question;
    }
}
