package android.gabriel_izzo_c196_scheduler.Entity;

import android.gabriel_izzo_c196_scheduler.Utility.DateTypeConverter;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import java.util.Date;

@Entity(tableName="assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private final int assessmentID;
    private final String assessmentType;
    private final String assessmentTitle;
    @TypeConverters(DateTypeConverter.class)
    private final Date assessmentStartDate;
    @TypeConverters(DateTypeConverter.class)
    private final Date assessmentEndDate;
    private Integer courseID;

    public Assessment(Integer assessmentID, String assessmentTitle,  Date assessmentStartDate, Date assessmentEndDate, String assessmentType, Integer courseID){
        this.assessmentID = assessmentID;
        this.assessmentType = assessmentType;
        this.assessmentTitle = assessmentTitle;
        this.assessmentStartDate = assessmentStartDate;
        this.assessmentEndDate = assessmentEndDate;
        this.courseID = courseID;
    }

    @NonNull
    @Override
    public String toString(){
        return "Assessment{" +
                "assessmentID=" + assessmentID +
                ", assessmentTitle=" + assessmentTitle +
                ", assessmentStartDate=" + assessmentStartDate +
                ", assessmentEndDate=" + assessmentEndDate +
                ", assessmentType=" + assessmentType +
                ", courseID=" + courseID +
                '}';
    }

    public Integer getCourseID() {
        return courseID;
    }
    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }
    public int getAssessmentID() {
        return assessmentID;
    }
    public String getAssessmentType() {
        return assessmentType;
    }
    public String getAssessmentTitle() {
        return assessmentTitle;
    }
    public Date getAssessmentStartDate() {
        return assessmentStartDate;
    }
    public Date getAssessmentEndDate() {
        return assessmentEndDate;
    }
}
