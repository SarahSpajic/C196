package com.example.c196;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Activities.TermAdapter;
import com.example.c196.R;
import com.example.c196.DAO.DatabaseHelper;
import com.example.c196.Activities.TermListActivity;
import com.example.c196.Entities.Term;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Button btnEnter;  // Define the button

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the button
        btnEnter = findViewById(R.id.btn_enter);

        // Set OnClickListener to start TermListActivity
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TermListActivity.class);
                startActivity(intent);
            }
        });
    }
}

