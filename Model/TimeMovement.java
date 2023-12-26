package Model;

public class TimeMovement implements PieceMovement{

    @Override
    public boolean canMove(Tile currentTile, Tile newTile) {
        // Calculating the number of tiles travelled by 
        //using the absolute value of the current and destination tile.
        int xTiles = Math.abs(currentTile.getY() - newTile.getY());
        int yTiles = Math.abs(currentTile.getX() - newTile.getX());

        // Checks if the pieces is moving diagonally.
        if(xTiles == yTiles && newTile.getPiece() == null){
            return true;
        }

        return false;
    }
    
}
