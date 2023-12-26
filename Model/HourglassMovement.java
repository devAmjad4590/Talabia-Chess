package Model;

public class HourglassMovement implements PieceMovement{

    @Override
    public boolean canMove(Tile currentTile, Tile newTile) {
        int xTiles = Math.abs(currentTile.getX() - newTile.getX());
        int yTiles = Math.abs(currentTile.getY() - newTile.getY());

        if((xTiles == 1 && yTiles == 2) || (xTiles == 2 && yTiles == 1)){
            return true;
        }

        return false;
    }
}
