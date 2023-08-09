package com.example.c196.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import com.example.c196.Database.Repository;
import com.example.c196.Entities.Term;
import com.example.c196.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

public class TermList extends AppCompatActivity {
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        FloatingActionButton fab = findViewById(R.id.fab);
        repository = new Repository(getApplication());
        LiveData<List<Term>> allTerms = repository.getAllTerms();
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        final TermAdapter termAdapter = new TermAdapter(this);
        Log.d("Where are my terms?", allTerms.toString());
        allTerms.observe(this, new Observer<List<Term>>() {
            @Override
            public void onChanged(List<Term> terms) {
                Log.d("onChanged", "Data received: " + terms);
                if (terms != null && !terms.isEmpty()) {
                    termAdapter.setTerms(terms);
                    // Hide loading spinner if visible
                } else {
                    // Show loading spinner or a message indicating that data is being loaded
                }
            }

        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermList.this, TermDetails.class);
                startActivity(intent);
            }
        });


        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_term_list, menu);
        return true;
    }
//TODO: create a menu on the terms list screen //
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            this.finish();
//            Intent intent = new Intent(TermList.this, com.example.c196.Activities.MainActivity.class);
//            startActivity(intent);
//            return true;
//        }
//
//        if (item.getItemId() == R.id.termSave) {
//            Repository repo = new Repository(getApplication());
//            Term term = new Term(1, "bicycle");
//            repo.insert(term);
//            term = new Term(2, "tricycle");
//            repo.insert(term);
//            List<Term> allTerms = repository.getAllTerms().getValue();
//            RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
//            final TermAdapter termAdapter = new TermAdapter(this);
//            recyclerView.setAdapter(termAdapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            termAdapter.setTerms(allTerms);
//
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        List<Term> allTerms = repository.getAllTerms().getValue();
//        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
//        final TermAdapter termAdapter = new TermAdapter(this);
//        recyclerView.setAdapter(termAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        termAdapter.setTerms(allTerms);
//    }
}
