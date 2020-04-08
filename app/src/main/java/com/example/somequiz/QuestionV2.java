package com.example.somequiz;

import com.example.somequiz.database.QuestionLab;

import java.util.UUID;

public class QuestionV2 {
    private UUID mId;
    private String mQuestionText;

    public QuestionV2(){
        this(UUID.randomUUID());
    }

    public QuestionV2(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getQuestionText() {
        return mQuestionText;
    }

    public void setQuestionText(String questionText) {
        mQuestionText = questionText;
    }
}
