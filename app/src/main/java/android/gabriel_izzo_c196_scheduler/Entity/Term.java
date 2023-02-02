package android.gabriel_izzo_c196_scheduler.Entity;

import android.gabriel_izzo_c196_scheduler.Utility.DateTypeConverter;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import java.util.Date;

@Entity(tableName="terms")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int termID;
    private final String termTitle;
    @TypeConverters(DateTypeConverter.class)
    private final Date termStart;
    @TypeConverters(DateTypeConverter.class)
    private final Date termEnd;

    public Term(int termID, String termTitle, Date termStart, Date termEnd){
        this.termID = termID;
        this.termTitle = termTitle;
        this.termStart = termStart;
        this.termEnd = termEnd;
    }
    @NonNull
    @Override
    public String toString(){
        return "Term{" +
                ", termID=" + termID +
                ", termTitle=" + termTitle +
                ", termStart=" + termStart +
                ", termEnd=" + termEnd +
                '}';
    }
    public int getTermID() {
        return termID;
    }
    public void setTermID(int termID) {
        this.termID = termID;
    }
    public String getTermTitle() {
        return termTitle;
    }
    public Date getTermStart() {
        return termStart;
    }
    public Date getTermEnd(){
        return termEnd;
    }
}