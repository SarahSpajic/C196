package com.example.c196.model;

import java.util.Date;

public class Assessment {
    private String title;
    private Date endDate;

    public Assessment(String title, Date endDate) {
        this.title = title;
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // getter and setter methods...
}
