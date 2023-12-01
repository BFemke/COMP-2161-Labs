package emke.comp2161.tictactoeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.MyViewHolder> {
    private ArrayList<Player> players;

    //Constructor for OptionAdapter
    public StandingsAdapter( ArrayList<Player> players){
        this.players = players;
    }

    //Inflates the view using our defined layout "option_row"
    @NonNull
    @Override
    public StandingsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.standings_row, parent, false);
        return new StandingsAdapter.MyViewHolder(v);
    }

    //Sets recycler item values
    @Override
    public void onBindViewHolder(@NonNull StandingsAdapter.MyViewHolder holder, int position) {
        Player player = players.get(position);
        holder.name.setText(player.getName());
        holder.score.setText(String.valueOf(player.getScore()));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    //Creates the view based on the contents of the option_list Array List
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView score;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.record_name);
            score = itemView.findViewById(R.id.wins);
        }
    }
}
