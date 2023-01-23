package android.gabriel_izzo_c196_scheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Database.Repository;
import android.gabriel_izzo_c196_scheduler.Entity.Course;
import android.gabriel_izzo_c196_scheduler.Entity.Term;
import android.gabriel_izzo_c196_scheduler.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TermDetails extends AppCompatActivity {
    int termID;
    EditText termTitle;
    EditText termStart;
    EditText termEnd;
    RecyclerView addedCourses;
    RecyclerView allCourses;

    Date start;
    ImageView startCalView;
    final Calendar startCal = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener startCalListener;

    Date end;
    ImageView endCalView;
    final Calendar endCal = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener endCalListener;

    Repository repo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        termTitle = findViewById(R.id.termTitleTxt);
        termStart = findViewById(R.id.termStartTxt);
        termEnd = findViewById(R.id.termEndTxt);
        allCourses = findViewById(R.id.allCourses);
        addedCourses = findViewById(R.id.addedCourses);
        startCalView = findViewById(R.id.startCalView);
        endCalView = findViewById(R.id.endCalView);
        termID = getIntent().getIntExtra("id", -1);
        termTitle.setText(getIntent().getStringExtra("title"));
        termStart.setText(getIntent().getStringExtra("start"));
        termEnd.setText(getIntent().getStringExtra("end"));

        displayCalendar();

        repo = new Repository(getApplication());

        Button saveButton = findViewById(R.id.saveButton);
        if (termID != -1) {
            saveButton.setText(R.string.update_term_lbl);
        } else{
            saveButton.setText(R.string.save_btn);
        }

        final CourseAdapter addedCoursesAdapter = new CourseAdapter(this);
        addedCourses.setAdapter(addedCoursesAdapter);
        addedCourses.setLayoutManager(new LinearLayoutManager(this));
        addedCoursesAdapter.setCourses(repo.getAssociatedCourses(termID));

        final CourseAdapter allCoursesAdapter = new CourseAdapter(this);
        allCourses.setAdapter(allCoursesAdapter);
        allCourses.setLayoutManager(new LinearLayoutManager(this));
        allCoursesAdapter.setCourses(repo.getAllCourses());

    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_term_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        termTitle = findViewById(R.id.termTitleTxt);
        termStart = findViewById(R.id.termStartTxt);
        termEnd = findViewById(R.id.termEndTxt);
        allCourses = findViewById(R.id.allCourses);
        addedCourses = findViewById(R.id.addedCourses);
        startCalView = findViewById(R.id.startCalView);
        endCalView = findViewById(R.id.endCalView);
        termID = getIntent().getIntExtra("id", -1);
        termTitle.setText(getIntent().getStringExtra("title"));
        termStart.setText(getIntent().getStringExtra("start"));
        termEnd.setText(getIntent().getStringExtra("end"));

        displayCalendar();

        repo = new Repository(getApplication());

        if (termID != -1) {
            Button saveButton = findViewById(R.id.saveButton);
            saveButton.setText(R.string.update_term_lbl);
        }
        else{
            Button saveButton = findViewById(R.id.saveButton);
            saveButton.setText(R.string.save_btn);
        }
        final CourseAdapter addedCoursesAdapter = new CourseAdapter(this);
        addedCourses.setAdapter(addedCoursesAdapter);
        addedCourses.setLayoutManager(new LinearLayoutManager(this));
        addedCoursesAdapter.setCourses(repo.getAssociatedCourses(termID));


        final CourseAdapter allCoursesAdapter = new CourseAdapter(this);
        allCourses.setAdapter(allCoursesAdapter);
        allCourses.setLayoutManager(new LinearLayoutManager(this));
        allCoursesAdapter.setCourses(repo.getAllCourses());

    }

    private void displayCalendar() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

        startCalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(
                        TermDetails.this,
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

                termStart.setText(dateFormatter.format(startCal.getTime()));
            }
        };

        endCalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(
                        TermDetails.this,
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

                termEnd.setText(dateFormatter.format(endCal.getTime()));
            }
        };
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

        try {
            if (termID == -1) {
                int newID = repo.getAllTerms().get(repo.getAllTerms().size() - 1).getTermID() + 1;
                term = new Term(newID, termTitle.getText().toString(), new Date(termStart.getText().toString()), new Date(termEnd.getText().toString()));
                repo.insert(term);

                Toast toast = Toast.makeText(this, "New Term Added", Toast.LENGTH_LONG);
                toast.show();

                Intent intent=new Intent(TermDetails.this, TermList.class);
                startActivity(intent);


            } else {
                term = new Term(termID, termTitle.getText().toString(), new Date(termStart.getText().toString()), new Date(termEnd.getText().toString()));
                repo.update(term);

                Toast toast = Toast.makeText(this, "Term Updated", Toast.LENGTH_LONG);
                toast.show();

                Intent intent=new Intent(TermDetails.this, TermList.class);
                startActivity(intent);

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteTerm(MenuItem item) {
        Term term;
        try {
            if (termID != -1) {
                term = new Term(termID, termTitle.getText().toString(), new Date(termStart.getText().toString()), new Date(termEnd.getText().toString()));
                repo.delete(term);

                Toast toast = Toast.makeText(this, "Term Deleted Successfully", Toast.LENGTH_LONG);
                toast.show();

                Intent intent=new Intent(TermDetails.this, TermList.class);
                startActivity(intent);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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