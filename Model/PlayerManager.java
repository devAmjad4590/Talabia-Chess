package Model;

/**
 * The `PlayerManager` class manages the players and the turn progression in the
 * game.
 * It is responsible for tracking the current turn and providing access to the
 * current and next players.
 * class implemented by mamoon
 */
public class PlayerManager {
    private Player yellow; // The yellow player
    private Player blue; // The blue player
    private int turn = 0; // The current turn number
    private Player winner;
    private Player loser;

    /**
     * Initializes the `PlayerManager` by creating instances of the yellow and blue
     * players.
     */
    public PlayerManager() {
        yellow = new Player(true);
        blue = new Player(false);
    }

    // Getter methods for accessing Player instances
    public Player getYellowPlayer() {
        return yellow;
    }

    public Player getBluePlayer() {
        return blue;
    }

    public Player getPlayer(boolean isYellow) {
        if (isYellow) {
            return yellow;

        } else {
            return blue;
        }
    }


    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * Gets the current turn number.
     *
     * @return The current turn number.
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Advances the game to the next turn.
     */
    private void nextTurn() {
        turn++;
    }

    /**
     * Gets the current Player turn.
     *
     * @return The current Player turn.
     */
    public Player getCurrentPlayer() {
        return turn % 2 == 0 ? yellow : blue;
    }

    /**
     * Gets the next Player turn.
     * Advances the game to the next turn before returning the current player.
     *
     * @return The next Player turn.
     */
    public Player getNextPlayer() {
        nextTurn();
        return getCurrentPlayer();
    }

    public void reset() {
        turn = 0;
        blue.resetScore();
        yellow.resetScore();
    }


    public void resetTurn(){
        turn = 0;
    }

    public boolean isSwapTurn(){
        return turn % 4 == 0;
    }

    public void setLoser(boolean yellow) {
        // Get winner using PlayerManager
        winner = getPlayer(!yellow);
        loser = getPlayer(yellow);

        // Increment wins and losses
        winner.incrementWin();
        loser.incrementLose();
    }

    public Player getWinner() {
        return winner;
    }



    


          
}
