package Model;

public class SunMovement implements PieceMovement{

    @Override
    public boolean isValid(int xTiles, int yTiles) {
        if((xTiles == 1 && yTiles == 1) || (xTiles == 1 && yTiles == 0) || (xTiles == 0 && yTiles == 1)){
            return true;
        }

        return false;
    }
}
