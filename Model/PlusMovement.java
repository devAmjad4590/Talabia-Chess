package Model;

public class PlusMovement implements PieceMovement {
        
    @Override
    public boolean isValid(int xTiles, int yTiles) {
        // Checks if the piece is travelling through the rows or columns
        if ((xTiles > 0 && yTiles == 0) || (yTiles > 0 && xTiles == 0)) {
            return true;
        }



        // need another if condition to see if the piece movement will result in an exposed check

        return false;
    }
}
