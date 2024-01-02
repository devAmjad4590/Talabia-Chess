package Model;

import java.util.ArrayList;

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
    private static ArrayList<Piece> pieceList; // List of pieces on the board

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
        tiles = new Tile[length][width]; // Initialize the tiles array

        // Populate the board with Tile objects
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = new Tile(i, j, null);
            }
        }

        // Initialize the list of pieces
        pieceList = new ArrayList<>();

        // Initialize the pieces on the board
        initPieces();

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
        for (int i = 0; i < tiles.length / 2; i++) {
            for (int j = 0; j < tiles[i].length; j++) { 
                Piece temp = tiles[i][j].getPiece();
                tiles[i][j].setPiece(tiles[tiles.length - i - 1][j].getPiece());
                tiles[tiles.length - i - 1][j].setPiece(temp);
            }
        }
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
        createList();
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

    public Tile getTile(int i, int j) {
        return tiles[i][j];
    }

    public void printBoard() {
        // 1 if piece exist
        // 0 if piece not exist
        // write the print method like before
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (tiles[i][j].getPiece() == null) {
                    System.out.print("0 ");
                } else if (tiles[i][j].getPiece() != null) {
                    System.out.print(tiles[i][j].getPiece().toString().charAt(0) + " ");
                }
            }
            System.out.println();
        }
        for (int i = 0; i <= tiles.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();

    }

    private static void createList() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (tiles[i][j].getPiece() != null) {
                    pieceList.add(tiles[i][j].getPiece());
                }
            }
        }
    }

    // get width
    public int getWidth() {
        return width;
    }

    // get length
    public int getLength() {
        return length;
    }

    // get tiles
    public Tile[][] getTiles() {
        return tiles;
    }

    // get piece list
    public ArrayList<Piece> getPieceList() {
        return pieceList;
    }

    /**
     * Retrieves the tile containing the sun piece for the current player.
     *
     * @return The tile containing the sun piece.
     */
    public Tile getSunTile(Player currentPlayer) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (tiles[i][j].getPiece() instanceof Sun
                        && tiles[i][j].getPiece().isYellow() == currentPlayer.isYellow()) {
                    return tiles[i][j];
                }
            }
        }
        return null;
    }

    // find the piece's tile
    public Tile findPieceTile(Piece piece) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (tiles[i][j].getPiece() == piece) {
                    return tiles[i][j];
                }
            }
        }
        return null;
    }

}
