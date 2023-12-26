package Model;

public class SunMovement implements PieceMovement{
    public boolean canMove(Tile currentTile, Tile newTile) {
        int xTiles = Math.abs(currentTile.getX() - newTile.getX());
        int yTiles = Math.abs(currentTile.getY() - newTile.getY());

        if((xTiles == 1 && yTiles == 1) || (xTiles == 1 && yTiles == 0) || (xTiles == 0 && yTiles == 1)){
            return true;
        }

        return false;
    }
}
