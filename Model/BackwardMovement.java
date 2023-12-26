package Model;

public class BackwardMovement implements PieceMovement{

    @Override
    public boolean isValid(int xTiles, int yTiles) {
        if(xTiles == -1 || xTiles == -2){
            return true;
        }

        return false;
    }
    
}
