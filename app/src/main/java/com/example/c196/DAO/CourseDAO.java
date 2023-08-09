package com.example.c196.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196.Entities.Course;
import com.example.c196.Entities.Term;

import java.util.List;

@Dao
public interface CourseDAO {
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM course")
    LiveData<List<Course>> getAllCourses();

    @Query("SELECT * FROM course WHERE termId = :termId")
    LiveData<List<Course>> getCoursesForTerm(int termId);
}
