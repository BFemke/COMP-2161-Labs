package emke.comp2161.tictactoeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.MyViewHolder> {
    Context context;
    private List<String> option_list;

    //Constructor for OptionAdapter
    public OptionAdapter(Context context, List<String> option_list){
        this.context = context;
        this.option_list = option_list;
    }

    //Inflates the view using our defined layout "option_row"
    @NonNull
    @Override
    public OptionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.option_row, parent, false);
        return new OptionAdapter.MyViewHolder(v);
    }

    //Sets up the items in the options array, and sets recyclerView on click listener
    @Override
    public void onBindViewHolder(@NonNull OptionAdapter.MyViewHolder holder, int position) {
        holder.option.setText(option_list.get(position));
        holder.option.setOnClickListener(v ->{
            //If Start Game was selected, opens dialog for number of players in MainActivity
            if(position == 0){
                if(context instanceof MainActivity){
                    ((MainActivity)context).openNumberDialog();
                }
            }
            //If Enter Names was selected, starts NameEntryActivity
            else if(position == 1){
                Intent intent = new Intent(context, NameEntryActivity.class);
                context.startActivity(intent);

            }
            //If Standings was selected, starts StandingsActivity
            else if(position == 2){
                Intent intent = new Intent(context, StandingsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return option_list.size();
    }

    //Creates the view based on the contents of the option_list Array List
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView option;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            option = itemView.findViewById(R.id.option);
        }
    }
}
