package Model;

public class HourglassMovement implements PieceMovement{

    @Override
    public boolean isValid(int xTiles, int yTiles) {
        if((xTiles == 1 && yTiles == 2) || (xTiles == 2 && yTiles == 1)){
            return true;
        }
        return false;
    }
}
