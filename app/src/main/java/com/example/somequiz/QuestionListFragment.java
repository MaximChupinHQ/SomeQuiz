package com.example.somequiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.somequiz.database.QuestionLab;
import java.util.List;

public class QuestionListFragment extends Fragment {
    RecyclerView mQuestionRecyclerView;
    QuestionAdapter mAdapter;

    private class QuestionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mQuestionTitle;
        private TextView mQuestionId;
        private QuestionV2 mQuestion;

        //В конструкторе происходит заполнение list_item_question
        public QuestionHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_question, parent, false));
            itemView.setOnClickListener(this);
            mQuestionTitle = (TextView) itemView.findViewById(R.id.question_title_text_view);
            mQuestionId = (TextView) itemView.findViewById(R.id.question_id_text_view);
        }

        public void bind(QuestionV2 question){
            mQuestion = question;
            mQuestionTitle.setText(mQuestion.getQuestionText());
            mQuestionId.setText(mQuestion.getId().toString());
        }

        @Override
        public void onClick(View v) {
            Intent intent = QuestionPagerActivity.newIntent(getActivity(),mQuestion.getId());
            startActivity(intent);
        }

    }

    private class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder>{

        private List<QuestionV2> mQuestions;

        public QuestionAdapter(List<QuestionV2> questions){
            mQuestions = questions;
        }

        @Override
        //Метод вызывается RecyclerView, когда необходимо создать новое представление дл отображенияэлемента
        public QuestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new QuestionHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(QuestionHolder holder, int position) {
            QuestionV2 question = mQuestions.get(position);
            holder.bind(question);
        }

        @Override
        public int getItemCount() {
            return mQuestions.size();
        }

        public void setQuestions(List<QuestionV2> questions){
            mQuestions = questions;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);
        mQuestionRecyclerView = view.findViewById(R.id.question_recycler_view);
        //Назначаем LayoutManager для размещения элементов на экране, так как RecyclerView этим не занимается.
        //LinearLayout размещает в вертикальном порядке
        mQuestionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_question_list, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_question_button:
                QuestionLab questionLab = QuestionLab.get(getActivity());
                QuestionV2 question = new QuestionV2();
                questionLab.addQuestion(question);
                Intent intent = QuestionPagerActivity.newIntent(getActivity(),question.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI(){
        QuestionLab questionLab = QuestionLab.get(getActivity());
        List<QuestionV2> questions = questionLab.getQuestions();
        if (mAdapter == null) {
            mAdapter = new QuestionAdapter(questions);
            mQuestionRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.setQuestions(questions);
            mAdapter.notifyDataSetChanged();
        }

    }
}
