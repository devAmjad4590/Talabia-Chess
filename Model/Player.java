package Model;

/**
 * Represents a player in a game.
 */
public class Player {
    private boolean isTurn = false;
    private boolean yellow;
    private int noOfWins = 0;
    private int noOfLosses = 0;
    private int noOfDraws = 0;
    private Piece pickedPiece; // we might need it for GUI

    /**
     * Initializes a new player with the specified color.
     *
     * @param yellow true if the player is assigned the yellow color, false otherwise.
     */
    public Player(boolean yellow){
        this.yellow = yellow;
    }

    /**
     * Gets whether it's the player's turn.
     *
     * @return true if it's the player's turn, false otherwise.
     * // by amgad elrashid gurashi eltayeb
     */
    public boolean getTurn() { 
        return isTurn;
    }

    /**
     * Sets the player's turn status.
     *
     * @param isTurn true if it's the player's turn, false otherwise.
     * // by amgad elrashid gurashi eltayeb (ill document the comment later)
     */
    public void setTurn(boolean isTurn) {
        this.isTurn = isTurn;
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
     * Picks a piece 
     *
     * @param piece the piece to be moved.
     * @param initial the initial tile of the piece.
     * @param destination the destination tile for the piece.
     */
    public Piece getChosenPiece() {
        return pickedPiece;
        
    }

    public void setPickedPiece(Tile tile) {
        this.pickedPiece = tile.getPiece();
    }



    /**
     * Gets the player's score as a formatted string.
     *
     * @return the player's score in the format "Score: wins-draws-losses".
     */
    public String getScore() {
        return "Score: " + noOfWins + "- " + noOfDraws + "- " + noOfLosses;
    }

    // get Player's number of wins
    public int getNoOfWins() {
        return noOfWins;
    }

    // get Player's number of losses
    public int getNoOfLosses() {
        return noOfLosses;
    }

    // get Player's number of draws
    public int getNoOfDraws() {
        return noOfDraws;
    }

    public String toString(){
        return "Player" + (yellow ? "Yellow" : "Blue");
    }
}
