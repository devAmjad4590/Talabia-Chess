package Model;

import Model.Pieces.*;

public class PieceFactory {
    public static Piece createPiece(String piece, boolean isYellow){
        switch(piece){
            case "Point":
                return new Point(isYellow);
            case "Sun":
                return new Sun(isYellow);
            case "Hourglass":
                return new Hourglass(isYellow);
            case "Time":
                return new Time(isYellow);
            case "Plus":
                return new Plus(isYellow);
            
            default: throw new IllegalArgumentException("Invalid piece type");
        }

        

    }
}
