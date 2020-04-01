package com.example.somequiz;

import java.util.UUID;

public class QuestionV2 {
    private UUID mId;
    private String mQuestionText;

    public QuestionV2(){
        mId = UUID.randomUUID();
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
