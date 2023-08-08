package com.example.c196.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.R;
import com.example.c196.Entities.Term;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {
    private List<Term> termList; // List of terms
    private Context context;

    public TermAdapter(Context context, List<Term> termList) {
        this.context = context;
        this.termList = termList;
    }

    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.term_item, parent, false);
        return new TermViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {

    }

//    @Override
//    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
//        Term term = termList.get(position);
//        holder.termTitle.setTitle(term.getTitle());
//        String termDates = term.getStartDate() + " - " + term.getEndDate();
//        holder.termDates.setText(termDates);
//    }

    @Override
    public int getItemCount() {
        return termList.size();
    }

    public static class TermViewHolder extends RecyclerView.ViewHolder {
        TextView termTitle, termDates;

        public TermViewHolder(@NonNull View itemView) {
            super(itemView);
//            termTitle = itemView.findViewById(R.id.termTitle);
//            termDates = itemView.findViewById(R.id.termDates);
        }
    }
}
