package android.gabriel_izzo_c196_scheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Database.Repository;
import android.gabriel_izzo_c196_scheduler.Entity.Assessment;
import android.gabriel_izzo_c196_scheduler.Entity.Term;
import android.gabriel_izzo_c196_scheduler.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class AssessmentList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_assessment_list);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    RecyclerView assessmentListRecyclerView = findViewById(R.id.al_recyclerView);
    Repository repo = new Repository(getApplication());
    List<Assessment> assessments = repo.getAllAssessments();
    final AssessmentAdapter adapter = new AssessmentAdapter(this);
        assessmentListRecyclerView.setAdapter(adapter);
        assessmentListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(assessments);
}

    public void onResume() {
        super.onResume();
        setContentView(R.layout.activity_assessment_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView assessmentListRecyclerView = findViewById(R.id.al_recyclerView);
        Repository repo = new Repository(getApplication());
        List<Assessment> assessments = repo.getAllAssessments();
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        assessmentListRecyclerView.setAdapter(adapter);
        assessmentListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(assessments);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_assessments_list, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void goToTerms(View view) {
        Intent intent=new Intent(AssessmentList.this, TermList.class);
        startActivity(intent);
    }
    public void goToAssessmentDetails(View view) {
        Intent intent=new Intent(AssessmentList.this, AssessmentDetails.class);
        startActivity(intent);
    }

    public void goToCourses(MenuItem item) {
        Intent intent=new Intent(AssessmentList.this, CourseList.class);
        startActivity(intent);
    }
}