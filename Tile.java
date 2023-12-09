// class implemented by Amgad

public class Tile {
    private Piece piece;
    private int x;
    private int y;

    // Constructor takes a value of x, y and a piece (if piece are not in this tile, piece = null)
    public Tile(int x, int y, Piece piece){
        this.x =  x;
        this.y = y;
        setPiece(piece);
    }

    // returns a tile instance
    public Tile getTile(){
        return this;
    }

    // returns tile coordinates
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    
    // setting the piece in the tile instance
    public void setPiece(Piece piece){
        this.piece =piece;
    }

    // returns the piece in the tile instance
    public Piece getPiece(){
        return piece;
    }
}
