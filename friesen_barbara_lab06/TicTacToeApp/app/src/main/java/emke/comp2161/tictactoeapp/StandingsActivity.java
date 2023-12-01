package emke.comp2161.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StandingsActivity extends AppCompatActivity {

    private RecyclerView standings;
    private Button clearRecords;
    private Button back;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Player> players;
    private StandingsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);

        standings = (RecyclerView) findViewById(R.id.standings_list);
        clearRecords = (Button) findViewById(R.id.clear_btn);
        back = (Button) findViewById(R.id.back_btn);

        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("standings", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);

        //If json has content, sends it to adapter to be put on screen
        if(!(json == null)){
            Type type = new TypeToken<ArrayList<Player>>() {}.getType();
            players = gson.fromJson(json,type);

            layoutManager = new LinearLayoutManager(this);
            standings.setHasFixedSize(true);
            standings.setLayoutManager(layoutManager);
            adapter = new StandingsAdapter( players);
            standings.setAdapter(adapter);
        }

        //listens for clicks on clear records button
        clearRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences;
                sharedPreferences = getSharedPreferences("standings", Context.MODE_PRIVATE);

                //Clears everything out of the standings shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                layoutManager = new LinearLayoutManager(getApplicationContext());
                standings.setHasFixedSize(true);
                standings.setLayoutManager(layoutManager);
                adapter = new StandingsAdapter(new ArrayList<Player>());
                standings.setAdapter(adapter);
            }
        }
        );

        //Listens for clicks on back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finished activity and goes back to mainActivity
                finish();
            }
        });
    }
}