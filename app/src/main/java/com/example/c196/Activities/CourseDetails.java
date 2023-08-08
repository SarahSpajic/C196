package com.example.c196.Activities;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.c196.Database.Repository;
import com.example.c196.Entities.Course;
import com.example.c196.Entities.Term;
import com.example.c196.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CourseDetails extends AppCompatActivity {
    String name;
    Double price;
    int courseId;
    int termID;
    EditText editName;
    EditText editPrice;
    EditText editNote;
    TextView editDate;
    Repository repository;
    DatePickerDialog.OnDateSetListener startDate;
    final Calendar myCalendarStart = Calendar.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        repository=new Repository(getApplication());
        name = getIntent().getStringExtra("name");
        editName = findViewById(R.id.course_name);
        editName.setText(name);
        termID = getIntent().getIntExtra("termID", -1);

        ArrayList<Term> termArrayList= new ArrayList<>();
        termArrayList.addAll(repository.getAllTerms());
        ArrayList<Integer> termIdList= new ArrayList<>();
        for(Term term:termArrayList){
            termIdList.add(term.getTermID());
        }
        ArrayAdapter<Integer> productIdAdapter= new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,termIdList);
        Spinner spinner=findViewById(R.id.spinner);
        spinner.setAdapter(productIdAdapter);
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== android.R.id.home){
            this.finish();
            return true;}

        if (item.getItemId()== R.id.add_course_button){
            Course course;
            if (courseId == -1) {
                if (repository.getAllCourses().size() == 0)
                    courseId = 1;
                else
                    courseId = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseID() + 1;
                course = new Course(courseId, editName.getText().toString(), termID);
                repository.insert(course);
            } else {
                course = new Course(courseId, editName.getText().toString(), termID);
                repository.update(course);
            }
            return true;}
        if (item.getItemId()== R.id.course_name) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, editNote.getText().toString());
            sendIntent.putExtra(Intent.EXTRA_TITLE, "Message Title");
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

