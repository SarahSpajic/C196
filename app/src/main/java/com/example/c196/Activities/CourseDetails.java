package com.example.c196.Activities;

import static com.example.c196.R.id.coursesave;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.c196.Database.Repository;
import com.example.c196.Entities.Course;
import com.example.c196.Entities.Term;
import com.example.c196.R;

import java.util.ArrayList;
import java.util.List;


public class CourseDetails extends AppCompatActivity {
    String courseName;
    Double price;
    int courseId;
    int termID;
    EditText editCourseName;
    Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail);
        repository = new Repository(getApplication());
        courseName = getIntent().getStringExtra("name");
        editCourseName = findViewById(R.id.courseName);
        editCourseName.setText(courseName);

        ArrayList<Term> termArrayList = new ArrayList<>();
        LiveData<List<Term>> termsFromRepo = repository.getAllTerms();
        termsFromRepo.observe(this, new Observer<List<Term>>() {
            @Override
            public void onChanged(List<Term> terms) {
                termArrayList.addAll(terms);
            }
        });


        ArrayList<Integer> termIdList = new ArrayList<>();
        for (Term term : termArrayList) {
            termIdList.add(term.getTermID());
        }
        ArrayAdapter<Integer> termAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, termIdList);
        Spinner spinner = findViewById(R.id.statusSpinner);
        spinner.setAdapter(termAdapter);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_details, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
//
//            case R.id.coursesave:
//                handleCourseSave();
//                return true;
//
//            case R.id.share:
//                handleShareAction();
//                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void handleCourseSave() {
        repository.getAllCourses().observe(this, courses -> {
            int lastCourseId = (courses.size() > 0) ? courses.get(courses.size() - 1).getCourseID() : 0;

            Course course;
            if (courseId == -1) {
                courseId = lastCourseId + 1;
                course = new Course(courseId, editCourseName.getText().toString(), termID);
                repository.insert(course);
            } else {
                course = new Course(courseId, editCourseName.getText().toString(), termID);
                repository.update(course);
            }
        });
    }


    private void handleShareAction() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TITLE, "Message Title");
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

}
