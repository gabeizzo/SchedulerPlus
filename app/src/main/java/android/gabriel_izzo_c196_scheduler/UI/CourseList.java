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

    EditText editTitle;
    EditText editStartDate;
    EditText editEndDate;
    int termID;
    String title;
    String start;
    String end;
    Repository repo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        editTitle =findViewById(R.id.editTermTitle);
        editStartDate =findViewById(R.id.editTermStart);
        editEndDate =findViewById(R.id.editTermEnd);
        termID=getIntent().getIntExtra("id", -1);
        title =getIntent().getStringExtra("title");
        start =getIntent().getStringExtra("start");
        end =getIntent().getStringExtra("end");
        editTitle.setText(title);
        editStartDate.setText(start);
        editEndDate.setText(end);

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
            term = new Term(newID, editTitle.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
            repo.insert(term);
        }
        else {
            term=new Term(termID, editTitle.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
            repo.update(term);
        }

    }
}