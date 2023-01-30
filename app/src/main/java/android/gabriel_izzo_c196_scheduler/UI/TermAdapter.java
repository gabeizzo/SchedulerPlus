package android.gabriel_izzo_c196_scheduler.UI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Entity.Course;
import android.gabriel_izzo_c196_scheduler.Entity.Term;
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

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    public class TermViewHolder extends RecyclerView.ViewHolder{
        private final TextView termTitleView;
        private final TextView termDateView;
        private final ConstraintLayout termItemLayout;
        private final RecyclerView allCoursesRecycler;
        private final RecyclerView addedCoursesRecycler;

        private TermViewHolder (View itemView){
            super(itemView);
            termTitleView =itemView.findViewById(R.id.textView_term_name);
            termDateView=itemView.findViewById(R.id.textView_term_date);
            termItemLayout = itemView.findViewById(R.id.term_item_layout);
            allCoursesRecycler = itemView.findViewById(R.id.allCourses);
            addedCoursesRecycler = itemView.findViewById(R.id.addedCourses);
        }
    }

    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;
    private int selectedPosition = -1;

    final Calendar startCal = Calendar.getInstance();
    final Calendar endCal = Calendar.getInstance();

    public TermAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;

    }
    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.term_list_item, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        final Term current=mTerms.get(position);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

        if(mTerms!=null){
            String name = current.getTermTitle();
            Date start = current.getTermStart();
            Date end = current.getTermEnd();
            holder.termTitleView.setText(name);
            holder.termDateView.setText(new StringBuilder().append("(").append(dateFormatter.format(start)).append(" - ").append(dateFormatter.format(end)).append(")").toString());

        }
        else{
            holder.termTitleView.setText("No Terms Currently");
        }
        if (selectedPosition == position) {
            holder.itemView.setSelected(true); //using selector drawable
            holder.termTitleView.setTextColor(ContextCompat.getColor(holder.termTitleView.getContext(),R.color.white));
            holder.termItemLayout.setBackgroundColor(ContextCompat.getColor(holder.termItemLayout.getContext(),R.color.triton_blue));
            holder.termDateView.setTextColor(ContextCompat.getColor(holder.termDateView.getContext(),R.color.white));

        } else {
            holder.itemView.setSelected(false);
            holder.termTitleView.setTextColor(ContextCompat.getColor(holder.termTitleView.getContext(),R.color.black));
            holder.termDateView.setTextColor(ContextCompat.getColor(holder.termDateView.getContext(),R.color.black));
        }

        holder.itemView.setOnClickListener(v -> {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition);
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(selectedPosition);
            String termStart = dateFormatter.format(current.getTermStart());
            String termEnd = dateFormatter.format(current.getTermEnd());
            Intent intent=new Intent(context, TermDetails.class);
            intent.putExtra("id", current.getTermID());
            intent.putExtra("title", current.getTermTitle());
            intent.putExtra("start", termStart);
            intent.putExtra("end", termEnd);
            context.startActivity(intent);
        });
    }

    public void setTerms(List<Term> terms){
        mTerms=terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mTerms!=null) {
            return mTerms.size();
        }
        else return 0;
    }


}
