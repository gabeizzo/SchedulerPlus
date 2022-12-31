package android.gabriel_izzo_c196_scheduler.Database;

import android.content.Context;
import android.gabriel_izzo_c196_scheduler.DAO.AssessmentDAO;
import android.gabriel_izzo_c196_scheduler.DAO.CourseDAO;
import android.gabriel_izzo_c196_scheduler.DAO.TermDAO;
import android.gabriel_izzo_c196_scheduler.Entity.Assessment;
import android.gabriel_izzo_c196_scheduler.Entity.Course;
import android.gabriel_izzo_c196_scheduler.Entity.Term;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Term.class, Course.class, Assessment.class}, version=5, exportSchema = false)
public abstract class SchedulerDatabaseBuilder extends RoomDatabase {
    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();


    private static volatile SchedulerDatabaseBuilder INSTANCE;

    static SchedulerDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SchedulerDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SchedulerDatabaseBuilder.class, "SchedulerDatabase.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
