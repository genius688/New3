package Family;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartstore.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter< RecyclerViewAdapter.MyviewHolder> {
 Context context;
 ArrayList<CurrentTaskModel> tasklist;

    public RecyclerViewAdapter(Context context, ArrayList<CurrentTaskModel> tasklist) {
        this.context=context;
        this.tasklist=tasklist;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyviewHolder holder, int position) {
        holder.textView.setText((CharSequence) tasklist.get(position).getTask_name());

    }

    @Override
    public int getItemCount() {
        return this.tasklist.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            textView =itemView.findViewById(R.id.task_title);
        }
    }
}
