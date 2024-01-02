package Model;

public class BackwardMovement implements PieceMovement{

    @Override
    public boolean isValid(int xTiles, int yTiles) {
        if((xTiles == -1 || xTiles == -2) && yTiles == 0){
            return true;
        }

        return false;
    }
    
}
