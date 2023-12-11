/* 
 * This class was implemented by Amgad Elrashid Gurashi Eltayeb.
 * The tile class represents the Tiles that are used to make the Board. 
 * Each tile consist of its coordinates (X and Y) and the pieces in that tile accordingly.
 */

public class Tile {
    private Piece piece; // the piece that exist in the tile instance (it can be null, if not pieces are in it)
    private int x; // coordinate X of the tile
    private int y; // coordinate Y of the tile

    /**
     * Constructor takes a value of x, y and a piece (if piece are not in this tile, piece = null)
     * Initializes the tile with the specified attributes 
     * 
     * @param x Coordinate X of the tile
     * @param y Coordinate Y of the tile
     * @param piece Piece in the tile
     */
    public Tile(int x, int y, Piece piece){
        this.x =  x;
        this.y = y;
        setPiece(piece);
    }

    /**
     * @return coordinates of the tile
     */
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    
    /**
     * 
     * @param piece Setting the piece in the tile instance
     */
    public void setPiece(Piece piece){
        this.piece =piece;
    }

    /**
     * @return The piece in the tile
     */
    public Piece getPiece(){
        return piece;
    }
}


