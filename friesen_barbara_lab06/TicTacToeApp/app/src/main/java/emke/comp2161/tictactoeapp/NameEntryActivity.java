package emke.comp2161.tictactoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NameEntryActivity extends AppCompatActivity {

    private EditText enter_player;
    private String playerName;
    private Button saveBtn;
    private Button backBtn;
    private ArrayList<Player> players;


    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_entry);

        enter_player = (EditText) findViewById(R.id.player_name);
        saveBtn = (Button) findViewById(R.id.save_name);
        backBtn = (Button) findViewById(R.id.back);

        //Opens internal storage
        Gson gson = new Gson();
        sharedPreferences = getSharedPreferences("standings", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        String json = sharedPreferences.getString("list", null);

        //Checks if list of players exists, if so holds the list
        if(!(json == null)){
            Type type = new TypeToken<ArrayList<Player>>() {}.getType();
            players = gson.fromJson(json,type);
        }
        //If no list of players exists it creates a new one, with computer added in
        else{
            players = new ArrayList<>();
            players.add(new Player("Computer", 0));
        }


        Intent intent = getIntent();

        //Creates onClick listener for save button
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerName = enter_player.getText().toString();
                boolean duplicate = false;

                //Prevents duplicate names from being entered
                for(Player p : players) {
                    if (p.getName().equals(playerName)){
                        duplicate = true;
                    }
                }
                if(duplicate){
                    Toast.makeText(getApplicationContext(), "Name already exists..", Toast.LENGTH_SHORT).show();
                }

                //Adds name to list and save it to shared preferences
                else if(!playerName.isEmpty()) {
                    players.add(new Player(playerName, 0));
                    String json = gson.toJson(players);
                    editor.putString("list", json);
                    editor.commit();
                    enter_player.setText("");

                    //Notifies user of saved name
                    Toast.makeText(getApplicationContext(), "Player name has been saved!", Toast.LENGTH_SHORT).show();
                }
                //Executes if user tries to save an empty line
                else{
                    Toast.makeText(getApplicationContext(), "Please enter a name...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Sends user back to the main menu
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ends activity and goes back to MainActivity
                finish();
            }
        });
    }

    //Saves instant state for screen orientation purposes so typed name is not erased
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", enter_player.getText().toString());
    }

    //restores partially typed name if exists
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        enter_player.setText(savedInstanceState.getString("name"));
    }
}