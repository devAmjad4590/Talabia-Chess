package Model;

public class TimeMovement implements PieceMovement{

    @Override
    public boolean isValid(int xTiles, int yTiles) {
        // Checks if the pieces is moving diagonally.
        if(xTiles == yTiles){
            return true;
        }

        return false;
    }
    
}
