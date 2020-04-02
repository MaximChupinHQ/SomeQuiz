package com.example.somequiz;

import java.util.UUID;

public class QuestionV2 {
    private Integer mId;
    private String mQuestionText;

    public QuestionV2(Integer id) {
        mId = id;
    }

    public Integer getId() {
        return mId;
    }

    public String getQuestionText() {
        return mQuestionText;
    }

    public void setQuestionText(String questionText) {
        mQuestionText = questionText;
    }
}
