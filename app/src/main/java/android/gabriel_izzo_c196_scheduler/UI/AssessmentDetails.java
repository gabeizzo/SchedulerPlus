package android.gabriel_izzo_c196_scheduler.UI;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Database.Repository;
import android.gabriel_izzo_c196_scheduler.Entity.Assessment;
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
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
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
    String title;
    String type;
    int courseID;
    Spinner courseSpinner;
    Spinner assessmentTypeSpinner;

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
        setContentView(R.layout.activity_assessment_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        assessmentTypeSpinner = findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> assessmentAdapter = ArrayAdapter.createFromResource(this, R.array.type_array, android.R.layout.simple_spinner_item);
        assessmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        assessmentTypeSpinner.setAdapter(assessmentAdapter);
        assessmentTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = adapterView.getItemAtPosition(i).toString();
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
        courseID =getIntent().getIntExtra("courseID",-1);

        assessmentTitle.setText(title);
        assessmentStart.setText(getIntent().getStringExtra("start"));
        assessmentEnd.setText(getIntent().getStringExtra("end"));

        if(type.equals("Objective Assessment")){
            assessmentTypeSpinner.setSelection(0);
        }else if(type.equals("Performance Assessment")) {
            assessmentTypeSpinner.setSelection(1);
        }

        displayCalendar();

        repo = new Repository(getApplication());

        List<Course> allCourses = repo.getAllCourses();
        List<Integer> courseIDs = new ArrayList<>();
        for(Course course : allCourses) {
            courseIDs.add(course.getCourseID());
        }
        courseSpinner = findViewById(R.id.course_spinner);
        ArrayAdapter<Integer> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseIDs);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);
        int position = courseAdapter.getPosition(courseID);
        courseSpinner.setSelection(position);

        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                courseID = (int) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                try {
                    for (Course c : allCourses) {
                        if (c.getCourseID() == courseID) {
                            int position = courseAdapter.getPosition(courseID);
                            courseSpinner.setSelection(position);
                        }

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void displayCalendar() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

        startCalView.setOnClickListener(v -> new DatePickerDialog(
                AssessmentDetails.this,
                startCalListener,
                startCal.get(Calendar.YEAR),
                startCal.get(Calendar.MONTH),
                startCal.get(Calendar.DAY_OF_MONTH)
        ).show());

        startCalListener = (view, year, month, dayOfMonth) -> {
            startCal.set(Calendar.YEAR, year);
            startCal.set(Calendar.MONTH, month);
            startCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            assessmentStart.setText(dateFormatter.format(startCal.getTime()));
        };

        endCalView.setOnClickListener(v -> new DatePickerDialog(
                AssessmentDetails.this,
                endCalListener,
                endCal.get(Calendar.YEAR),
                endCal.get(Calendar.MONTH),
                endCal.get(Calendar.DAY_OF_MONTH)
        ).show());

        endCalListener = (view, year, month, dayOfMonth) -> {
            endCal.set(Calendar.YEAR, year);
            endCal.set(Calendar.MONTH, month);
            endCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            assessmentEnd.setText(dateFormatter.format(endCal.getTime()));
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_assessment_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        assessmentTypeSpinner = findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> assessmentAdapter = ArrayAdapter.createFromResource(this, R.array.type_array, android.R.layout.simple_spinner_item);
        assessmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        assessmentTypeSpinner.setAdapter(assessmentAdapter);
        assessmentTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = adapterView.getItemAtPosition(i).toString();
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
        courseID =getIntent().getIntExtra("courseID",-1);

        assessmentTitle.setText(title);
        assessmentStart.setText(getIntent().getStringExtra("start"));
        assessmentEnd.setText(getIntent().getStringExtra("end"));

        if(type.equals("Objective Assessment")){
            assessmentTypeSpinner.setSelection(0);
        }else if(type.equals("Performance Assessment")) {
            assessmentTypeSpinner.setSelection(1);
        }

        displayCalendar();

        repo = new Repository(getApplication());

        List<Course> allCourses = repo.getAllCourses();
        List<Integer> courseIDs = new ArrayList<>();
        for(Course course : allCourses) {
            courseIDs.add(course.getCourseID());
        }
        courseSpinner = findViewById(R.id.course_spinner);
        ArrayAdapter<Integer> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseIDs);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);
        int position = courseAdapter.getPosition(courseID);
        courseSpinner.setSelection(position);

        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                courseID = (int) adapterView.getItemAtPosition(i);



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                try {
                    for (Course c : allCourses) {
                        if (c.getCourseID() == courseID) {
                            int position = courseAdapter.getPosition(courseID);
                            courseSpinner.setSelection(position);
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessment_details, menu);
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

    private boolean inputNotValid() {
        return assessmentTitle.getText().toString().isEmpty() ||
                assessmentStart.getText().toString().isEmpty() ||
                assessmentEnd.getText().toString().isEmpty() ||
                assessmentTypeSpinner.getSelectedItem() == null ||
                courseSpinner.getSelectedItem()==null;
    }

    public void saveAssessment(View view) {
        Assessment assessment;
        try {
            if (inputNotValid()) {
                if (courseSpinner.getSelectedItem() == null) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setMessage("No Course to associate this Assessment with. Create the Term and Course first to associate this Assessment with a Course.");
                    dialog.setTitle("No Courses Created Yet!");
                    dialog.show();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setMessage("To save this Assessment, all fields must be filled out.");
                    dialog.setTitle("Uh-Oh!");
                    dialog.show();
                }

            } else {
                String type = assessmentTypeSpinner.getSelectedItem().toString();
                int assessmentCourseID = (int) courseSpinner.getSelectedItem();


                if (assessmentID == -1) {
                    //int newID = repo.getAllAssessments().get(repo.getAllAssessments().size() - 1).getAssessmentID() + 1;
                    assessment = new Assessment(
                            0,
                            assessmentTitle.getText().toString(),
                            new Date(assessmentStart.getText().toString()),
                            new Date(assessmentEnd.getText().toString()),
                            type,
                            assessmentCourseID);

                    repo.insert(assessment);
                    Toast toast = Toast.makeText(this, "New Assessment Added!", Toast.LENGTH_LONG);
                    toast.show();

                    Intent intent = new Intent(AssessmentDetails.this, AssessmentList.class);
                    startActivity(intent);
                } else {
                    assessment = new Assessment(
                            assessmentID,
                            assessmentTitle.getText().toString(),
                            new Date(assessmentStart.getText().toString()),
                            new Date(assessmentEnd.getText().toString()),
                            type,
                            assessmentCourseID);

                    repo.update(assessment);
                    Toast toast = Toast.makeText(this, "Assessment Updated!", Toast.LENGTH_LONG);
                    toast.show();

                    Intent intent = new Intent(AssessmentDetails.this, AssessmentList.class);
                    startActivity(intent);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteAssessment(MenuItem item) {
        Assessment assessment;
        try {
            if (assessmentID != -1) {
                assessment = new Assessment(assessmentID, assessmentTitle.getText().toString(), new Date(assessmentStart.getText().toString()), new Date(assessmentEnd.getText().toString()), type ,courseID);
                repo.delete(assessment);

                Toast toast = Toast.makeText(this, "Assessment Deleted Successfully!", Toast.LENGTH_LONG);
                toast.show();

                Intent intent=new Intent(AssessmentDetails.this, AssessmentList.class);
                startActivity(intent);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAssessmentAlerts(MenuItem item) {
        long alertStart = new Date(assessmentStart.getText().toString()).getTime();
        long alertEnd = new Date(assessmentEnd.getText().toString()).getTime();
        String title = assessmentTitle.getText().toString();
        //Start Alert
        Intent startAlertIntent = new Intent(this, AlarmReceiver.class);
        startAlertIntent.putExtra("text", title);
        startAlertIntent.putExtra("title", "Assessment Start Alert!");

        PendingIntent pendingStartIntent = PendingIntent.getBroadcast(this, MainActivity.broadcastID++, startAlertIntent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager startAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        startAlarmManager.set(AlarmManager.RTC_WAKEUP, alertStart, pendingStartIntent);
        //End Alert
        Intent endAlertIntent = new Intent(this, AlarmReceiver.class);
        endAlertIntent.putExtra("text", title);
        endAlertIntent.putExtra("title", "Assessment End Alert!");

        PendingIntent pendingEndIntent = PendingIntent.getBroadcast(this, MainActivity.broadcastID++, endAlertIntent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager endAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        endAlarmManager.set(AlarmManager.RTC_WAKEUP, alertEnd, pendingEndIntent);

        Toast toast = Toast.makeText(this, "Assessment Start and End Alerts Set!", Toast.LENGTH_LONG);
        toast.show();

    }

}
