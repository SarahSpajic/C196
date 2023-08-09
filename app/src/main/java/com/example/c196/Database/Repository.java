package com.example.c196.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.c196.DAO.CourseDAO;
import com.example.c196.DAO.AssessmentDAO;
import com.example.c196.DAO.TermDAO;
import com.example.c196.Entities.Term;
import com.example.c196.Entities.Course;
import com.example.c196.Entities.Assessment;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private TermDAO mTermDAO;
    private CourseDAO mCourseDAO;
    private AssessmentDAO mAssessmentDAO;
    private LiveData<List<Term>> mAllTerms;
    private LiveData<List<Assessment>> mAllAssessments;
    private LiveData<List<Course>> mAllCourses;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        C196DatabaseBuilder db = C196DatabaseBuilder.getDatabase(application);
        mTermDAO = db.termDAO();
        mCourseDAO = db.courseDAO();
        mAssessmentDAO = db.assessmentDAO();

        mAllTerms = mTermDAO.getAllTerms();
        mAllCourses = mCourseDAO.getAllCourses();
        mAllAssessments = mAssessmentDAO.getAllAssessments();
    }

    // Term Methods
    public void insert(Term term) {
        databaseExecutor.execute(() -> mTermDAO.insert(term));
    }

    public void update(Term term) {
        databaseExecutor.execute(() -> mTermDAO.update(term));
    }

    public void delete(Term term) {
        databaseExecutor.execute(() -> mTermDAO.delete(term));
    }

    public LiveData<Term> getTermById(int termId) {
        return mTermDAO.getTermById(termId);
    }

    // Course Methods
    public void insert(Course course) {
        databaseExecutor.execute(() -> mCourseDAO.insert(course));
    }

    public void update(Course course) {
        databaseExecutor.execute(() -> mCourseDAO.update(course));
    }

    public void delete(Course course) {
        databaseExecutor.execute(() -> mCourseDAO.delete(course));
    }

    public LiveData<List<Course>> getCoursesForTerm(int termId) {
        return mCourseDAO.getCoursesForTerm(termId);
    }

    public LiveData<List<Course>> getAllCourses() {
        return mAllCourses;
    }

    public LiveData<List<Term>> getAllTerms() {
        return mAllTerms;
    }

    public LiveData<List<Assessment>> getAllAssessments() {
        return mAllAssessments;
    }
}
