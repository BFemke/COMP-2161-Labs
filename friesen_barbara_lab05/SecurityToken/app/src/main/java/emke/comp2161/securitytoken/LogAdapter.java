package emke.comp2161.securitytoken;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> time_log;

    //Constructor for LogAdapter
    public LogAdapter(Context context, ArrayList<String> time_log){
        this.context = context;
        this.time_log = time_log;
    }


    //Inflates the view using our defined layout "log_row"
    @NonNull
    @Override
    public LogAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.log_row, parent, false);
        return new LogAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LogAdapter.MyViewHolder holder, int position) {
        holder.log.setText(time_log.get(position));
    }

    @Override
    public int getItemCount() {
        return time_log.size();
    }

    //Creates the view based on the contents of the time_log Array List
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView log;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            log = itemView.findViewById(R.id.timeLog);
        }
    }

    public void addLog(String time){
        time_log.add(time);
    }
}
