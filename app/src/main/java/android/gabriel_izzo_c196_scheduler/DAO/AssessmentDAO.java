package android.gabriel_izzo_c196_scheduler.DAO;

import android.gabriel_izzo_c196_scheduler.Entity.Assessment;
import android.gabriel_izzo_c196_scheduler.Entity.Course;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessment assessment);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("DELETE FROM assessments")
    void deleteAllAssessments();

    @Query("SELECT * FROM assessments ORDER BY assessmentID ASC")
    List<Assessment> getAllAssessments();

    @Query("SELECT * FROM assessments WHERE courseID= :courseID")
    List<Assessment> getAllAssocAssessments(int courseID);

    @Query("SELECT COUNT(*) FROM assessments WHERE assessmentID=:id")
    int isNewRecord(int id);

}
