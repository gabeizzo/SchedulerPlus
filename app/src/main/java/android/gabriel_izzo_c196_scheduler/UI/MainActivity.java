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

import java.util.Date;

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
        Term term=new Term(1,"Spring 2023", new Date(01/01/2023), new Date(01/06/2023));
        repo.insert(term);
        Assessment assessment=new Assessment(1,"ABM2 — ABM2 TASK 1: MOBILE APPLICATION DEVELOPMENT",new Date(01/01/2021),new Date(01/06/2021),"Objective Assessment",1);
        repo.insert(assessment);
        Course course=new Course(1,"Mobile Applications", new Date(01/01/2023), new Date(06/01/2023), "In Progress", "Mike Jones", "123-456-7891", "mikeinstructor@wgu.edu", "Example Course Notes (Share via email using the menu option on the top right of the screen).", 1);
        repo.insert(course);
    }
}