package Model;

/**
 * Represents a player in a game.
 * class implemented by Maher
 */
public class Player {
    private boolean yellow;
    private int noOfWins = 0;
    private int noOfLosses = 0;
  

    /**
     * Initializes a new player with the specified color.
     *
     * @param yellow true if the player is assigned the yellow color, false otherwise.
     */
    public Player(boolean yellow){
        this.yellow = yellow;
    }

    public void incrementWin() {
        this.noOfWins++;
    }

    public void incrementLose() {
        this.noOfLosses++;
    }

    public void resetScore(){
        this.noOfWins = 0;
        this.noOfLosses = 0;
    }

    /**
     * Gets the color of the player's pieces.
     *
     * @return true if the player's pieces are yellow, false otherwise.
     */
    public boolean isYellow() {
        return yellow;
    }

    /**
     * Gets the player's score as a formatted string.
     *
     * @return the player's score in the format "Score: wins-draws-losses".
     */
    public String getScore() {
        return "(" + noOfWins + " - " + noOfLosses + ")";
    }

    // get Player's number of wins
    public int getNoOfWins() {
        return noOfWins;
    }

    // get Player's number of losses
    public int getNoOfLosses() {
        return noOfLosses;
    }

    // get player number, 1 is yellow, 2 is blue
    public String toString(){
        if(yellow){
            return "Player 1";
        }else{
            return "Player 2";
        }
    }

   
}
