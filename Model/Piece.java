package Model;

public abstract class Piece {
    private boolean captured;
    private boolean yellow;
    private PieceMovement pieceMovement;

    public Piece(boolean yellow) {
        this.yellow = yellow;
    }

    public boolean isCaptured() {
        return captured;
    }
    public void setCaptured() {
        this.captured = true;
    }

    public boolean isYellow() {
        return yellow;
    }

    //method added by Amgad Elrashid Gurashi Eltayeb
    public boolean sameColor(Piece otherPiece){
        return this.yellow == otherPiece.yellow;
    }

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

}
