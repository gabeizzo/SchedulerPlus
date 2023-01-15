package android.gabriel_izzo_c196_scheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Database.Repository;
import android.gabriel_izzo_c196_scheduler.Entity.Term;
import android.gabriel_izzo_c196_scheduler.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class TermList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView termListRecyclerView = findViewById(R.id.tl_recyclerView);
        Repository repo = new Repository(getApplication());
        List<Term> terms = repo.getAllTerms();
        final TermAdapter adapter = new TermAdapter(this);
        termListRecyclerView.setAdapter(adapter);
        termListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(terms);
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.activity_term_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView termListRecyclerView = findViewById(R.id.tl_recyclerView);
        Repository repo = new Repository(getApplication());
        List<Term> terms = repo.getAllTerms();
        final TermAdapter adapter = new TermAdapter(this);
        termListRecyclerView.setAdapter(adapter);
        termListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(terms);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_term_list, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
        return true;
            case R.id.tl_assessments:
                intent = new Intent(this, AssessmentList.class);
                this.startActivity(intent);
                this.finish();
                return true;
            case R.id.tl_courses:
                intent = new Intent(this, CourseList.class);
                this.startActivity(intent);
                this.finish();
                return true;
    }
    return super.onOptionsItemSelected(item);
}


    public void goToTermDetails(View view) {
        Intent intent=new Intent(TermList.this, TermDetails.class);
        startActivity(intent);
    }

    public void goToAssessments(MenuItem item) {
        Intent intent=new Intent(TermList.this, AssessmentList.class);
        startActivity(intent);
    }
    public void goToCourses(MenuItem item) {
        Intent intent=new Intent(TermList.this, CourseList.class);
        startActivity(intent);
    }

}