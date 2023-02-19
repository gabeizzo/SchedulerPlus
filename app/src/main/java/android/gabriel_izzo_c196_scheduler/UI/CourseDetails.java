package android.gabriel_izzo_c196_scheduler.UI;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Database.Repository;
import android.gabriel_izzo_c196_scheduler.Entity.Course;
import android.gabriel_izzo_c196_scheduler.Entity.Term;
import android.gabriel_izzo_c196_scheduler.R;
import android.gabriel_izzo_c196_scheduler.Utility.AlarmReceiver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CourseDetails extends AppCompatActivity {
    EditText courseTitle;
    EditText courseStart;
    EditText courseEnd;
    EditText instructorName;
    EditText instructorPhone;
    EditText instructorEmail;
    RecyclerView courseAssessmentsRecyclerView;
    EditText courseNotes;
    Spinner termSpinner;
    Spinner courseStatusSpinner;
    TextView courseIDTxt;

    int courseID;
    String title;
    String status;
    String name;
    String phone;
    String email;
    String notes;
    int termID;

    Repository repo;

    ImageView startCalView;
    final Calendar startCal = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener startCalListener;

    ImageView endCalView;
    final Calendar endCal = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener endCalListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        courseTitle = findViewById(R.id.courseTitleTxt);
        courseStart = findViewById(R.id.courseStartTxt);
        courseEnd = findViewById(R.id.courseEndTxt);
        instructorName = findViewById(R.id.instructorNameTxt);
        instructorPhone = findViewById(R.id.instructorPhoneTxt);
        instructorEmail = findViewById(R.id.instructorEmailTxt);
        courseAssessmentsRecyclerView = findViewById(R.id.courseAssessmentsRecyclerView);
        startCalView = findViewById(R.id.startCalView);
        endCalView = findViewById(R.id.endCalView);
        courseNotes = findViewById(R.id.courseNotesTxt);
        courseIDTxt = findViewById(R.id.courseIDTxt);

        courseID = getIntent().getIntExtra("id", -1);
        if(courseID == -1){
            courseIDTxt.setText(R.string.auto_gen_txt);
        }else{
            courseIDTxt.setText(String.valueOf(courseID));
        }
        title = getIntent().getStringExtra("title");
        status = getIntent().getStringExtra("status");
        name = getIntent().getStringExtra("instructor");
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");
        notes = getIntent().getStringExtra("notes");
        termID = getIntent().getIntExtra("termID", -1);

        courseTitle.setText(title);
        courseStart.setText(getIntent().getStringExtra("start"));
        courseEnd.setText(getIntent().getStringExtra("end"));
        instructorName.setText(name);
        instructorPhone.setText(phone);
        instructorEmail.setText(email);
        courseNotes.setText(notes);

        try{
            switch (status) {
                case "In Progress":
                    courseStatusSpinner.setSelection(0);
                    break;
                case "Completed":
                    courseStatusSpinner.setSelection(1);
                    break;
                case "Planned to Take":
                    courseStatusSpinner.setSelection(2);
                    break;
                case "Dropped":
                    courseStatusSpinner.setSelection(3);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        displayCalendar();
        repo = new Repository(getApplication());

        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        courseAssessmentsRecyclerView.setAdapter(adapter);
        courseAssessmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(repo.getAssociatedAssessments(courseID));

        courseStatusSpinner = findViewById(R.id.course_status_spinner);
        ArrayAdapter<CharSequence> courseAdapter = ArrayAdapter.createFromResource(this, R.array.status_array, android.R.layout.simple_spinner_item);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseStatusSpinner.setAdapter(courseAdapter);
        courseStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                status = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        List<Term> allTerms = repo.getAllTerms();
        List<Integer> termIDs = new ArrayList<>();
        for(Term term : allTerms) {
            termIDs.add(term.getTermID());
        }
        termSpinner = findViewById(R.id.term_spinner);
        ArrayAdapter<Integer> termAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, termIDs);
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(termAdapter);
        int position = termAdapter.getPosition(termID);
        termSpinner.setSelection(position);

        termSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                termID = (int) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                try {
                    for (Term term : allTerms) {
                        if (term.getTermID() == termID) {
                            int position = termAdapter.getPosition(termID);
                            termSpinner.setSelection(position);
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
    @SuppressLint("CutPasteId")
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_course_details);

        courseTitle = findViewById(R.id.courseTitleTxt);
        courseStart = findViewById(R.id.courseStartTxt);
        courseEnd = findViewById(R.id.courseEndTxt);
        instructorName = findViewById(R.id.instructorNameTxt);
        instructorPhone = findViewById(R.id.instructorPhoneTxt);
        instructorEmail = findViewById(R.id.instructorEmailTxt);
        courseAssessmentsRecyclerView = findViewById(R.id.courseAssessmentsRecyclerView);
        startCalView = findViewById(R.id.startCalView);
        endCalView = findViewById(R.id.endCalView);
        courseNotes = findViewById(R.id.courseNotesTxt);
        courseIDTxt = findViewById(R.id.courseIDTxt);
        courseID = getIntent().getIntExtra("id", -1);
        if(courseID == -1){
            courseIDTxt.setText(R.string.auto_gen_txt);
        }else{
            courseIDTxt.setText(String.valueOf(courseID));

        }
        title = getIntent().getStringExtra("title");
        status = getIntent().getStringExtra("status");
        name = getIntent().getStringExtra("instructor");
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");
        notes = getIntent().getStringExtra("notes");
        termID = getIntent().getIntExtra("termID", -1);

        courseTitle.setText(title);
        courseStart.setText(getIntent().getStringExtra("start"));
        courseEnd.setText(getIntent().getStringExtra("end"));
        instructorName.setText(name);
        instructorPhone.setText(phone);
        instructorEmail.setText(email);
        courseNotes.setText(notes);

        courseStatusSpinner = findViewById(R.id.course_status_spinner);
        ArrayAdapter<CharSequence> courseAdapter = ArrayAdapter.createFromResource(this, R.array.status_array, android.R.layout.simple_spinner_item);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseStatusSpinner.setAdapter(courseAdapter);

        courseStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                status = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        try{
            switch (status) {
                case "In Progress":
                    courseStatusSpinner.setSelection(0);
                    break;
                case "Completed":
                    courseStatusSpinner.setSelection(1);
                    break;
                case "Planned to Take":
                    courseStatusSpinner.setSelection(2);
                    break;
                case "Dropped":
                    courseStatusSpinner.setSelection(3);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        displayCalendar();
        repo = new Repository(getApplication());

        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        courseAssessmentsRecyclerView.setAdapter(adapter);
        courseAssessmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(repo.getAssociatedAssessments(courseID));

        List<Term> allTerms = repo.getAllTerms();
        List<Integer> termIDs = new ArrayList<>();
        for(Term term : allTerms) {
            termIDs.add(term.getTermID());
        }

        termSpinner = findViewById(R.id.term_spinner);
        ArrayAdapter<Integer> termAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, termIDs);
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(termAdapter);
        int position = termAdapter.getPosition(termID);
        termSpinner.setSelection(position);
        termSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                termID = (int) adapterView.getItemAtPosition(i);



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                for(Term term : allTerms) {
                    if(term.getTermID()==termID){
                        int position = termAdapter.getPosition(termID);
                        termSpinner.setSelection(position);
                    }

                }
            }
        });
    }

    private void displayCalendar() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

        startCalView.setOnClickListener(v -> new DatePickerDialog(
                CourseDetails.this,
                startCalListener,
                startCal.get(Calendar.YEAR),
                startCal.get(Calendar.MONTH),
                startCal.get(Calendar.DAY_OF_MONTH)
        ).show());

        startCalListener = (view, year, month, dayOfMonth) -> {
            startCal.set(Calendar.YEAR, year);
            startCal.set(Calendar.MONTH, month);
            startCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            courseStart.setText(dateFormatter.format(startCal.getTime()));
        };

        endCalView.setOnClickListener(v -> new DatePickerDialog(
                CourseDetails.this,
                endCalListener,
                endCal.get(Calendar.YEAR),
                endCal.get(Calendar.MONTH),
                endCal.get(Calendar.DAY_OF_MONTH)
        ).show());

        endCalListener = (view, year, month, dayOfMonth) -> {
            endCal.set(Calendar.YEAR, year);
            endCal.set(Calendar.MONTH, month);
            endCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            courseEnd.setText(dateFormatter.format(endCal.getTime()));
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_course_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(CourseDetails.this, CourseList.class);
                startActivity(intent);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean inputNotValid() {
        return courseTitle.getText().toString().isEmpty() ||
                courseStart.getText().toString().isEmpty() ||
                courseEnd.getText().toString().isEmpty() ||
                courseStatusSpinner.getSelectedItem() == null ||
                courseStatusSpinner.getSelectedItem().toString().isEmpty() ||
                termSpinner.getSelectedItem() == null ||
                termSpinner.getSelectedItem().toString().isEmpty() ||
                instructorName.getText().toString().isEmpty() ||
                instructorEmail.getText().toString().isEmpty() ||
                instructorPhone.getText().toString().isEmpty();
    }

    public void saveCourse(View view) {
        Course course;
        String status = courseStatusSpinner.getSelectedItem().toString();
        int termID = (int) termSpinner.getSelectedItem();

      try{
        if(inputNotValid()){
            if( termSpinner.getSelectedItem() == null || termSpinner.getSelectedItem().toString().isEmpty()) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("No Term to associate this Course with. Create the Term first to associate a Courses with it.");
                dialog.setTitle("No Terms Created Yet!");
                dialog.show();}
            else{
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("To save this Course, all fields must be filled out.");
                dialog.setTitle("Uh-Oh!");
                dialog.show();
            }

        }else {

            if (courseID == -1) {
               // int newID = repo.getAllCourses().get(repo.getAllCourses().size() - 1).getCourseID() + 1;
                course = new Course(0, courseTitle.getText().toString(), new Date(courseStart.getText().toString()),
                        new Date(courseEnd.getText().toString()), status, instructorName.getText().toString(),
                        instructorPhone.getText().toString(), instructorEmail.getText().toString(), courseNotes.getText().toString(), termID);
                repo.insert(course);

                Toast toast = Toast.makeText(this, "New Course Added", Toast.LENGTH_LONG);
                toast.show();

                if(termID > 0) {
                    Intent intent = new Intent(CourseDetails.this, TermDetails.class);
                    intent.putExtra("id", termID);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(CourseDetails.this, CourseList.class);
                    startActivity(intent);
                }
            }
            else {
                course = new Course(courseID, courseTitle.getText().toString(), new Date(courseStart.getText().toString()),
                        new Date(courseEnd.getText().toString()), status, instructorName.getText().toString(),
                        instructorPhone.getText().toString(), instructorEmail.getText().toString(), courseNotes.getText().toString(), termID);
                repo.update(course);

                Toast toast = Toast.makeText(this, "Course Updated", Toast.LENGTH_LONG);
                toast.show();

                Intent intent = new Intent(CourseDetails.this, TermDetails.class);
                intent.putExtra("id", termID);
                startActivity(intent);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //Terms Menu option
    public void goToTerms(MenuItem item) {
        Intent intent = new Intent(CourseDetails.this, TermList.class);
        startActivity(intent);
    }
    //Assessments Menu option
    public void goToAssessments(MenuItem item) {
        Intent intent = new Intent(CourseDetails.this, AssessmentList.class);
        startActivity(intent);
    }

    //Share Menu option
    public void shareCourseDetails(MenuItem item) {
        EditText noteToShare = findViewById(R.id.courseNotesTxt);
        EditText title = findViewById(R.id.courseTitleTxt);
        EditText start = findViewById(R.id.courseStartTxt);
        EditText end = findViewById(R.id.courseEndTxt);
        EditText instructor = findViewById(R.id.instructorNameTxt);
        EditText phone = findViewById(R.id.instructorPhoneTxt);
        EditText instructorEmail = findViewById(R.id.instructorEmailTxt);
        Spinner courseStatus = findViewById(R.id.course_status_spinner);

        String message = title.getText().toString() + "\n"
                + start.getText().toString() + "\n"
                + end.getText().toString() + "\n"
                + courseStatus.getSelectedItem().toString() + "\n"
                + instructor.getText().toString() + "\n"
                + phone.getText().toString() + "\n"
                + instructorEmail.getText().toString()+ "\n\n\n"
                + noteToShare.getText().toString();
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_TEXT, message);

        //prompts email client only, without this line other options will show
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    public void setCourseAlerts(MenuItem item) {
        long alertStart = new Date(courseStart.getText().toString()).getTime();
        long alertEnd = new Date(courseEnd.getText().toString()).getTime();
        String title = courseTitle.getText().toString();
        //Start Alert
        Intent startAlertIntent = new Intent(this, AlarmReceiver.class);
        startAlertIntent.putExtra("text", title);
        startAlertIntent.putExtra("title", "Course Start Alert!");

        PendingIntent pendingStartIntent = PendingIntent.getBroadcast(this, MainActivity.broadcastID++, startAlertIntent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager startAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        startAlarmManager.set(AlarmManager.RTC_WAKEUP, alertStart, pendingStartIntent);
        //End Alert
        Intent endAlertIntent = new Intent(this, AlarmReceiver.class);
        endAlertIntent.putExtra("text", title);
        endAlertIntent.putExtra("title", "Course End Alert!");

        PendingIntent pendingEndIntent = PendingIntent.getBroadcast(this, MainActivity.broadcastID++, endAlertIntent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager endAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        endAlarmManager.set(AlarmManager.RTC_WAKEUP, alertEnd, pendingEndIntent);

        Toast toast = Toast.makeText(this, "Course Start and End Alerts Set!", Toast.LENGTH_LONG);
        toast.show();

    }



    public void deleteCourse(MenuItem item) {
        Course course;
        try {
            if (courseID != -1) {
                course = new Course(
                        courseID,
                        courseTitle.getText().toString(),
                        new Date(courseStart.getText().toString()),
                        new Date(courseEnd.getText().toString()),
                        status,
                        instructorName.getText().toString(),
                        instructorPhone.getText().toString(),
                        instructorEmail.getText().toString(),
                        courseNotes.getText().toString(),
                        termID
                        );

                if(repo.getAssociatedAssessments(courseID).size() == 0){
                    repo.delete(course);

                    Toast toast = Toast.makeText(this, "Course Deleted Successfully", Toast.LENGTH_LONG);
                    toast.show();

                    Intent intent=new Intent(CourseDetails.this, CourseList.class);
                    startActivity(intent);
                }
                else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setMessage("To Delete this Course, re-assign or delete the Assessments associated with it.");
                    dialog.setTitle("Uh-Oh!");
                    dialog.show();

                }
            }
            else{
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("Courses that have not been saved cannot be deleted.");
                dialog.setTitle("Uh-Oh!");
                dialog.show();

                }
            }
                catch (Exception e) {
                e.printStackTrace();
            }
        }
}