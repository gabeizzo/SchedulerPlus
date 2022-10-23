package android.gabriel_izzo_c196_scheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Database.Repository;
import android.gabriel_izzo_c196_scheduler.Entity.Assessment;
import android.gabriel_izzo_c196_scheduler.Entity.Course;
import android.gabriel_izzo_c196_scheduler.Entity.Term;
import android.gabriel_izzo_c196_scheduler.R;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterApp(View view) {
        Intent intent=new Intent(MainActivity.this, TermList.class);
        startActivity(intent);
        Repository repo=new Repository(getApplication());
        Term term=new Term(1,"10/1/2022-04/30/2023",100.0);
        repo.insert(term);
        Assessment assessment=new Assessment(1,"Objective Assessment",100.0);
        repo.insert(assessment);
        Course course=new Course(1,"Mobile Applications",100.0);
        repo.insert(course);
    }
}