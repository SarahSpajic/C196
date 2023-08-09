package com.example.c196.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.c196.Entities.Course;
import com.example.c196.Entities.Term;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) throws ParseException {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public List<Course> getAllCourses() throws ParseException {
        List<Course> courseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM courses", null); // Assuming your Course table is named 'courses'

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("courseID")); // Example column
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title")); // Example column
                Course course = new Course(title /*, add additional parameters as per your Course class*/);
                course.setCourseID(id);
                courseList.add(course);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return courseList;
    }

    public Course getCourseById(int courseId) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM courses WHERE courseID = ?", new String[] { String.valueOf(courseId) });

        if (!cursor.moveToFirst()) {
            return null;
        }

        @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("courseID")); // Example column
        @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title")); // Example column
        Course course = new Course(title /*, add additional parameters as per your Course class*/);
        course.setCourseID(id);

        cursor.close();
        return course;
    }

    public void insertCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("course_name", course.getCourseName()); // Example column
        db.insert("courses", null, values);
        db.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE terms (" +
                "termID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "startDate INTEGER, " +
                "endDate INTEGER, " +
                "course TEXT" +
                ")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS terms");
        onCreate(db);
    }

    public List<Term> getAllTerms() throws ParseException {
        List<Term> termList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM terms", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("termID"));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") long startMillis = cursor.getLong(cursor.getColumnIndex("startDate"));
                @SuppressLint("Range") long endMillis = cursor.getLong(cursor.getColumnIndex("endDate"));
                Date startDate = new Date(startMillis);
                Date endDate = new Date(endMillis);

                Term term = new Term(title, startDate, endDate);
                term.setTermID(id);
                termList.add(term);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return termList;
    }

    public Term getTermById(int termId) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM terms WHERE termID = ?", new String[] { String.valueOf(termId) });

        if (!cursor.moveToFirst()) {
            return null;
        }

        @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("termID"));
        @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
        @SuppressLint("Range") long startMillis = cursor.getLong(cursor.getColumnIndex("startDate"));
        @SuppressLint("Range") long endMillis = cursor.getLong(cursor.getColumnIndex("endDate"));
        @SuppressLint("Range") String course = String.valueOf(cursor.getInt(cursor.getColumnIndex("courseName")));

        Date startDate = new Date(startMillis);
        Date endDate = new Date(endMillis);

        Term term = new Term(title, startDate, endDate);
        term.setTermID(id);

        cursor.close();
        return term;
    }


    public void insertTerm(Term term) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", term.getTitle());
        values.put("startDate", term.getStartDate().getTime());
        values.put("endDate", term.getEndDate().getTime());

        db.insert("terms", null, values);
        db.close();
    }



}
