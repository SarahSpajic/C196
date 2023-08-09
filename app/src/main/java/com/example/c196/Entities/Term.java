package com.example.c196.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverters;

import com.example.c196.Database.DateConverter;

import java.util.Date;
import java.util.List;


@Entity(tableName = "term")
public class Term {

    @PrimaryKey(autoGenerate = true)
    private int termID;
    private String title;
    private Date startDate;
    private Date endDate;


    @Ignore
    public Term() {

    }

    @Ignore
    public Term(int termID, String title, Date startDate, Date endDate) {
        this.termID = termID;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
@Ignore
    public Term( String title, Date startDate, Date endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public Term(int termID, String title) {
        this.termID = termID;
        this.title = title;
    }

    public int getTermID() {
        return this.termID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTermID(int id) {
        this.termID = id;
    }


    public String getTitle() {
      return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return "Term ID: " + termID + "\n" +
                "Title: " + title + "\n" +
                "Start Date: " + startDate + "\n" +
                "End Date: " + endDate;
    }



}
