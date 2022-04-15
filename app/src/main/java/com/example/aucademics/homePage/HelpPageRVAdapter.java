package com.example.aucademics.homePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aucademics.R;
import com.example.aucademics.cgpaFragment.gpaItem;
import com.example.aucademics.cgpaFragment.selectGpaRVAdapter;

import java.util.ArrayList;

public class HelpPageRVAdapter extends RecyclerView.Adapter<HelpPageRVAdapter.FAQViewHolder> {
    ArrayList<faqItem> dataList;
    Context context;

    public HelpPageRVAdapter(ArrayList<faqItem> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_question_answer_vh,parent,false);
        HelpPageRVAdapter.FAQViewHolder evh = new HelpPageRVAdapter.FAQViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull FAQViewHolder holder, int position) {
        faqItem currentItem = dataList.get(position);
        holder.mQuestion.setText(currentItem.getQuestion());
        holder.mAnswer.setText(currentItem.getAnswer());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class FAQViewHolder extends RecyclerView.ViewHolder{
        TextView mQuestion;
        TextView mAnswer;
        public FAQViewHolder(@NonNull View itemView) {
            super(itemView);
            mQuestion = itemView.findViewById(R.id.faq_question_tv);
            mAnswer = itemView.findViewById(R.id.faq_answer_tv);
        }
    }

}
