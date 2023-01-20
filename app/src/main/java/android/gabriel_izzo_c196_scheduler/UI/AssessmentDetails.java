package android.gabriel_izzo_c196_scheduler.UI;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AssessmentDetails extends AppCompatActivity {
    int assessmentID;
    EditText assessmentTitle;
    EditText assessmentStart;
    EditText assessmentEnd;
    Spinner assessmentTypeSpinner;
    String title;
    String type;
    Integer courseID;
    Repository repo;

    Date start;
    ImageView startCalView;
    final Calendar startCal = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener startCalListener;

    Date end;
    ImageView endCalView;
    final Calendar endCal = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener endCalListener;


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
        startCalView = findViewById(R.id.startCalView);
        endCalView = findViewById(R.id.endCalView);

        assessmentID = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");
        courseID = getIntent().getIntExtra("courseID",-1);
        assessmentTitle.setText(title);
        assessmentStart.setText(getIntent().getStringExtra("start"));
        assessmentEnd.setText(getIntent().getStringExtra("end"));
        displayCalendar();

        repo = new Repository(getApplication());

    }


    private void displayCalendar() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

        startCalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(
                        AssessmentDetails.this,
                        startCalListener,
                        startCal.get(Calendar.YEAR),
                        startCal.get(Calendar.MONTH),
                        startCal.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

        startCalListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                startCal.set(Calendar.YEAR, year);
                startCal.set(Calendar.MONTH, month);
                startCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                assessmentStart.setText(dateFormatter.format(startCal.getTime()));
            }
        };

        endCalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(
                        AssessmentDetails.this,
                        endCalListener,
                        endCal.get(Calendar.YEAR),
                        endCal.get(Calendar.MONTH),
                        endCal.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

        endCalListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                endCal.set(Calendar.YEAR, year);
                endCal.set(Calendar.MONTH, month);
                endCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                assessmentEnd.setText(dateFormatter.format(endCal.getTime()));
            }
        };
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
        startCalView = findViewById(R.id.startCalView);
        endCalView = findViewById(R.id.endCalView);

        assessmentID = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");
        courseID = getIntent().getIntExtra("courseID",-1);
        assessmentTitle.setText(title);
        assessmentStart.setText(getIntent().getStringExtra("start"));
        assessmentEnd.setText(getIntent().getStringExtra("end"));


        displayCalendar();

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
