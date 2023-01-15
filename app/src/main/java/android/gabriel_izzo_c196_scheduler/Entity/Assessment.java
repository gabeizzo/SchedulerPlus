package android.gabriel_izzo_c196_scheduler.Entity;

import android.gabriel_izzo_c196_scheduler.Utility.Converter;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName="assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String assessmentType;
    private String assessmentTitle;

    @TypeConverters(Converter.class)
    private Date assessmentStartDate;

    @TypeConverters(Converter.class)
    private Date assessmentEndDate;

    private Integer courseID;

    public Assessment(Integer assessmentID, String assessmentTitle,  Date assessmentStartDate, Date assessmentEndDate, String assessmentType, Integer courseID){
        this.assessmentID = assessmentID;
        this.assessmentType = assessmentType;
        this.assessmentTitle = assessmentTitle;
        this.assessmentStartDate = assessmentStartDate;
        this.assessmentEndDate = assessmentEndDate;
        this.courseID = courseID;

    }

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

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public Date getAssessmentStartDate() {
        return assessmentStartDate;
    }

    public void setAssessmentStartDate(Date assessmentStartDate) {
        this.assessmentStartDate = assessmentStartDate;
    }
    public Date getAssessmentEndDate() {
        return assessmentEndDate;
    }

    public void setAssessmentEndDate(Date assessmentEndDate) {
        this.assessmentEndDate = assessmentEndDate;
    }
}
