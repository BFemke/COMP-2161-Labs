package emke.comp2161.tictactoeapp;

import androidx.appcompat.app.AlertDialog;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView option_list;
    private OptionAdapter adapter;
    private List<String> options;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creates RecyclerView of the three options
        option_list = findViewById(R.id.options_list);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        option_list.setLayoutManager(layoutManager);
        options = Arrays.asList(getResources().getStringArray(R.array.options));
        option_list.setHasFixedSize(true);
        adapter = new OptionAdapter(this, options);
        option_list.setAdapter(adapter);
    }

    /*
    Purpose: Opens dialog to ask user how many players are playing
     */
    public void openNumberDialog(){
        PlayerNumberDialog dialog = new PlayerNumberDialog();
        dialog.show(getSupportFragmentManager(), "number dialog");
    }

    /*
    Purpose: Gets called when one player is selected. Opens new dialog with spinner of all stored
            names and gets user to select their name. Then calls function to start activity.
     */
    public void openOnePlayerNameDialog(){

        //Creates alert to get player name
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.layout_dialog_1selection, null);
        Spinner spinner = (Spinner) view.findViewById(R.id.nameSelector);
        Button selectBtn = (Button) view.findViewById(R.id.selectBtn);

        //Grabs array of Players out of internal storage
        SharedPreferences sharedPreferences;
        Gson gson = new Gson();
        sharedPreferences = getSharedPreferences("standings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = sharedPreferences.getString("list", null);
        String [] names = new String[0];

        //Executes if json does contain content
        if(!(json == null)){
            //Puts all names into names string array
            Type type = new TypeToken<ArrayList<Player>>() {}.getType();
            players = gson.fromJson(json,type);
            names = new String[players.size()];
            names[0] = "Select a name..";
            for(int i = 1; i< players.size();i++){
                names[i] = players.get(i).getName();
            }
        }
        //Executes if json does not contain content
        else {
            names = new String[] {"No names entered.."};
        }

        //Sets up spinner to show names array for selection
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Sets dialog to be shown
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        //click listener for select button
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Checks if the user has selected a name
                if(spinner.getSelectedItem().toString().equals("Select a name..")){
                    Toast.makeText(getApplicationContext(), "Please select a name..", Toast.LENGTH_SHORT).show();
                }
                //if name is selected
                else{
                    //stores player names
                    String player1 = spinner.getSelectedItem().toString();
                    String player2 = "Computer";

                    //calls startGame function with player names and gamemode
                    startGame(player1, player2, true);
                    dialog.cancel();
                }
            }
        });

    }

     /*
    Purpose: Gets called when one player is selected. Opens new dialog with spinner of all stored
            names and gets user to select their name. Then calls function to start activity.
    */
    public void openTwoPlayerNameDialog(){

        //Creates alert to get player name
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.layout_dialog_2selection, null);
        Spinner spinner = (Spinner) view.findViewById(R.id.nameSelector);
        Spinner spinner2 = (Spinner) view.findViewById(R.id.nameSelector2);
        Button selectBtn = (Button) view.findViewById(R.id.selectBtn);
        String player1;
        String player2;

        //Grabs array of Players out of internal storage
        SharedPreferences sharedPreferences;
        Gson gson = new Gson();
        sharedPreferences = getSharedPreferences("standings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = sharedPreferences.getString("list", null);
        String [] names = new String[0];

        //Executes if json does contain content
        if(!(json == null)){
            //Puts all names into names string array
            Type type = new TypeToken<ArrayList<Player>>() {}.getType();
            players = gson.fromJson(json,type);
            names = new String[players.size()];
            names[0] = "Select a name..";
            for(int i = 1; i < players.size();i++){
                names[i] = players.get(i).getName();
            }
        }
        //Executes if json does not contain content
        else {
            names = new String[] {"No names entered.."};
        }

        //Sets up spinner and spinner2 to show names array for selection
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        //Sets dialog to be shown
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();

        //click listener for select button
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Checks if the player 1 has selected a name
                if(spinner.getSelectedItem().toString().equals("Select a name..")){
                    Toast.makeText(getApplicationContext(), "Player 1 select your name..", Toast.LENGTH_SHORT).show();
                }
                //Checks if the player 2 has selected a name
                else if(spinner2.getSelectedItem().toString().equals("Select a name..")){
                    Toast.makeText(getApplicationContext(), "Player 2 select your name..", Toast.LENGTH_SHORT).show();
                }
                //Checks if player 1 and 2 chose the same name
                else if(spinner.getSelectedItem().toString().equals(spinner2.getSelectedItem().toString())){
                    Toast.makeText(getApplicationContext(), "You must have different names..", Toast.LENGTH_SHORT).show();
                }
                //If both players chose different names
                else{
                    String player1 = spinner.getSelectedItem().toString();
                    String player2 = spinner2.getSelectedItem().toString();

                    //calls startGame function with player names and gamemode
                    startGame(player1, player2, false);
                    dialog.cancel();
                }
            }
        });
    }

    /*
    String p1: name of player 1
    String p2: name of player 2
    boolean ai: true is Computer will be playing
    Purpose: Starts the GameActivity with the info it needs to set up the game and run it
     */
    private void startGame(String p1, String p2, boolean ai){

        //Creates intent and adds included extras
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("player1", p1);
        intent.putExtra("player2", p2);
        intent.putExtra("AI", ai);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Starts new GameActivity
        getApplicationContext().startActivity(intent);
    }

}