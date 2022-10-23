package android.gabriel_izzo_c196_scheduler.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="terms")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int termID;
    private String termName;
    private double termScore;

    public Term(int termID, String termName, double termScore){
        this.termID = termID;
        this.termName = termName;
        this.termScore = termScore;

    }
    @Override
    public String toString(){
        return "Term{" +
                "termID=" + termID +
                ", termName='" + termName +
                ", termScore" + termScore +
                '}';
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public double getTermScore() {
        return termScore;
    }

    public void setTermScore(double termScore) {
        this.termScore = termScore;
    }
}