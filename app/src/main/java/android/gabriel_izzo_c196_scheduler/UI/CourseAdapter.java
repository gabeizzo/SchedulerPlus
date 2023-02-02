package android.gabriel_izzo_c196_scheduler.UI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Entity.Course;
import android.gabriel_izzo_c196_scheduler.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    public static class CourseViewHolder extends RecyclerView.ViewHolder{
        private final TextView courseNameView;
        private final TextView courseDateView;
        private final ConstraintLayout courseItemLayout;

        private CourseViewHolder (View itemView){
            super(itemView);
            courseNameView =itemView.findViewById(R.id.textView_course_name);
            courseDateView = itemView.findViewById(R.id.textView_course_date);
            courseItemLayout = itemView.findViewById(R.id.course_item_layout);
        }
    }
    private List<Course> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;
    private int selectedPosition = -1;

    public CourseAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.course_list_item, parent, false);
        return new CourseViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        final Course current = mCourses.get(position);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

        if(mCourses!=null){
            String title = current.getCourseTitle();
            Date start = current.getCourseStart();
            Date end = current.getCourseEnd();
            holder.courseDateView.setText("(" + dateFormatter.format(start) + " - " + dateFormatter.format(end) + ")");
            holder.courseNameView.setText(title);
        }
        else {
            holder.courseNameView.setText("No Courses Currently");
        }

        if (selectedPosition == position) {
            holder.itemView.setSelected(true); //using selector drawable
            holder.courseNameView.setTextColor(ContextCompat.getColor(holder.courseNameView.getContext(),R.color.white));
            holder.courseItemLayout.setBackgroundColor(ContextCompat.getColor(holder.courseItemLayout.getContext(),R.color.triton_blue));
            holder.courseDateView.setTextColor(ContextCompat.getColor(holder.courseDateView.getContext(),R.color.white));
        }
        else {
            holder.itemView.setSelected(false);
            holder.courseNameView.setTextColor(ContextCompat.getColor(holder.courseNameView.getContext(),R.color.black));
            holder.courseDateView.setTextColor(ContextCompat.getColor(holder.courseDateView.getContext(),R.color.black));
        }
        holder.itemView.setOnClickListener(v -> {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition);
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(selectedPosition);
            String courseStart = dateFormatter.format(current.getCourseStart());
            String courseEnd = dateFormatter.format(current.getCourseEnd());

            Intent intent=new Intent(context, CourseDetails.class);
            intent.putExtra("id", current.getCourseID());
            intent.putExtra("title", current.getCourseTitle());
            intent.putExtra("start", courseStart);
            intent.putExtra("end", courseEnd);
            intent.putExtra("status", current.getCourseStatus());
            intent.putExtra("instructor", current.getInstructorName());
            intent.putExtra("phone", current.getInstructorPhone());
            intent.putExtra("email", current.getInstructorEmail());
            intent.putExtra("notes", current.getCourseNote());
            intent.putExtra("termID", current.getTermID());

            context.startActivity(intent);
        });

    }
    @SuppressLint("NotifyDataSetChanged")
    public void setCourses(List<Course> courses){
        mCourses=courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if(mCourses!=null){
            return mCourses.size();
        }
        else return 0;
    }

}
