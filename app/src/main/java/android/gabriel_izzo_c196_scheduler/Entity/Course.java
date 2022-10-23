package android.gabriel_izzo_c196_scheduler.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String courseName;
    private double courseScore;

    public Course(int courseID, String courseName,double courseScore){
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseScore = courseScore;

    }
    @Override
    public String toString(){
        return "Course{" +
                "courseID=" + courseID +
                ", courseName='" + courseName +
                ", couresScore" + courseScore +
                '}';
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(double courseScore) {
        this.courseScore = courseScore;
    }
}