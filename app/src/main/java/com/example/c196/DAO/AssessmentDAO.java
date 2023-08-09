package com.example.c196.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196.Entities.Assessment;
import com.example.c196.Entities.Term;

import java.util.List;

@Dao
public interface AssessmentDAO {

    @Update
    void update(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("SELECT * FROM assessment")
    LiveData<List<Assessment>> getAllAssessments();
}
