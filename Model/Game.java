package Model;

import java.util.Map;

import Model.Pieces.*;

/**
 * The `Game` class represents the main logic and state of a game.
 * It is implemented by Amgad Elrashid Gurashi Eltayeb.
 */
public class Game {
    private PlayerManager playerManager; // The player manager
    private Player currentPlayer; // The current player
    private Move move; // The move instance
    private boolean gameOver; // True if the game is over, false otherwise

    /**
     * Initializes the game by creating a player manager instance and a game board.
     */
    public Game() {
        init();
    }

    /**
     * Initializes the game by creating a player manager instance, move instance and board.
     */
    public void init() {
        gameOver = false;
        Board.resetBoard(); // reset the board to its initial state
        playerManager = new PlayerManager(this); // playerManager created
        currentPlayer = playerManager.getCurrentPlayer(); //
        move = new Move(currentPlayer);
    }

    /**
     * Gets the player manager.
     *
     * @return The player manager.
     */
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void setCurrentPlayer() {
        currentPlayer = playerManager.getCurrentPlayer();
        move = new Move(currentPlayer);
    }

    

    /**
     * Gets the tile at the specified coordinates on the board.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return The tile at the specified coordinates.
     */
    public Tile getTile(int x, int y) {
        return Board.getTile(x, y);
    }

    /**
     * Gets the move instance.
     *
     * @return The move instance.
     */
    public Move getMove() {
        return move;
    }

    /**
     * Sets the player's move from the current tile to the new tile.
     * After the move, flips the game board and updates the current player,
     * checks if the pieces can swap or point can change direction,
     * creates new move instance for the next player.
     * 
     * @param currentTile The current tile of the player's piece.
     * @param newTile     The new tile where the player's piece will be moved.
     */
    public void playerMove(Tile currentTile, Tile newTile) {
        if(!move.isMoveValid(currentTile, newTile))
            return;
        isGameOver(newTile.getPiece()); // check if the piece captured a sun
        move.setPlayerMove(currentTile, newTile); // sets the player's move
        currentPlayer = playerManager.getNextPlayer(); // updates the current player
        move = new Move(currentPlayer); // creates a new move instance for the next player
        checkGameState(currentTile, newTile); // checks if the pieces can swap or point can change direction
        Board.flipBoard(); // flips the board

    }

    private void checkGameState(Tile currentTile, Tile newTile) {
        canSwitchPoint(newTile); // checks if the point can change direction
        if (playerManager.isSwapTurn()) // checks if the pieces can swap
            swapPieces();
    }

    private void canSwitchPoint(Tile current) {
        Piece piece = current.getPiece();
        if ((current.getX() == 5 || current.getX() == 0) && piece instanceof Point) {
            Point point = (Point) piece;
            point.switchMovement();
        }
    }

    private void swapPieces() {
        for (Map.Entry<Piece, Tile> entry : Board.getMap().entrySet()) {
            Piece piece = entry.getKey();
            Tile tile = entry.getValue();
            if (piece instanceof Time) {
                tile.setPiece(new Plus(piece.isYellow()));
            }

            if (piece instanceof Plus) {
                tile.setPiece(new Time(piece.isYellow()));
            }

        }

    }

    /**
     * Moves a piece from the current tile to the new tile.
     * If the new tile is occupied by an opponent's piece, the opponent's piece is
     * captured.
     *
     * @param currentTile The current tile of the piece.
     * @param newTile     The new tile where the piece will be moved.
     */
    private void isGameOver(Piece piece) {
        if (move.isSunCaptured(piece)) {
            playerManager.setLoser(piece.isYellow());
            gameOver = true;
        } else
            return;
    }

    public void setGameOver() {
        gameOver = true;
    }

    /**
     * Checks if the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean getGameOver() {
        return gameOver;
    }

    /**
     * Resets the game state for next Round
     */
    public void nextGame() {
        gameOver = false;
        Board.resetBoard();
        playerManager.resetTurn();
        setCurrentPlayer();

    }

}
