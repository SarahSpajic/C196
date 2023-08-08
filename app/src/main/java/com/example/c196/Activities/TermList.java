package com.example.c196.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.c196.DAO.DatabaseHelper;
import com.example.c196.DAO.TermDAO;
import com.example.c196.Database.C196DatabaseBuilder;
import com.example.c196.Entities.Term;
import com.example.c196.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.List;

public class TermListActivity extends AppCompatActivity {
    private RecyclerView termRecyclerView;
    private TermAdapter termAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);

        termRecyclerView = findViewById(R.id.termRecyclerView);
        termRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter = new TermAdapter(this, null);
        termRecyclerView.setAdapter(termAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermListActivity.this, TermDetails.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                    final List<Term> terms = db.getAllTerms();

                    if (terms == null || terms.isEmpty()) {
                        Log.d("TermListActivity", "Terms list is empty or null");
                    } else {
                        Log.d("TermListActivity", "Number of terms: " + terms.size());
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            termAdapter.setTerms(terms);
                        }
                    });
                } catch (ParseException e) {
                    Log.e("TermListActivity", "Error while fetching terms", e);
                }
            }
        }).start();
    }

}
