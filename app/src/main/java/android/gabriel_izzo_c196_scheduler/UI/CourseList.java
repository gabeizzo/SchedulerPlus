package android.gabriel_izzo_c196_scheduler.UI;

import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.DAO.CourseDAO;
import android.gabriel_izzo_c196_scheduler.Database.Repository;
import android.gabriel_izzo_c196_scheduler.Entity.Course;
import android.gabriel_izzo_c196_scheduler.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView=findViewById(R.id.course_list_recyclerView);
        Repository repo=new Repository(getApplication());
        List<Course> courses=repo.getAllCourses();
        final CourseAdapter adapter=new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCourses(courses);
    }

    @Override
    protected void onResume() {
        super.onResume();
            setContentView(R.layout.activity_course_list);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            RecyclerView recyclerView=findViewById(R.id.course_list_recyclerView);
            Repository repo=new Repository(getApplication());
            List<Course> courses=repo.getAllCourses();
            final CourseAdapter adapter=new CourseAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setCourses(courses);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_course_list, menu);
        return true;
    }

    public void goToCourseDetails(View view) {
        Intent intent=new Intent(CourseList.this, CourseDetails.class);
        intent.putExtra("status", "In Progress");

        startActivity(intent);
    }
    public void goToAssessments(MenuItem item) {
        Intent intent=new Intent(CourseList.this, AssessmentList.class);
        startActivity(intent);
    }
    public void goToTerms(MenuItem item) {
        Intent intent=new Intent(CourseList.this, TermList.class);
        startActivity(intent);
    }

}
