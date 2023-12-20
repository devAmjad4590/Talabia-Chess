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
        if (isMoveValid(player, currentTile, newTile) && !isSunExposed()) {
            // check if the move would expose the sun

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
        } else {
            blue.setTurn(true);
            yellow.setTurn(false);
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

    /**
     * Prints the current state of the game board.
     */
    public void printBoard() {
        board.printBoard();
    }

    /**
     * Checks whether the sun is exposed to capture.
     *
     * @return true if the sun is exposed, false otherwise.
     */
    private boolean isSunExposed() {
        Tile sunTile = getSunTile();
        System.out.println("Sun tile: " + sunTile.getX() + ", " + sunTile.getY());
        for (int i = 0; i < board.getLength(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                Tile currentTile = board.getTile(i, j);
                Piece currentPiece = currentTile.getPiece();

                if (currentPiece != null
                        && currentPiece.isYellow() != getPlayerTurn().isYellow()) {
                    if ((currentPiece.canMove(currentTile, sunTile))) {
                        // print the tile location of the piece that can move to the sun
                        // System.out.println("Piece that can move to sun: " + currentTile.getX() + ", "
                        //         + currentTile.getY() + " " + currentPiece);
                        // System.out.println("Sun is exposed");
                        return true;
                    }
                }
            }
        }
        System.out.println("Sun is not exposed");
        return false;
    }

     /**
     * Retrieves the tile containing the sun piece for the current player.
     *
     * @return The tile containing the sun piece.
     */
    private Tile getSunTile() {
        Tile[][] tiles = board.getTiles();
        for (int i = 0; i < board.getLength(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (tiles[i][j].getPiece() instanceof Sun
                        && tiles[i][j].getPiece().isYellow() == getPlayerTurn().isYellow()) {
                    return board.getTile(i, j);
                }
            }
        }
        return null;
    }

}