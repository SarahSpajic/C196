package com.example.c196.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termTitle;
        private TermViewHolder(View itemView) {
            super(itemView);
            termTitle = itemView.findViewById(R.id.termTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Term current = termsList.get(position);
                    Intent intent = new Intent(context, TermDetails.class);
                    intent.putExtra("id", current.getTermID());
                    intent.putExtra("title", current.getTitle());
                        intent.putExtra("startDate", current.getStartDate());
                        intent.putExtra("endDate", current.getEndDate());
                    context.startActivity(intent);
                }
            });
        }

    }

    private List<Term> termsList;
        private final Context context;
        private final LayoutInflater mInflater;

        public TermAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
        }


    @NonNull
    @Override

    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.term_item, parent, false);
        return new TermViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        if (termsList != null) {
            Term current = termsList.get(position);
            String termDates = (current.getStartDate() != null ? current.getStartDate().toString() : "null") + " - " + (current.getEndDate() != null ? current.getEndDate().toString() : "null");
            holder.termTitle.setText(current.getTitle() + "\n" + termDates);
        } else {
            holder.termTitle.setText("No Term Title");
        }
    }


    public void setTerms(List<Term> terms) {
        this.termsList = terms;
        notifyDataSetChanged();
    }



    @Override
        public int getItemCount() {
            if (termsList != null)
                return termsList.size();
            else
                return 0;
        }
    }


