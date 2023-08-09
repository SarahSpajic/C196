    package com.example.c196.Database;

    import android.content.Context;

    import androidx.room.Database;
    import androidx.room.Room;
    import androidx.room.RoomDatabase;
    import androidx.room.TypeConverters;

    import com.example.c196.DAO.CourseDAO;
    import com.example.c196.DAO.AssessmentDAO;
    import com.example.c196.DAO.TermDAO;
    import com.example.c196.Entities.Term;
    import com.example.c196.Entities.Course;
    import com.example.c196.Entities.Assessment;
    @TypeConverters(DateConverter.class)
    @Database(entities = {Term.class, Course.class, Assessment.class}, version = 3, exportSchema = false)
    public abstract class  C196DatabaseBuilder extends RoomDatabase {

        private static volatile C196DatabaseBuilder INSTANCE;


        public abstract CourseDAO courseDAO();

        public abstract AssessmentDAO assessmentDAO();
        public abstract TermDAO termDAO();

        public static C196DatabaseBuilder getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (C196DatabaseBuilder.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                        C196DatabaseBuilder.class, "c196_database")
                                .fallbackToDestructiveMigration()
                                .build();
                    }
                }
            }
            return INSTANCE;
        }

    }
