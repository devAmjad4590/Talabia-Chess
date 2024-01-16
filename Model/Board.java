package Model;

import Model.Pieces.Hourglass;
import Model.Pieces.Piece;
import Model.Pieces.Plus;
import Model.Pieces.Point;
import Model.Pieces.Sun;
import Model.Pieces.Time;
import java.util.HashMap;

/**
 * This was class was implemented by Maher M.N. Balchi.
 * The Board class represents the game board for a specific game.
 * It follows the Singleton pattern to ensure only one instance of the board
 * exists.
 */
public class Board {
    private static Board instance; // The single instance of the Board
    private static int length = 6; // The length of the board
    private static int width = 7; // The width of the board
    private static Tile[][] tiles; // 2D array to store the tiles on the board
    private static HashMap<Piece, Tile> pieceMap = new HashMap<Piece, Tile>();

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the board with the specified length and width.
     */
    private Board() {
        initBoard();
    }

    /*
     * Creates the board with the specified dimensions and initializes all tiles.
     */
    private static void initBoard() {
        tiles = new Tile[length][width]; // Initialize the tiles array

        // Populate the board with Tile objects
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                // i =0, j = 2
                tiles[i][j] = new Tile(i, j, null);

            }
        }

        // Initialize the pieces on the board
        initPieces();
        initMap();

    }

    // reset the board to its initial state
    public static void resetBoard() {
        initBoard();
    }

    /**
     * Flips the board by swapping the pieces between the top half and bottom half
     * of the tiles array.
     * This method iterates over the top half of the tiles array and exchanges the
     * pieces with their
     * corresponding positions in the bottom half, achieving a symmetric flip.
     * implemented by amgad elrashid gurashi eltayeb
     */
    public static void flipBoard() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // swapping the pieces vertically
        for (int i = 0; i < length / 2; i++) {
            for (int j = 0; j < width; j++) {
                Piece temp = tiles[i][j].getPiece();
                tiles[i][j].setPiece(tiles[length - 1 - i][j].getPiece());
                tiles[length - 1 - i][j].setPiece(temp);
            }
        }

        // swapping the pieces horizontally
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width / 2; j++) {
                Piece temp = tiles[i][j].getPiece();
                tiles[i][j].setPiece(tiles[i][width - 1 - j].getPiece());
                tiles[i][width - 1 - j].setPiece(temp);
            }
        }
        initMap();
    }

    private static void initMap() {
        pieceMap.clear();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (tiles[i][j].getPiece() != null) {
                    pieceMap.put(tiles[i][j].getPiece(), tiles[i][j]);
                    tiles[i][j].getPiece();
                }

            }
        }
    }

    // add a piece to the board
    public static void addPiece(Piece piece, int x, int y) {
        tiles[x][y].setPiece(piece);
        initMap();
    }

    public static void clearBoard() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j].setPiece(null);
            }
        }
        pieceMap.clear();
    }

    public static HashMap<Piece, Tile> getMap() {
        return pieceMap;
    }

    // method by Amgad Elrashid Gurashi Eltayeb
    private static void initPieces() {
        // Initialize the yellow pieces
        for (int i = 0; i < 7; i++) {
            tiles[4][i].setPiece(new Point(true));
        }
        // Plus pieces
        tiles[5][0].setPiece(new Plus(true));
        tiles[5][6].setPiece(new Plus(true));

        // Hourglass pieces
        tiles[5][1].setPiece(new Hourglass(true));
        tiles[5][5].setPiece(new Hourglass(true));

        // Time pieces
        tiles[5][2].setPiece(new Time(true));
        tiles[5][4].setPiece(new Time(true));

        // Sun pieces
        tiles[5][3].setPiece(new Sun(true));

        // Initialize the blue pieces
        for (int i = 0; i < 7; i++) {
            tiles[1][i].setPiece(new Point(false));
        }
        // Plus pieces
        tiles[0][0].setPiece(new Plus(false));
        tiles[0][6].setPiece(new Plus(false));

        // Hourglass pieces
        tiles[0][1].setPiece(new Hourglass(false));
        tiles[0][5].setPiece(new Hourglass(false));

        // Time pieces
        tiles[0][2].setPiece(new Time(false));
        tiles[0][4].setPiece(new Time(false));

        // Sun pieces
        tiles[0][3].setPiece(new Sun(false));
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

    /**
     * Gets the tile at the specified coordinates on the board.
     *
     * @param i The row index of the tile.
     * @param j The column index of the tile.
     * @return The Tile at the specified coordinates.
     */
    public Tile getTile(int i, int j) {
        return tiles[i][j];
    }

    /**
     * Gets the width of the board.
     *
     * @return The width of the board.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the length of the board.
     *
     * @return The length of the board.
     */
    public int getLength() {
        return length;
    }

}
