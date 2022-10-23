package android.gabriel_izzo_c196_scheduler.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String assessmentName;
    private double assessmentScore;

    public Assessment(Integer assessmentID, String assessmentName, double assessmentScore){
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.assessmentScore = assessmentScore;

    }

    @Override
    public String toString(){
        return "Assessment{" +
                "assessmentID=" + assessmentID +
                ", assessmentName='" + assessmentName +
                ", assessmentScore" + assessmentScore +
                '}';
    }

    public int getAssessmentID() {return assessmentID; }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public double getAssessmentScore() {
        return assessmentScore;
    }

    public void setAssessmentScore(double assessmentScore) {
        this.assessmentScore = assessmentScore;
    }
}
