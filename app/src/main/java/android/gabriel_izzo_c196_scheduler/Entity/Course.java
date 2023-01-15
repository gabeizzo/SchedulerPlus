package android.gabriel_izzo_c196_scheduler.Entity;

import android.gabriel_izzo_c196_scheduler.Utility.Converter;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName="courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String courseTitle;

    @TypeConverters(Converter.class)
    private Date courseStart;

    @TypeConverters(Converter.class)
    private Date courseEnd;

    private String courseStatus;
    private String instructorName;
    private String instructorPhone;
    private String instructorEmail;
    private String courseNote;
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

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Date getCourseStart() {
        return courseStart;
    }
    public void setCourseStart(Date courseStart) {
        this.courseStart = courseStart;
    }

    public Date getCourseEnd() {
        return courseEnd;
    }
    public void setCourseEnd(Date courseEnd) {
        this.courseEnd = courseEnd;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }
}