package android.gabriel_izzo_c196_scheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.R;
import android.os.Bundle;
import android.view.View;

public class CourseList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

    }

    public void goToCourseDetails(View view) {
        Intent intent=new Intent(CourseList.this, CourseDetail.class);
        startActivity(intent);
    }
}