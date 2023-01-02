package android.gabriel_izzo_c196_scheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.DAO.TermDAO;
import android.gabriel_izzo_c196_scheduler.DAO.TermDAO_Impl;
import android.gabriel_izzo_c196_scheduler.Database.Repository;
import android.gabriel_izzo_c196_scheduler.Entity.Course;
import android.gabriel_izzo_c196_scheduler.Entity.Term;
import android.gabriel_izzo_c196_scheduler.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class TermDetails extends AppCompatActivity {

    EditText termTitle;
    EditText termStart;
    EditText termEnd;
    int termID;
    String title;
    String start;
    String end;
    Repository repo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        termTitle =findViewById(R.id.termTitleTxt);
        termStart =findViewById(R.id.termStartTxt);
        termEnd =findViewById(R.id.termEndTxt);
        termID=getIntent().getIntExtra("id", -1);
        title =getIntent().getStringExtra("title");
        start =getIntent().getStringExtra("start");
        end =getIntent().getStringExtra("end");
        termTitle.setText(title);
        termStart.setText(start);
        termEnd.setText(end);

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
            term = new Term(newID, termTitle.getText().toString(), termStart.getText().toString(), termEnd.getText().toString());
            repo.insert(term);
        }
        else {
            term=new Term(termID, termTitle.getText().toString(), termStart.getText().toString(), termEnd.getText().toString());
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