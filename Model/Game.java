package Model;

/**
 * This class is implemented by Amgad Elrashid Gurashi Eltayeb
 * The Game class represents the main game logic and state.
 */

public class Game {
    private Player yellow; // The yellow player
    private Player blue; // The blue player
    private int turn = 0; // The current turn number
    private Board board; // The game board

    /**
     * Initializes the game by creating player instances and game board.
     */
    private void init() {
        board = Board.getInstance();
    }

    public Tile getTile(int x, int y) {
        return board.getTile(x, y);
    }

    /**
     * Moves a player's piece from the current tile to the new tile.
     *
     * @param player      The player to move
     * @param currentTile The current tile of the player's piece.
     * @param newTile     The new tile where the player's piece will be moved.
     */
    private boolean isMoveValid(Player player, Tile currentTile, Tile newTile) {
        Piece playerPiece = currentTile.getPiece(); // store the source piece in the playerPiece variable

        // Check if the picked tile has a piece, if the player is the same as the player
        // turn,
        // if the player piece can move to the new tile, and if the player piece is the
        // same color as the player
        if (playerPiece == null ||
                player != getPlayerTurn() ||
                !playerPiece.canMove(currentTile, newTile) ||
                player.isYellow() != playerPiece.isYellow()) {
            return false;
        }

        // Check if the new tile has a piece and if it does,
        // check if the piece of the same color as the player is in the new tile
        if (newTile.getPiece() != null && newTile.getPiece().isYellow() == playerPiece.isYellow()) {
            return false;
        }

        return true;

    }

    public void setPlayerMove(Player player, Tile currentTile, Tile newTile) {
        Piece piece = currentTile.getPiece();
        if (isMoveValid(player,currentTile, newTile)) {
            // move the piece to the new tile
            newTile.setPiece(piece);
            currentTile.setPiece(null);
            nextTurn();
        }
    }

    /**
     * Starts the game by initializing the game state and starting the first turn.
     */
    public void startGame() {
        init();

        // implementation
    };

    /**
     * Handles the logic when the game is over.
     */
    public void gameOver() {
        // implementation

    };

    /**
     * Advances the game to the next turn, switching between yellow and blue
     * players.
     */
    private void nextTurn() {
        // Switch turns between yellow and blue players
        if (turn % 2 == 0) {
            yellow.setTurn(true);
            blue.setTurn(false);
            System.out.println("yellow player's turn");
            // Additional logic for yellow player's turn
        } else {
            blue.setTurn(true);
            yellow.setTurn(false);
            System.out.println("blue player's turn");
            // Additional logic for blue player's turn
        }

        turn++;
    }

    /**
     * Gets the current Player turn.
     *
     * @return The current Player turn.
     */
    public Player getPlayerTurn() {
        if (yellow.getTurn()) {
            return yellow;
        }
        return blue;
    }

    /**
     * Sets the blue player for the game.
     *
     * @param player The blue player to set.
     */
    public void setBluePlayer(Player player) {
        this.blue = player;
    }

    /**
     * Sets the yellow player for the game.
     *
     * @param player The yellow player to set.
     */
    public void setYellowPlayer(Player player) {
        this.yellow = player;
    }

    /**
     * Gets the blue player of the game.
     *
     * @return The blue player.
     */
    public Player getBluePlayer() {
        return blue;
    }

    /**
     * Gets the yellow player of the game.
     *
     * @return The yellow player.
     */
    public Player getYellowPlayer() {
        return yellow;
    }

    public void printBoard() {
        board.printBoard();
    }

}
