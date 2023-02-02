package android.gabriel_izzo_c196_scheduler.Entity;

import android.gabriel_izzo_c196_scheduler.Utility.DateTypeConverter;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import java.util.Date;

@Entity(tableName="courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private final String courseTitle;
    @TypeConverters(DateTypeConverter.class)
    private final Date courseStart;
    @TypeConverters(DateTypeConverter.class)
    private final Date courseEnd;
    private final String courseStatus;
    private final String instructorName;
    private final String instructorPhone;
    private final String instructorEmail;
    private final String courseNote;
    private int termID;

    public Course(int courseID, String courseTitle, Date courseStart, Date courseEnd, String courseStatus, String instructorName, String instructorPhone, String instructorEmail, String courseNote, int termID){
        this.courseID= courseID;
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
        this.courseNote = courseNote;
        this.termID = termID;
    }

    @NonNull
    @Override
    public String toString(){
        return "Course{" +
                "courseID=" + courseID +
                ", courseTitle=" + courseTitle +
                ", courseStart=" + courseStart +
                ", courseEnd=" + courseEnd +
                ", courseStatus=" + courseStatus +
                ", instructorName=" + instructorName +
                ", instructorPhone=" + instructorPhone +
                ", instructorEmail=" + instructorEmail +
                ", courseNote=" + courseNote +
                ", termID=" + termID +
                '}';
    }
    public int getTermID() {
        return termID;
    }
    public void setTermID(int termID) {
        this.termID = termID;
    }
    public int getCourseID() {
        return courseID;
    }
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
    public String getCourseTitle() {
        return courseTitle;
    }
    public Date getCourseStart() {
        return courseStart;
    }
    public Date getCourseEnd() {
        return courseEnd;
    }
    public String getCourseStatus() {
        return courseStatus;
    }
    public String getInstructorName() {
        return instructorName;
    }
    public String getInstructorPhone() {
        return instructorPhone;
    }
    public String getInstructorEmail() {return instructorEmail;}
    public String getCourseNote() {return courseNote;}
}