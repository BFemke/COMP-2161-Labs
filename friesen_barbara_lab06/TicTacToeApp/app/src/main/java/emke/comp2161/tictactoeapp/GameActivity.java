package emke.comp2161.tictactoeapp;

import static emke.comp2161.tictactoeapp.R.drawable.o_style;
import static emke.comp2161.tictactoeapp.R.drawable.x_style;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private TextView turnIndicator;
    private int playerTurn = 1;
    private boolean AI;
    private Button backBtn;
    private Button restartBtn;
    private String player1;
    private String player2;
    private String board[][] = new String[][] {{"", "", ""}, {"", "", ""}, {"", "", ""}};
    private ImageButton cell1;
    private ImageButton cell2;
    private ImageButton cell3;
    private ImageButton cell4;
    private ImageButton cell5;
    private ImageButton cell6;
    private ImageButton cell7;
    private ImageButton cell8;
    private ImageButton cell9;
    private ArrayList<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        turnIndicator = (TextView) findViewById(R.id.playerTurn);
        backBtn = (Button) findViewById(R.id.backBtn);
        restartBtn = (Button) findViewById(R.id.restartBtn);
        cell1 = (ImageButton) findViewById(R.id.cellOne);
        cell2 = (ImageButton) findViewById(R.id.cellTwo);
        cell3 = (ImageButton) findViewById(R.id.cellThree);
        cell4 = (ImageButton) findViewById(R.id.cellFour);
        cell5 = (ImageButton) findViewById(R.id.cellFive);
        cell6 = (ImageButton) findViewById(R.id.cellSix);
        cell7 = (ImageButton) findViewById(R.id.cellSeven);
        cell8 = (ImageButton) findViewById(R.id.cellEight);
        cell9 = (ImageButton) findViewById(R.id.cellNine);

        sharedPreferences = getSharedPreferences("standings", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);

        //Grabs array of players from gson to allow score incrementing
        if(!(json == null)){
            Type type = new TypeToken<ArrayList<Player>>() {}.getType();
            players = gson.fromJson(json,type);
        }

        //Sets initial values gotten from intent
        Intent intent = getIntent();
        player1 = intent.getStringExtra("player1");
        player2 = intent.getStringExtra("player2");
        AI = intent.getBooleanExtra("AI", false);

        //Let's user know it is player 1;s turn
        turnIndicator.setText(player1+" it is your turn!");

        //listeners for each cell of the board
        cell1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Executes if valid cell selected
                if(markCell(0, 0)){

                    //sets appropriate image to cell depending on player number
                    if(playerTurn == 1)
                        cell1.setImageDrawable(getDrawable(o_style));
                    else
                        cell1.setImageDrawable(getDrawable(R.drawable.x_style));

                    checkWin();     //calls function to check win conditions
                }
                else
                    //Notifies user of invalid move
                    Toast.makeText(GameActivity.this, "Invalid move..", Toast.LENGTH_SHORT).show();
            }
        });
        cell2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Executes if valid cell selected
                if(markCell(0, 1)){

                    //sets appropriate image to cell depending on player number
                    if(playerTurn == 1)
                        cell2.setImageDrawable(getDrawable(o_style));
                    else
                        cell2.setImageDrawable(getDrawable(R.drawable.x_style));

                    checkWin();     //calls function to check win conditions
                }
                else
                    //Notifies user of invalid move
                    Toast.makeText(GameActivity.this, "Invalid move..", Toast.LENGTH_SHORT).show();
            }
        });
        cell3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Executes if valid cell selected
                if(markCell(0, 2)){

                    //sets appropriate image to cell depending on player number
                    if(playerTurn == 1)
                        cell3.setImageDrawable(getDrawable(o_style));
                    else
                        cell3.setImageDrawable(getDrawable(R.drawable.x_style));

                    checkWin();     //calls function to check win conditions
                }
                else
                    //Notifies user of invalid move
                    Toast.makeText(GameActivity.this, "Invalid move..", Toast.LENGTH_SHORT).show();
            }
        });
        cell4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Executes if valid cell selected
                if(markCell(1, 0)){

                    //sets appropriate image to cell depending on player number
                    if(playerTurn == 1)
                        cell4.setImageDrawable(getDrawable(o_style));
                    else
                        cell4.setImageDrawable(getDrawable(R.drawable.x_style));

                    checkWin();     //calls function to check win conditions
                }
                else
                    //Notifies user of invalid move
                    Toast.makeText(GameActivity.this, "Invalid move..", Toast.LENGTH_SHORT).show();
            }
        });
        cell5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Executes if valid cell selected
                if(markCell(1, 1)){

                    //sets appropriate image to cell depending on player number
                    if(playerTurn == 1)
                        cell5.setImageDrawable(getDrawable(o_style));
                    else
                        cell5.setImageDrawable(getDrawable(R.drawable.x_style));

                    checkWin();     //calls function to check win conditions
                }
                else
                    //Notifies user of invalid move
                    Toast.makeText(GameActivity.this, "Invalid move..", Toast.LENGTH_SHORT).show();
            }
        });
        cell6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Executes if valid cell selected
                if(markCell(1, 2)){

                    //sets appropriate image to cell depending on player number
                    if(playerTurn == 1)
                        cell6.setImageDrawable(getDrawable(o_style));
                    else
                        cell6.setImageDrawable(getDrawable(R.drawable.x_style));

                    checkWin();     //calls function to check win conditions
                }
                else
                    //Notifies user of invalid move
                    Toast.makeText(GameActivity.this, "Invalid move..", Toast.LENGTH_SHORT).show();
            }
        });
        cell7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Executes if valid cell selected
                if(markCell(2, 0)){

                    //sets appropriate image to cell depending on player number
                    if(playerTurn == 1)
                        cell7.setImageDrawable(getDrawable(o_style));
                    else
                        cell7.setImageDrawable(getDrawable(R.drawable.x_style));

                    checkWin();     //calls function to check win conditions
                }
                else
                    //Notifies user of invalid move
                    Toast.makeText(GameActivity.this, "Invalid move..", Toast.LENGTH_SHORT).show();
            }
        });
        cell8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Executes if valid cell selected
                if(markCell(2, 1)){

                    //sets appropriate image to cell depending on player number
                    if(playerTurn == 1)
                        cell8.setImageDrawable(getDrawable(o_style));
                    else
                        cell8.setImageDrawable(getDrawable(R.drawable.x_style));

                    checkWin();     //calls function to check win conditions
                }
                else
                    //Notifies user of invalid move
                    Toast.makeText(GameActivity.this, "Invalid move..", Toast.LENGTH_SHORT).show();
            }
        });
        cell9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Executes if valid cell selected
                if(markCell(2, 2)){

                    //sets appropriate image to cell depending on player number
                    if(playerTurn == 1)
                        cell9.setImageDrawable(getDrawable(o_style));
                    else
                        cell9.setImageDrawable(getDrawable(R.drawable.x_style));

                    checkWin();     //calls function to check win conditions
                }
                else
                    //Notifies user of invalid move
                    Toast.makeText(GameActivity.this, "Invalid move..", Toast.LENGTH_SHORT).show();
            }
        });

        //click listener for the back button which brings you to main menu
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Creates alert dialog to verify intention of user
                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setTitle("Exit")
                    //Warns progress will be erased
                    .setMessage("Are you sure you want to leave?\nAll your progress will be lost..")

                    //Sets yes button which closes activity and returns to main activity
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id){
                            finish();
                        }
                    })

                    //Sets no button which removes dialog and continues game
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show();
            }
        });
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Creates alert dialog to verify intention of user
                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setTitle("Restart")
                        //Warns progress will be erased
                        .setMessage("Are you sure you want to restart?\nAll your progress will be lost..")

                        //Sets yes button which closes the activity and restarts it
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id){
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })

                        //Sets no button which closes dialog and returns to game
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            }
        });
    }

    //Saves instance state in event of turning the screen
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray("row1",  board[0]);
        outState.putStringArray("row2",  board[1]);
        outState.putStringArray("row3",  board[2]);
    }

    //Restores instance state after turning the screen
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String row1[] = savedInstanceState.getStringArray("row1");
        String row2[] = savedInstanceState.getStringArray("row2");
        String row3[] = savedInstanceState.getStringArray("row3");

        for(int i = 0; i < 3;i++){
            board[0][i] = row1[i];
            board[1][i] = row2[i];
            board[2][i] = row3[i];
        }

        for(int x = 0; x < 3;x++){
            for(int y = 0; y < 3;y++){
                if(!board[y][x].equals("")){
                    setCellImage(x, y);
                }
            }
        }
    }

    //Sets tic tac toe board after instance state is restored
    private void setCellImage(int x, int y) {
        //Nested switch statements corresponding to x and y coordinates to mark appropriate cell
        Drawable img;
        if(board[y][x].equals("x")){
            img = getDrawable(x_style);
        }
        else {
            img = getDrawable(o_style);
        }
        switch(y) {
            case 0:
                switch (x){
                    case 0:
                        cell1.setImageDrawable(img);
                        break;
                    case 1:
                        cell2.setImageDrawable(img);
                        break;
                    case 2:
                        cell3.setImageDrawable(img);
                        break;
                }
                break;
            case 1:
                switch (x){
                    case 0:
                        cell4.setImageDrawable(img);
                        break;
                    case 1:
                        cell5.setImageDrawable(img);
                        break;
                    case 2:
                        cell6.setImageDrawable(img);
                        break;
                }
                break;
            case 2:
                switch (x){
                    case 0:
                        cell7.setImageDrawable(img);
                        break;
                    case 1:
                        cell8.setImageDrawable(img);
                        break;
                    case 2:
                        cell9.setImageDrawable(img);
                        break;
                }
                break;
        }
    }

    /*
            Purpose: To check every possible win condition in the tic tac toe game including a tie.
                    If win or tie conditions are met it calls winAlert function
                    If the game is not over it changes the turn indicator message.
                    If it is the computer's turn it calls the computerMove function.
             */
    private void checkWin() {

        //Tests Columns for winning Line
        for (int x = 0; x < 3; x++) {
            String test = board[0][x];
            if (test.equals("")) continue;
            if (test.equals(board[1][x]) && test.equals(board[2][x])) {
                winAlert(1);
                return;
            }
        }


        //Tests rows for winning line
        for(int y = 0; y < 3 ;y++){
            String test = board[y][0];
            if(test.equals("")) continue;
            if (test.equals(board[y][1]) && test.equals(board[y][2])) {
                winAlert(1);
                return;
            }
        }

        //Checks for right diagonal win
        String test = board[0][0];
        if(!test.equals("")){
            if(test.equals(board[1][1]) && test.equals(board[2][2])) {
                winAlert(1);
                return;
            }
        }

        //Checks for left diagonal win
        test = board[0][2];
        if(!test.equals("")){
            if(test.equals(board[1][1]) && test.equals(board[2][0])) {
                winAlert(1);
                return;
            }
        }

        //Checks to see if there are any more moves left to play
        for(int x = 0; x < 3;x++){
            for(int y = 0; y < 3;y++){
                if(board[y][x].equals("")){

                    //Sets turn indicator
                    if(playerTurn == 1)
                        turnIndicator.setText(player1+" it is your turn!");
                    else if(playerTurn == 2 && !AI)
                        turnIndicator.setText(player2+" it is your turn!");
                    else{
                        turnIndicator.setText("Computer's turn!");
                        computerMove();     //calls for computer's move
                    }
                    return;
                }
            }
        }

        //Executes only if a tie occurs
        winAlert(2);
        return;
    }

    /*
    Purpose: Randomly select a cell for the computer's move and executes it
     */
    private void computerMove() {
        Random random = new Random();
        int x;
        int y;
        boolean valid;

        //Executes until the computer finds a valid move
        do{
            x = random.nextInt(3);
            y = random.nextInt(3);
            valid = markCell(y, x);         //returns true if valid move has been found
        }while(valid == false);

        //Nested switch statements corresponding to x and y coordinates to mark appropriate cell
        switch(y) {
            case 0:
                switch (x){
                    case 0:
                        cell1.setImageDrawable(getDrawable(o_style));
                        break;
                    case 1:
                        cell2.setImageDrawable(getDrawable(o_style));
                        break;
                    case 2:
                        cell3.setImageDrawable(getDrawable(o_style));
                        break;
                }
                break;
            case 1:
                switch (x){
                    case 0:
                        cell4.setImageDrawable(getDrawable(o_style));
                        break;
                    case 1:
                        cell5.setImageDrawable(getDrawable(o_style));
                        break;
                    case 2:
                        cell6.setImageDrawable(getDrawable(o_style));
                        break;
                }
                break;
            case 2:
                switch (x){
                    case 0:
                        cell7.setImageDrawable(getDrawable(o_style));
                        break;
                    case 1:
                        cell8.setImageDrawable(getDrawable(o_style));
                        break;
                    case 2:
                        cell9.setImageDrawable(getDrawable(o_style));
                        break;
                }
                break;
        }
        checkWin();     //calls checkWin function
        return;
    }

    /*
    int i: number of winners
    Purpose: Create an alert dialog to let users know when someone has won, who it was that one,
            and ask if they want to play again or return to main menu. Calls incrementPlayerWin
            to keep track of scores.
     */
    private void winAlert(int i) {
        String message;
        String title;

        //Handles case of 1 winner
        if(i == 1){
            title = "You won!!";

            //If player 2 won
            if(playerTurn == 1 && !AI){
                message = "Congratulations "+player2+" you won!";
                incrementPlayerWin(player2);
            }

            //If player 1 won
            else if(playerTurn == 2){
                message = "Congratulations "+player1+" you won!";
                incrementPlayerWin(player1);
            }

            //If computer won
            else {
                title = "Computer won";
                message = "The computer won..";
                incrementPlayerWin(player2);
            }

        }
        //If it is a tie (two people won)
        else {
            title = "It's a tie!!";
            message = "You have run out of moves!";
            incrementPlayerWin(player1);
            incrementPlayerWin(player2);
        }

        //Creates alert to display win message
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle(title)
            .setMessage(message)

            //Sets rematch button which closes the activity and restarts it with same players
            .setPositiveButton("Rematch", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id){
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            })

            //Sets main menu button which closes activity and brings user back to the main activity
            .setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            })
            .show();
    }

    /*
    String player: name of player to increment score of
    Purpose: To increment score of appropriate player to store in internal storage, to allow for
            tracking of standings.
     */
    private void incrementPlayerWin(String player) {

        //Loops through Player array
        for(Player p: players){
            //If player name matches the winning player
            if(p.getName().equals(player)){
                p.incrementScore();     //Calls the Player incrementScore function


                //Updates the internal storage with new value
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String json = gson.toJson(players);
                editor.putString("list", json);
                editor.commit();
            }
        }
    }

    /*
    int y: y coordinate of move
    int x: x coordinate of move
    Purpose: Check if move is valid and if so marks in representational string array. Returns
            boolean to indicate if valid or not.
     */
    private boolean markCell(int y, int x) {

        //checks if valid move
        if(board[y][x].equals("")){

            //marks move as player 1
            if(playerTurn == 1){
                board[y][x] = "x";
                playerTurn = 2;
            }

            //marks move as player 2
            else {
                board[y][x] = "o";
                playerTurn = 1;
            }
            return true;
        }
        return false;
    }
}