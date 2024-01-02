package Model;

/**
 * The `PlayerManager` class manages the players and the turn progression in the game.
 * It is responsible for tracking the current turn and providing access to the current and next players.
 * class implemented by mamoon
 */
public class PlayerManager {
    private Player yellow; // The yellow player
    private Player blue;   // The blue player
    private int turn = 0;   // The current turn number

    /**
     * Initializes the `PlayerManager` by creating instances of the yellow and blue players.
     */
    public PlayerManager() {
        yellow = new Player(true);
        blue = new Player(false);
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
    public Player getNextPlayer(){
        nextTurn();
        return getCurrentPlayer();
    }
}
