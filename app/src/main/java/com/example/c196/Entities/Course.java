package com.example.c196.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "course")
public class Course {


    private String courseName;
    @PrimaryKey(autoGenerate = true)
    private int courseID;

    private int termID;

    @Ignore
    public Course(String courseName) {
        this.courseName = courseName;
    }


    public Course(int courseID, String courseName, int termID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.termID = termID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermId(int termId) {
        this.termID = termId;
    }

}
