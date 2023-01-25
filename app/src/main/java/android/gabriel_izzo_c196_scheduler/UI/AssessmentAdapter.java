package android.gabriel_izzo_c196_scheduler.UI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Entity.Assessment;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {
        public class AssessmentViewHolder extends RecyclerView.ViewHolder{
            private final TextView assessmentNameView;
            private final TextView assessmentDateView;
            private final ConstraintLayout assessmentItemLayout;

            private AssessmentViewHolder (View itemView){
                super(itemView);
                assessmentNameView =itemView.findViewById(R.id.textView_assessment_title);
                assessmentDateView = itemView.findViewById(R.id.textView_assessment_date);
                assessmentItemLayout = itemView.findViewById(R.id.assessment_item_layout);
               /* itemView.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view){
                        int position=getAdapterPosition();
                        final Assessment current=mAssessments.get(position);

                        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
                        String startDate = dateFormatter.format(current.getAssessmentStartDate());
                        String endDate = dateFormatter.format(current.getAssessmentEndDate());

                        Intent intent=new Intent(context, AssessmentDetails.class);
                        intent.putExtra("id", current.getAssessmentID());
                        intent.putExtra("title", current.getAssessmentTitle());
                        intent.putExtra("start", startDate);
                        intent.putExtra("end", endDate);
                        intent.putExtra("type", current.getAssessmentType());
                        intent.putExtra("courseID", String.valueOf(current.getCourseID()));
                        context.startActivity(intent);

                    }
                });*/
            }
        }
        private List<Assessment> mAssessments;
        private final Context context;
        private final LayoutInflater mInflater;
        private int selectedPosition = -1;

        final Calendar startCal = Calendar.getInstance();
        final Calendar endCal = Calendar.getInstance();

        public AssessmentAdapter(Context context){
            mInflater=LayoutInflater.from(context);
            this.context=context;
        }
        @NonNull
        @Override
        public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView=mInflater.inflate(R.layout.assessment_list_item, parent, false);
            return new AssessmentViewHolder(itemView);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
            final Assessment current = mAssessments.get(position);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

            if(mAssessments!=null){
                String title = current.getAssessmentTitle();
                Date start = current.getAssessmentStartDate();
                Date end = current.getAssessmentEndDate();
                String type = current.getAssessmentType();
                holder.assessmentDateView.setText(new StringBuilder().append(type).append("\n(").append(dateFormatter.format(start)).append(" - ").append(dateFormatter.format(end)).append(")").toString());
                holder.assessmentNameView.setText(title);
            }
            else {
                holder.assessmentNameView.setText("No Assessments Currently");
            }

            if (selectedPosition == position) {
                holder.itemView.setSelected(true); //using selector drawable
                holder.assessmentNameView.setTextColor(ContextCompat.getColor(holder.assessmentNameView.getContext(),R.color.white));
                holder.assessmentItemLayout.setBackgroundColor(ContextCompat.getColor(holder.assessmentItemLayout.getContext(),R.color.triton_blue));
                holder.assessmentDateView.setTextColor(ContextCompat.getColor(holder.assessmentDateView.getContext(),R.color.white));

            } else {
                holder.itemView.setSelected(false);
                holder.assessmentNameView.setTextColor(ContextCompat.getColor(holder.assessmentNameView.getContext(),R.color.black));
                holder.assessmentDateView.setTextColor(ContextCompat.getColor(holder.assessmentDateView.getContext(),R.color.black));
            }

            holder.itemView.setOnClickListener(v -> {
                if (selectedPosition >= 0)
                    notifyItemChanged(selectedPosition);
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(selectedPosition);
                String assessmentStart = dateFormatter.format(current.getAssessmentStartDate());
                String assessmentEnd = dateFormatter.format(current.getAssessmentEndDate());

                Intent intent=new Intent(context, AssessmentDetails.class);
                intent.putExtra("id", current.getAssessmentID());
                intent.putExtra("title", current.getAssessmentTitle());
                intent.putExtra("start", assessmentStart);
                intent.putExtra("end", assessmentEnd);
                intent.putExtra("type", current.getAssessmentType());
                intent.putExtra("courseID", current.getCourseID());
                context.startActivity(intent);
            });

        }
        @SuppressLint("NotifyDataSetChanged")
        public void setAssessments(List<Assessment> assessments){
            mAssessments=assessments;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount(){
            if(mAssessments!=null){
                return mAssessments.size();
            }
            else return 0;
        }

    }
