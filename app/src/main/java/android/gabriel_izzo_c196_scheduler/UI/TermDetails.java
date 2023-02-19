package android.gabriel_izzo_c196_scheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TermDetails extends AppCompatActivity {
    int termID;
    TextView termIDTxt;
    EditText termTitle;
    EditText termStart;
    EditText termEnd;
    TextView termHint;
    RecyclerView addedCourses;
    RecyclerView allCourses;

    ImageView startCalView;
    final Calendar startCal = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener startCalListener;

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

        termHint = findViewById(R.id.termHintLbl);
        termIDTxt = findViewById(R.id.termIDTxt);
        termTitle = findViewById(R.id.termTitleTxt);
        termStart = findViewById(R.id.termStartTxt);
        termEnd = findViewById(R.id.termEndTxt);
        allCourses = findViewById(R.id.allCourses);
        addedCourses = findViewById(R.id.addedCourses);
        startCalView = findViewById(R.id.startCalView);
        endCalView = findViewById(R.id.endCalView);
        termID = getIntent().getIntExtra("id", -1);
        if(termID == -1){
            termIDTxt.setText(R.string.auto_gen_txt);
        }else{
            termIDTxt.setText(String.valueOf(termID));
        }
        termTitle.setText(getIntent().getStringExtra("title"));
        termStart.setText(getIntent().getStringExtra("start"));
        termEnd.setText(getIntent().getStringExtra("end"));
        
        Term term = null;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        displayCalendar();
        setHintVisibility(termID);

        repo = new Repository(getApplication());

        Button saveButton = findViewById(R.id.saveButton);
        if (termID != -1) {
            saveButton.setText(R.string.update_term_lbl);
            setHintVisibility(termID);

            for(Term t: repo.getAllTerms()){
                if(t.getTermID() == termID){
                term = t;
                }
            }
            if(term!=null) {
                termTitle.setText(term.getTermTitle());
                termStart.setText(dateFormatter.format(term.getTermStart()));
                termEnd.setText(dateFormatter.format(term.getTermEnd()));
                setHintVisibility(term.getTermID());
            }
        }
        else{
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
        termHint = findViewById(R.id.termHintLbl);
        termIDTxt = findViewById(R.id.termIDTxt);
        termTitle = findViewById(R.id.termTitleTxt);
        termStart = findViewById(R.id.termStartTxt);
        termEnd = findViewById(R.id.termEndTxt);
        allCourses = findViewById(R.id.allCourses);
        addedCourses = findViewById(R.id.addedCourses);
        startCalView = findViewById(R.id.startCalView);
        endCalView = findViewById(R.id.endCalView);
        termID = getIntent().getIntExtra("id", -1);
        if(termID == -1){
            termIDTxt.setText(R.string.auto_gen_txt);
        }else{
            termIDTxt.setText(String.valueOf(termID));
        }
        termTitle.setText(getIntent().getStringExtra("title"));
        termStart.setText(getIntent().getStringExtra("start"));
        termEnd.setText(getIntent().getStringExtra("end"));

        Term term = null;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

        displayCalendar();
        setHintVisibility(termID);

        repo = new Repository(getApplication());
        Button saveButton = findViewById(R.id.saveButton);

        if (termID != -1) {
            setHintVisibility(termID);
            saveButton.setText(R.string.update_term_lbl);
            for(Term t: repo.getAllTerms()){
                if(t.getTermID() == termID){
                    term = t;
                }
            }
            if(term!=null) {
                termTitle.setText(term.getTermTitle());
                termStart.setText(dateFormatter.format(term.getTermStart()));
                termEnd.setText(dateFormatter.format(term.getTermEnd()));
                setHintVisibility(term.getTermID());
            }
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

    private void displayCalendar() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

        startCalView.setOnClickListener(v -> new DatePickerDialog(
                TermDetails.this,
                startCalListener,
                startCal.get(Calendar.YEAR),
                startCal.get(Calendar.MONTH),
                startCal.get(Calendar.DAY_OF_MONTH)
        ).show());

        startCalListener = (view, year, month, dayOfMonth) -> {
            startCal.set(Calendar.YEAR, year);
            startCal.set(Calendar.MONTH, month);
            startCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            termStart.setText(dateFormatter.format(startCal.getTime()));
        };

        endCalView.setOnClickListener(v -> new DatePickerDialog(
                TermDetails.this,
                endCalListener,
                endCal.get(Calendar.YEAR),
                endCal.get(Calendar.MONTH),
                endCal.get(Calendar.DAY_OF_MONTH)
        ).show());

        endCalListener = (view, year, month, dayOfMonth) -> {
            endCal.set(Calendar.YEAR, year);
            endCal.set(Calendar.MONTH, month);
            endCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            termEnd.setText(dateFormatter.format(endCal.getTime()));
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
                Intent intent=new Intent(TermDetails.this, TermList.class);
                startActivity(intent);
                this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    private boolean inputNotValid() {
        return termTitle.getText().toString().isEmpty() ||
                termStart.getText().toString().isEmpty() ||
                termEnd.getText().toString().isEmpty();
    }

    public void saveTerm(View view) {
        Term term;

        try {
            if (inputNotValid()) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("To save this Term, all fields must be filled out.");
                dialog.setTitle("Uh-Oh!");
                dialog.show();
            } else {
                if (termID == -1) {
                    int newID = 1;
                    if (repo.getAllTerms().size() != 0)
                        newID = repo.getAllTerms().get(repo.getAllTerms().size() - 1).getTermID() + 1;
                    term = new Term(newID, termTitle.getText().toString(), new Date(termStart.getText().toString()), new Date(termEnd.getText().toString()));
                    repo.insert(term);

                    Toast toast = Toast.makeText(this, "New Term Added", Toast.LENGTH_LONG);
                    toast.show();

                    Intent intent = new Intent(TermDetails.this, TermList.class);
                    startActivity(intent);


                } else {
                    term = new Term(termID, termTitle.getText().toString(), new Date(termStart.getText().toString()), new Date(termEnd.getText().toString()));
                    repo.update(term);

                    Toast toast = Toast.makeText(this, "Term Updated", Toast.LENGTH_LONG);
                    toast.show();

                    Intent intent = new Intent(TermDetails.this, TermList.class);
                    startActivity(intent);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteTerm(MenuItem item) {
        Term term;
        try {
            if (termID != -1) {
                term = new Term(termID, termTitle.getText().toString(), new Date(termStart.getText().toString()), new Date(termEnd.getText().toString()));

                if(repo.getAssociatedCourses(termID).size() == 0){
                repo.delete(term);

                Toast toast = Toast.makeText(this, "Term Deleted Successfully", Toast.LENGTH_LONG);
                toast.show();

                Intent intent=new Intent(TermDetails.this, TermList.class);
                startActivity(intent);
                }
                else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setMessage("To Delete this Term, re-assign or delete the Courses associated with it.");
                    dialog.setTitle("Uh-Oh!");
                    dialog.show();

                 }
            }
            else{
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("Terms that have not been saved cannot be deleted.");
                dialog.setTitle("Uh-Oh!");
                dialog.show();

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

    public void setHintVisibility(int termID){
        if(termID!=-1){
            termHint.setVisibility(View.GONE);
        }
        else{
           termHint.setVisibility(View.VISIBLE);
        }
    }


}