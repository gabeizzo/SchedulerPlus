package android.gabriel_izzo_c196_scheduler.UI;

import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Database.Repository;
import android.gabriel_izzo_c196_scheduler.Entity.Course;
import android.gabriel_izzo_c196_scheduler.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CourseDetails extends AppCompatActivity {

    int courseID;
    EditText courseTitle;
    EditText courseStart;
    EditText courseEnd;
    EditText courseStatus;
    EditText instructorName;
    EditText instructorPhone;
    EditText instructorEmail;
    EditText courseNotes;
    String title;
    String start;
    String end;
    String status;
    String name;
    String phone;
    String email;
    String notes;
    Repository repo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        courseTitle = findViewById(R.id.courseTitleTxt);
        courseStart = findViewById(R.id.courseStartTxt);
        courseEnd = findViewById(R.id.courseEndTxt);
        courseStatus = findViewById(R.id.courseStatusTxt);
        instructorName = findViewById(R.id.instructorNameTxt);
        instructorPhone = findViewById(R.id.instructorPhoneTxt);
        instructorEmail = findViewById(R.id.instructorEmailTxt);
        courseNotes = findViewById(R.id.courseNotesTxt);
        courseID = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        status = getIntent().getStringExtra("status");
        name = getIntent().getStringExtra("instructor");
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");
        notes = getIntent().getStringExtra("notes");
        courseTitle.setText(title);
        courseStart.setText(start);
        courseEnd.setText(end);
        courseStatus.setText(status);
        instructorName.setText(name);
        instructorPhone.setText(phone);
        instructorEmail.setText(email);
        courseNotes.setText(notes);


        repo = new Repository(getApplication());
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
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveCourse(View view) {
        Course course;
        if (courseID == -1) {
            int newID = repo.getAllCourses().get(repo.getAllCourses().size() - 1).getCourseID() + 1;
            course = new Course(newID, courseTitle.getText().toString(), courseStart.getText().toString(),
                    courseEnd.getText().toString(), courseStatus.getText().toString(), instructorName.getText().toString(),
                    instructorPhone.getText().toString(), instructorEmail.getText().toString(), courseNotes.getText().toString());
            repo.insert(course);
        } else {
            course = new Course(courseID, courseTitle.getText().toString(), courseStart.getText().toString(),
                    courseEnd.getText().toString(), courseStatus.getText().toString(), instructorName.getText().toString(),
                    instructorPhone.getText().toString(), instructorEmail.getText().toString(), courseNotes.getText().toString());
            repo.update(course);
        }
    }

    public void goToTerms(MenuItem item) {
        Intent intent = new Intent(CourseDetails.this, TermList.class);
        startActivity(intent);
    }

    public void goToAssessments(MenuItem item) {
        Intent intent = new Intent(CourseDetails.this, AssessmentList.class);
        startActivity(intent);
    }

    public void shareCourseDetails(MenuItem item) {
        EditText noteToShare = findViewById(R.id.courseNotesTxt);
        String message = noteToShare.getText().toString();
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_TEXT, message);

        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
}