/**
 * This was class was implemented by Maher M.N. Balchi.
 * The Board class represents the game board for a specific game.
 * It follows the Singleton pattern to ensure only one instance of the board exists.
 */
public class Board {
    private static Board instance;  // The single instance of the Board
    private static int length = 6;   // The length of the board
    private static int width = 7;    // The width of the board
    private static Tile[][] tiles;   // 2D array to store the tiles on the board

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the board with the specified length and width.
     */
    private Board() {
        createBoard();
    }

    /*
     * Creates the board with the specified dimensions and initializes all tiles.
     */
    private static void createBoard() {
        tiles = new Tile[length][width];  // Initialize the tiles array

        // Populate the board with Tile objects
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = new Tile(i, j, null);
            }
        }
    }

    /**
     * Gets the single instance of the Board class.
     * If the instance does not exist, it is created.
     *
     * @return The Board instance.
     */
    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }
}
