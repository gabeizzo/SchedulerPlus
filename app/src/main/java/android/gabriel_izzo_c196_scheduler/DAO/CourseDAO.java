package android.gabriel_izzo_c196_scheduler.DAO;

import android.gabriel_izzo_c196_scheduler.Entity.Course;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Course course);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("DELETE FROM courses")
    void deleteAllCourses();

    @Query("SELECT * FROM courses ORDER BY courseID ASC")
    List<Course> getAllCourses();

    @Query ("SELECT * FROM courses WHERE termID= :termID ORDER BY termID ASC")
    List<Course> getAllAssociatedCourses(int termID);

    @Query("SELECT COUNT(*) FROM courses WHERE courseID= :id")
    int isNewRecord(int id);

    @Query("SELECT COUNT(*) FROM courses")
    int getRowCount();

    @Query("SELECT * FROM courses where courseID= :courseID")
    Course getCourseByID(int courseID);
}
