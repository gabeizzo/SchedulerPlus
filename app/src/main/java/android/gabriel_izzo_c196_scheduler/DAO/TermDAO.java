package android.gabriel_izzo_c196_scheduler.DAO;

import android.gabriel_izzo_c196_scheduler.Entity.Term;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Term term);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Term term);

    @Delete
    void delete(Term term);

    @Query("DELETE FROM terms")
    void deleteAllTerms();

    @Query("SELECT * FROM terms ORDER BY termID ASC")
    List<Term> getAllTerms();

    @Query("SELECT * FROM terms WHERE termID=:termID")
    Term getTermByID(int termID);

    @Query("SELECT COUNT(*) FROM terms")
    int getRowCount();

    @Query("SELECT COUNT(*) FROM terms WHERE termID=:termID")
    int isNewRecord(int termID);

}
