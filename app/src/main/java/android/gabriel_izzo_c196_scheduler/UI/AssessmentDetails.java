package android.gabriel_izzo_c196_scheduler.UI;

import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Database.Repository;
import android.gabriel_izzo_c196_scheduler.Entity.Assessment;
import android.gabriel_izzo_c196_scheduler.Entity.Course;
import android.gabriel_izzo_c196_scheduler.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AssessmentDetails extends AppCompatActivity {
    int assessmentID;
    EditText assessmentTitle;
    EditText assessmentType;
    EditText assessmentDate;
    String title;
    String date;
    String type;
    Repository repo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);
        assessmentTitle = findViewById(R.id.assessmentTitleTxt);
        assessmentType = findViewById(R.id.assessmentTypeTxt);
        assessmentDate = findViewById(R.id.assessmentDateTxt);
        assessmentID = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        date = getIntent().getStringExtra("date");
        type = getIntent().getStringExtra("type");
        assessmentTitle.setText(title);
        assessmentDate.setText(date);
        assessmentType.setText(type);

        repo = new Repository(getApplication());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_assessments_list, menu);
        return true;
    }
    public void goToTerms(MenuItem item) {
        Intent intent=new Intent(AssessmentDetails.this, TermList.class);
        startActivity(intent);
    }

    public void goToCourses(MenuItem item) {
        Intent intent=new Intent(AssessmentDetails.this, CourseList.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void saveAssessment(View view) {
        Assessment assessment;
        if (assessmentID == -1) {
            int newID = repo.getAllAssessments().get(repo.getAllAssessments().size() - 1).getAssessmentID() + 1;
            assessment = new Assessment(newID, assessmentTitle.getText().toString(), assessmentType.getText().toString(),
                    assessmentDate.getText().toString());
            repo.insert(assessment);
        } else {
            assessment = new Assessment(assessmentID, assessmentTitle.getText().toString(), assessmentType.getText().toString(),
                    assessmentDate.getText().toString());
            repo.update(assessment);
        }
    }
}
