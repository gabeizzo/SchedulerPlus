package android.gabriel_izzo_c196_scheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Database.Repository;
import android.gabriel_izzo_c196_scheduler.Entity.Term;
import android.gabriel_izzo_c196_scheduler.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CourseList extends AppCompatActivity {

    EditText editName;
    EditText editScore;
    int termID;
    String name;
    Double score;
    Repository repo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        editName =findViewById(R.id.editTermName);
        editScore=findViewById(R.id.editTermScore);
        termID=getIntent().getIntExtra("id", -1);
        name=getIntent().getStringExtra("name");
        score=getIntent().getDoubleExtra("score", -1.0);
        editName.setText(name);
        editScore.setText(Double.toString(score));
        repo=new Repository(getApplication());

    }

    public void goToAssessments(View view) {
        Intent intent=new Intent(CourseList.this, AssessmentList.class);
        startActivity(intent);
    }

    public void saveTerm(View view) {
        Term term;
        if (termID == -1){
            int newID = repo.getAllTerms().get(repo.getAllTerms().size()-1).getTermID()+1;
            term = new Term(newID, editName.getText().toString(), Double.parseDouble(editScore.getText().toString()));
            repo.insert(term);
        }
        else {
            term=new Term(termID, editName.getText().toString(), Double.parseDouble(editScore.getText().toString()));
            repo.update(term);
        }

    }
}