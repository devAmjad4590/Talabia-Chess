package Model;

public class PlusMovement implements PieceMovement {
        
    @Override
    public boolean isValid(int xTiles, int yTiles) {
        // Checks if the piece is travelling through the rows or columns
        if ((xTiles > 0 && yTiles == 0) || (yTiles > 0 && xTiles == 0)) {
            return true;
        }




        return false;
    }
}
