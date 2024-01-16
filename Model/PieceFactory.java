/**
 * The PieceFactory class represents a factory for creating instances of different Piece subclasses.
 * It utilizes static factory methods to encapsulate the instantiation logic and provide a centralized point for creating
 * specific types of Piece objects.
 */
package Model;

import Model.Pieces.*;

public class PieceFactory {

    /**
     * Creates a Piece object based on the provided piece type.
     *
     * @param piece    A String representing the type of piece for which a Piece object is to be created.
     * @param isYellow A boolean indicating whether the piece is yellow.
     * @return A concrete implementation of the Piece interface or class corresponding to the specified piece type.
     * @throws IllegalArgumentException if the provided piece type is not recognized.
     */
    public static Piece createPiece(String piece, boolean isYellow) {
        switch (piece) {
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
            default:
                throw new IllegalArgumentException("Invalid piece type");
        }
    }
}
