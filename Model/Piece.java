package Model;

/**
 * The {@code Piece} class is an abstract base class representing a chess piece in a chess game.
 * It includes information about whether the piece has been captured and its color.
 * Subclasses must implement the {@code canMove} method to determine the piece's valid moves.
 * Class implemented by Asim Adel Ahmed Maroof
 */
public abstract class Piece {
    private boolean captured;
    private boolean yellow;
    private PieceMovement pieceMovement;

    /**
     * Constructs a new Piece instance with the specified color.
     *
     * @param yellow Indicates the color of the piece. True for yellow, false for another color.
     */
    public Piece(boolean yellow) {
        this.yellow = yellow;
    }

    /**
     * Checks whether the piece has been captured.
     *
     * @return {@code true} if the piece has been captured, {@code false} otherwise.
     */
    public boolean isCaptured() {
        return captured;
    }
    public void setCaptured() {
        this.captured = true;
    }

     /**
     * Gets the color of the piece.
     *
     * @return {@code true} if the piece is yellow, {@code false} otherwise.
     */
    public boolean isYellow() {
        return yellow;
    }

    //method added by Amgad Elrashid Gurashi Eltayeb
    public boolean sameColor(Piece otherPiece){
        return this.yellow == otherPiece.yellow;
    }

    /**
     * Abstract method that must be implemented by subclasses to determine the valid moves of the piece.
     *
     * @param currentTile The current tile position of the piece.
     * @param newTile The new tile position to check for a valid move.
     * @return {@code true} if the move is valid, {@code false} otherwise.
     */
    public abstract boolean canMove(Tile currentTile, Tile newTile);

    public void setPieceMovement(PieceMovement pieceMovement) {
        this.pieceMovement = pieceMovement;
    }
    
    public PieceMovement getPieceMovement() {
        return pieceMovement;
    }

    public String toString(){
        // return the piece type
        return this.getClass().getSimpleName();
    }

    public boolean canPass(Tile tile, int kkk) {
        return true;
    }
}
