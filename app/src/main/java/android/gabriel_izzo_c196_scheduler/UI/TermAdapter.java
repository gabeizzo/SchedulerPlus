package android.gabriel_izzo_c196_scheduler.UI;

import android.content.Context;
import android.content.Intent;
import android.gabriel_izzo_c196_scheduler.Entity.Term;
import android.gabriel_izzo_c196_scheduler.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    public class TermViewHolder extends RecyclerView.ViewHolder{
        private final TextView termItemView;
        private final TextView termDateView;

        private TermViewHolder (View itemView){
            super(itemView);
            termItemView=itemView.findViewById(R.id.textView_term_name);
            termDateView=itemView.findViewById(R.id.textView_term_date);
            itemView.setOnClickListener(view -> {
              int position=getAdapterPosition();
              final Term current=mTerms.get(position);
              Intent intent=new Intent(context, TermDetails.class);
              intent.putExtra("id", current.getTermID());
              intent.putExtra("title", current.getTermTitle());
              intent.putExtra("start", current.getTermStart());
              intent.putExtra("end", current.getTermEnd());
              context.startActivity(intent);
            });
        }
    }

    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;
    public TermAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.term_list_item, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if(mTerms!=null){
            Term current = mTerms.get(position);
            String name = current.getTermTitle();
            String start = current.getTermStart();
            String end = current.getTermEnd();
            holder.termItemView.setText(name);
            holder.termDateView.setText(new StringBuilder().append("(").append(start).append(" - ").append(end).append(")").toString());

        }
        else{
            holder.termItemView.setText("No Terms Currently");
        }
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
