package android.gabriel_izzo_c196_scheduler.UI;

import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Database.Repository;
import android.gabriel_izzo_c196_scheduler.Entity.Assessment;
import android.gabriel_izzo_c196_scheduler.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssessmentDetails extends AppCompatActivity {
    int assessmentID;
    EditText assessmentTitle;
    EditText assessmentStart;
    EditText assessmentEnd;
    Spinner assessmentTypeSpinner;
    String title;
    Date start;
    Date end;
    String type;
    Integer courseID;
    Repository repo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);

        Spinner assessmentTypeSpinner = findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> assessmentAdapter = ArrayAdapter.createFromResource(this, R.array.type_array, android.R.layout.simple_spinner_item);
        assessmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        assessmentTypeSpinner.setAdapter(assessmentAdapter);
        assessmentTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String type = adapterView.getItemAtPosition(i).toString();
             //   selectSpinnerItemByValue(assessmentTypeSpinner, l);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                if(type.equals("Objective Assessment")){
                    assessmentTypeSpinner.setSelection(0);
                }
                else{
                    assessmentTypeSpinner.setSelection(1);
                }
            }
        });

        assessmentTitle = findViewById(R.id.assessmentTitleTxt);
        assessmentStart = findViewById(R.id.assessmentStartTxt);
        assessmentEnd = findViewById(R.id.assessmentEndTxt);

        assessmentID = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");
        courseID = getIntent().getIntExtra("courseID",-1);
        assessmentTitle.setText(title);
        assessmentStart.setText(getIntent().getStringExtra("start"));
        assessmentEnd.setText(getIntent().getStringExtra("end"));

        repo = new Repository(getApplication());
    }
    /*
    public static void selectSpinnerItemByValue(Spinner spnr, long value){
        SimpleCursorAdapter adapter = (SimpleCursorAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++){
            if(adapter.getItemId(position) == value){
                spnr.setSelection(position);
                return;
            }
        }
    }
*/

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_assessment_details);

        Spinner assessmentTypeSpinner = findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> assessmentAdapter = ArrayAdapter.createFromResource(this, R.array.type_array, android.R.layout.simple_spinner_item);
        assessmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        assessmentTypeSpinner.setAdapter(assessmentAdapter);
        assessmentTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //   selectSpinnerItemByValue(assessmentTypeSpinner, l);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing
            }
        });

        assessmentTitle = findViewById(R.id.assessmentTitleTxt);
        assessmentStart = findViewById(R.id.assessmentStartTxt);
        assessmentEnd = findViewById(R.id.assessmentEndTxt);

        assessmentID = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");
        courseID = getIntent().getIntExtra("courseID",-1);
        assessmentTitle.setText(title);
        assessmentStart.setText(getIntent().getStringExtra("start"));
        assessmentEnd.setText(getIntent().getStringExtra("end"));




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
        Spinner assessmentTypeSpinner = (Spinner)findViewById(R.id.type_spinner);
        String type = assessmentTypeSpinner.getSelectedItem().toString();



        if (assessmentID == -1) {
            int newID = repo.getAllAssessments().get(repo.getAllAssessments().size() - 1).getAssessmentID() + 1;
            assessment = new Assessment(newID, assessmentTitle.getText().toString(), new Date(assessmentStart.getText().toString()), new Date(assessmentEnd.getText().toString()), type, null);
            repo.insert(assessment);
        } else {
            assessment = new Assessment(assessmentID, assessmentTitle.getText().toString(), new Date(assessmentStart.getText().toString()), new Date(assessmentEnd.getText().toString()), type, courseID);
            repo.update(assessment);
        }
    }
}
