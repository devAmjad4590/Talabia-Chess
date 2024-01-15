package Model;

import Model.Movements.*;

public class PieceMovementFactory {
    public static PieceMovement createPieceMovement(String movement){
        switch(movement){
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
            default: throw new IllegalArgumentException("Invalid piece type");
        }
    }
}
