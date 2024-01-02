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

    public Game(){
        init();
    }

    /**
     * Initializes the game by creating player instances and game board.
     */
    private void init() {
        board = Board.getInstance();
        yellow = new Player(true);
        blue = new Player(false);
    }

    public Board getBoard() {
        return board;
    }

    public Tile getTile(int x, int y) {
        return board.getTile(x, y);
    } // only for test purposes

    /**
     * Moves a player's piece from the current tile to the new tile.
     *
     * @param player      The player to move
     * @param currentTile The current tile of the player's piece.
     * @param newTile     The new tile where the player's piece will be moved.
     */
    private boolean isMoveValid(Player player, Tile currentTile, Tile newTile) {
        Piece playerPiece = currentTile.getPiece(); // store the source piece in the playerPiece variable

        if (playerPiece == null ||  !playerPiece.canMove(currentTile, newTile) || player.isYellow() != playerPiece.isYellow()) {
            return false;
        }

        // Check if the new tile has a piece and if it does,
        // check if the piece of the same color as the player is in the new tile
        if (newTile.getPiece() != null && newTile.getPiece().isYellow() == playerPiece.isYellow()) {
            return false;
        }

        // if the tile has a piece and if it does,
        // check if the piece is an enemy piece then capture and return true
        if (newTile.getPiece() != null && newTile.getPiece().isYellow() != currentTile.getPiece().isYellow()) {
            removeCaptured(newTile);
        }
        
        return true;

    }

    /**
     * Sets the player's move from the current tile to the new tile.
     *
     * @param player      The player making the move.
     * @param currentTile The current tile of the player's piece.
     * @param newTile     The new tile where the player's piece will be moved.
     */
    public void setPlayerMove(Player player, Tile currentTile, Tile newTile) {
        Piece piece = currentTile.getPiece();

        // check if the move is valid
        if (isMoveValid(player, currentTile, newTile)) {
            newTile.setPiece(piece);
            currentTile.setPiece(null);
            nextTurn();
            System.out.println("successful Move");
        }
    }

    private void removeCaptured(Tile tile) {
        Piece capturedPiece = tile.getPiece();
        if (capturedPiece != null) {
            tile.setPiece(null);
            capturedPiece.setCaptured();
            board.getPieceList().remove(capturedPiece); // removes the first piece in the list
        }
    }



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
        yellow.setTurn(turn % 2 != 0);
        blue.setTurn(turn % 2 == 0);
        Board.flipBoard();
        turn++;
    }

    /**
     * Gets the current Player turn.
     *
     * @return The current Player turn.
     */
    public Player getCurrentPlayer() {
        if (blue.getTurn()) {
            return blue;
        }
        return yellow;
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

    

}
