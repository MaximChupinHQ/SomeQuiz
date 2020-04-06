package com.example.somequiz;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

import com.example.somequiz.database.QuestionLab;

import static android.widget.CompoundButton.*;

public class QuestionFragment extends Fragment {
    private static final String ARG_QUESTION_ID = "questionId";
    private QuestionV2 mQuestion;
    private EditText mTitleQuestion;
    private EditText mTitleAnswer1;
    private EditText mTitleAnswer2;
    private EditText mTitleAnswer3;
    private EditText mTitleAnswer4;
    private CheckBox mRightAnswer1;
    private CheckBox mRightAnswer2;
    private CheckBox mRightAnswer3;
    private CheckBox mRightAnswer4;

    public static QuestionFragment newInstance (Integer questionId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUESTION_ID, questionId);
        QuestionFragment questionFragment = new QuestionFragment();
        questionFragment.setArguments(args);
        return questionFragment;
    }

    private void SetAnswerEditText (EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void SetAnswerCheckBox (CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //получение переданного ID вопроса из активности
        Integer questionId = (Integer) getArguments().getSerializable(ARG_QUESTION_ID);
        mQuestion = QuestionLab.get(getActivity()).getQuestion(questionId);
    }

    public void onPause(){
        super.onPause();
        QuestionLab.get(getActivity()).updateQuestion(mQuestion);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question,container,false);
        mTitleQuestion = (EditText) v.findViewById(R.id.question_title);
        mTitleQuestion.setText(mQuestion.getQuestionText());
        mTitleQuestion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mQuestion.setQuestionText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mTitleAnswer1 = (EditText) v.findViewById(R.id.answer1_title);
        SetAnswerEditText(mTitleAnswer1);
        mTitleAnswer2 = (EditText) v.findViewById(R.id.answer2_title);
        SetAnswerEditText(mTitleAnswer1);
        mTitleAnswer3 = (EditText) v.findViewById(R.id.answer3_title);
        SetAnswerEditText(mTitleAnswer1);
        mTitleAnswer4 = (EditText) v.findViewById(R.id.answer4_title);
        SetAnswerEditText(mTitleAnswer1);

        mRightAnswer1 = v.findViewById(R.id.answer1_right_value);
        SetAnswerCheckBox(mRightAnswer1);
        mRightAnswer2 = v.findViewById(R.id.answer2_right_value);
        SetAnswerCheckBox(mRightAnswer2);
        mRightAnswer3 = v.findViewById(R.id.answer3_right_value);
        SetAnswerCheckBox(mRightAnswer3);
        mRightAnswer4 = v.findViewById(R.id.answer4_right_value);
        SetAnswerCheckBox(mRightAnswer4);

        return v;
    }

}
