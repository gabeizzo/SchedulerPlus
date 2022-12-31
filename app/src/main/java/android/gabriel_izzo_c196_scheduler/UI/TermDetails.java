package android.gabriel_izzo_c196_scheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Database.Repository;
import android.gabriel_izzo_c196_scheduler.Entity.Term;
import android.gabriel_izzo_c196_scheduler.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class TermDetails extends AppCompatActivity {

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
        setContentView(R.layout.activity_term_details);
        editTitle =findViewById(R.id.termNameTxt);
        editStartDate =findViewById(R.id.termStartTxt);
        editEndDate =findViewById(R.id.termEndTxt);
        termID=getIntent().getIntExtra("id", -1);
        title =getIntent().getStringExtra("title");
        start =getIntent().getStringExtra("start");
        end =getIntent().getStringExtra("end");
        editTitle.setText(title);
        editStartDate.setText(start);
        editEndDate.setText(end);

        repo=new Repository(getApplication());

    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_term_details, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    public void deleteTerm(MenuItem item) {
    }

    public void goToAssessments(MenuItem item) {
        Intent intent=new Intent(TermDetails.this, AssessmentList.class);
        startActivity(intent);
    }

    public void goToCourses(MenuItem item) {
        Intent intent=new Intent(TermDetails.this, CourseList.class);
        startActivity(intent);
    }
}