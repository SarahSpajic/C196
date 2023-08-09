package com.example.c196.Activities;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c196.Database.Repository;
import com.example.c196.Entities.Course;
import com.example.c196.Entities.Term;
import com.example.c196.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TermDetails extends AppCompatActivity {
    String name;
    Date startDate;
    Date endDate;

    int termID;
    EditText editName;
    EditText editStartDate;
    EditText editEndDate;
    Repository repository;
    Term currentTerm;
    int numCourses;
    private List<Term> allTerms = new ArrayList<>();
    private List<Course> allCourses = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_details);
        name = getIntent().getStringExtra("name");
        long startDateLong = getIntent().getLongExtra("startDate", -1);
//        long endDateLong = getIntent().getLongExtra("endDate", -1);
//        Log.d("TermDetails", "startDateLong: " + startDateLong);
//        Log.d("TermDetails", "endDateLong: " + endDateLong);
//        if (startDateLong != -1) {
//            startDate = new Date(startDateLong);
//        }
//        if (endDateLong != -1) {
//            endDate = new Date(endDateLong);
//        }

        editName = findViewById(R.id.termTitle);
        editStartDate = findViewById(R.id.startDate);
        editEndDate = findViewById(R.id.endDate);
        editName = findViewById(R.id.termTitle);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        if (startDate != null) {
//            editStartDate.setText(sdf.format(startDate));
//        }
//        if (endDate != null) {
//            editEndDate.setText(sdf.format(endDate));
//        }
//
//        editStartDate.setText(sdf.format(startDate));
//        editEndDate.setText(sdf.format(endDate));

        name = getIntent().getStringExtra("name");
        editName.setText(name);
        termID = getIntent().getIntExtra("id", -1);
        repository = new Repository(getApplication());
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        List<Course> filteredCourses = new ArrayList<>();

        repository.getAllTerms().observe(this, terms -> {
            allTerms = terms;
        });

        repository.getAllCourses().observe(this, courses -> {
            allCourses = courses;
        });

        courseAdapter.setCourses(filteredCourses);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermDetails.this, CourseDetails.class);
                intent.putExtra("termID", termID);
                intent.putExtra("startDate", startDate.getTime()); // Assuming startDate is a Date object
                intent.putExtra("endDate", endDate.getTime()); // Assuming endDate is a Date object
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
                case R.id.addCourses: // Assuming the ID for the "Add Courses" menu item is addCourses
                Intent intent = new Intent(TermDetails.this, CourseList.class); // Assuming CourseList is the name of the activity for the course list screen
                startActivity(intent);
                return true;
            // Handle other menu items if needed
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_term_details, menu);
        return true;
    }

    private void saveTerm() {
        Term term;
        if (termID == -1) {
            if (allTerms.isEmpty()) {
                termID = 1;
            } else {
                termID = allTerms.get(allTerms.size() - 1).getTermID() + 1;
            }
            term = new Term(termID, editName.getText().toString());
            repository.insert(term);
        } else {
            term = new Term(termID, editName.getText().toString());
            repository.update(term);
        }
        Intent intent = new Intent(TermDetails.this, TermList.class);
        startActivity(intent);
    }
    public void handleTermSave(View view) {
        saveTerm();
    }


    private void handleTermDelete() {
        Term currentTerm = null;
        for (Term term : allTerms) {
            if (term.getTermID() == termID) {
                currentTerm = term;
                break;
            }
        }

        int numCourses = 0;
        for (Course course : allCourses) {
            if (course.getTermID() == termID) ++numCourses;
        }

        if (numCourses == 0 && currentTerm != null) {
            repository.delete(currentTerm);
            Toast.makeText(TermDetails.this, currentTerm.getTitle() + " was deleted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(TermDetails.this, "Can't delete a term with parts", Toast.LENGTH_LONG).show();
        }
    }

    private void handleAddCourses() {
        if (termID == -1) {
            Toast.makeText(TermDetails.this, "Please save term before adding parts", Toast.LENGTH_LONG).show();
            return;
        }

        int courseID;
        if (allCourses.isEmpty()) {
            courseID = 1;
        } else {
            courseID = allCourses.get(allCourses.size() - 1).getCourseID() + 1;
        }

        Course course1 = new Course(courseID, "wheel", termID);
        repository.insert(course1);
        Course course2 = new Course(++courseID, "pedal", termID);
        repository.insert(course2);

        final CourseAdapter courseAdapter = new CourseAdapter(this);

        List<Course> filteredCourses = new ArrayList<>();
        for (Course c : allCourses) {
            if (c.getTermID() == termID) {
                filteredCourses.add(c);
            }
        }
        courseAdapter.setCourses(filteredCourses);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        List<Course> filteredCourses = new ArrayList<>();
        courseAdapter.setCourses(filteredCourses);
    }
}
