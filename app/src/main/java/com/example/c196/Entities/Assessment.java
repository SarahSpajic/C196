package com.example.c196.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "assessment")
public class Assessment {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String title;

    public Assessment(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
