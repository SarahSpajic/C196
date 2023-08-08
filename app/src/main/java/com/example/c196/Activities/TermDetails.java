package com.example.c196.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.c196.DAO.CourseDAO;
import com.example.c196.DAO.TermDAO;
import com.example.c196.Database.C196DatabaseBuilder;
import com.example.c196.Entities.Course;
import com.example.c196.Entities.Term;
import com.example.c196.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EditTermActivity extends AppCompatActivity {
    EditText titleInput;
    EditText startDateInput;
    EditText endDateInput;
    EditText courseName;
    Button saveButton;
    private TermAdapter termAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        termAdapter = new TermAdapter(this, new ArrayList<>());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);
        Intent intent = getIntent();
        titleInput = findViewById(R.id.title_input);
        startDateInput = findViewById(R.id.start_date_input);
        endDateInput = findViewById(R.id.end_date_input);

        if(intent.getExtras() != null) {
            String termTitle = intent.getStringExtra("term_title");
            titleInput.setText(termTitle);
            String courseNameValue = intent.getStringExtra("course_name");
            courseName.setText(courseNameValue); // set the course name
            long startDateMillis = intent.getLongExtra("term_start_date", -1);
            Date startDate = new Date(startDateMillis);
            startDateInput.setText(dateToString(startDate));

            long endDateMillis = intent.getLongExtra("term_end_date", -1);
            Date endDate = new Date(endDateMillis);
            endDateInput.setText(dateToString(endDate));
        }

        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            Date start_date = stringToDate(startDateInput.getText().toString());
            Date end_date = stringToDate(endDateInput.getText().toString());
            Term newTerm = new Term(title, start_date, end_date);

            new Thread(() -> {
                C196DatabaseBuilder db = C196DatabaseBuilder.getDatabase(getApplicationContext());
                TermDAO termDAO = db.termDAO();
                long newTermId = termDAO.insert(newTerm);

                finish();
            }).start();
        });

    }

    private Date stringToDate(String dateInput) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        try {
            return format.parse(dateInput);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        return format.format(date);
    }


}
