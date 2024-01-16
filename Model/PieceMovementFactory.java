/**
 * The PieceMovementFactory class represents a factory for creating instances of different PieceMovement subclasses.
 * It utilizes static factory methods to encapsulate the instantiation logic and provide a centralized point for creating
 * specific types of PieceMovement objects.
 * This class was implemented by Amgad Elrashid Gurashi Eltayeb.
 */
package Model;

import Model.Movements.*;

public class PieceMovementFactory {

    /**
     * Creates a PieceMovement object based on the provided movement type.
     *
     * @param movement A String representing the type of movement for which a PieceMovement object is to be created.
     * @return A concrete implementation of the PieceMovement interface or class corresponding to the specified movement type.
     * @throws IllegalArgumentException if the provided movement type is not recognized.
     */
    public static PieceMovement createPieceMovement(String movement) {
        switch (movement) {
            case "TimeMovement":
                return new TimeMovement();
            case "HourglassMovement":
                return new HourglassMovement();
            case "PlusMovement":
                return new PlusMovement();
            case "SunMovement":
                return new SunMovement();
            case "ForwardMovement":
                return new ForwardMovement();
            case "BackwardMovement":
                return new BackwardMovement();
            default:
                throw new IllegalArgumentException("Invalid piece type");
        }
    }
}
