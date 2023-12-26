package Model;

public class BackwardMovement implements PieceMovement{

    @Override
    public boolean canMove(Tile currentTile, Tile newTile) {
        int xTiles = currentTile.getX() - newTile.getX();

        if(xTiles == -1 || xTiles == -2){
            return true;
        }

        return false;
    }
    
}
