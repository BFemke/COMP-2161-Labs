package emke.comp2161.tictactoeapp;

//Player object to structure standings records
public class Player {
    private String name;
    private int score;

    //constructor
    public Player(String name, int score){
        this.name = name;
        this.score = score;
    }

    //returns player score
    public int getScore() {
        return score;
    }

    //returns player name
    public String getName() {
        return name;
    }

    //increments player's score
    public void incrementScore(){
        score++;
    }
}
