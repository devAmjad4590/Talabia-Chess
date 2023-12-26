package Model;

public class PlusMovement implements PieceMovement {
    public boolean canMove(Tile currentTile, Tile newTile) {
        // Calculating the number of tiles travelled by 
        //using the absolute value of the current and destination tile.
        int xTiles = Math.abs(currentTile.getY() - newTile.getY());
        int yTiles = Math.abs(currentTile.getX() - newTile.getX());

        // Checks if the piece is travelling through the rows or columns
        if ((xTiles > 0 && yTiles == 0) || (yTiles > 0 && xTiles == 0) && newTile.getPiece() == null) {
            return true;
        }



        // need another if condition to see if the piece movement will result in an exposed check

        return false;
    }
}
