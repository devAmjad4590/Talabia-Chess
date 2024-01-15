package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import Model.Movements.PieceMovement;
import Model.Pieces.Piece;

/**
 * SaveManager
 */
public class SaveManager {
    private Game game;

    public SaveManager(Game game) {
        this.game = game;
    }

    Board board = Board.getInstance();

    // save & load game function
    public void saveGame() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            for (Map.Entry<Piece, Tile> entry : Board.getMap().entrySet()) {
                Piece piece = entry.getKey();
                Tile tile = entry.getValue();
                Boolean isYellow = piece.isYellow();

                writer.write("piece" + " " + "=" + piece.getClass().getSimpleName()
                + ";" + " " +
                "position" + " " + "=" + tile.toString()
                + ";" + " " +
                "isYellow" + " " + "=" + isYellow
                + ";" + " " +
                "movement" + " " + "=" + piece.getPieceMovement().getClass().getSimpleName()
                + "\n");

            }
            writer.write("Player 1 Score" + " " + "=" + game.getPlayerManager().getYellowPlayer().getNoOfWins()
                    + "-" + game.getPlayerManager().getYellowPlayer().getNoOfLosses() + "\n");
            writer.write("Player 2 Score" + " " + "=" + game.getPlayerManager().getBluePlayer().getNoOfWins() + "-"
                    + game.getPlayerManager().getBluePlayer().getNoOfLosses() + "\n");
            writer.write("Number of turns taken" + " " + "=" + game.getPlayerManager().getTurn() + "\n");

            writer.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    };

    public void loadGame() {
        Board.clearBoard();
        try {
            File file = new File("output.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("; ");

                if (line.startsWith("piece")) {
                    String pieceType = parts[0].split(" =")[1];
                    String piecePos = parts[1].split(" =")[1];
                    boolean isYellow = Boolean.parseBoolean(parts[2].split(" =")[1]);
                    String movementType = parts[3].split(" =")[1];

                    Piece piece = PieceFactory.createPiece(pieceType, isYellow);
                    PieceMovement movement = PieceMovementFactory.createPieceMovement(movementType);

                    piece.setPieceMovement(movement);

                    String[] positionParts = piecePos.split(",");
                    int row = Integer.parseInt(positionParts[0]);
                    int col = Integer.parseInt(positionParts[1]);
                    Board.addPiece(piece, row, col);

                } else if (line.startsWith("Number of turns taken")) {
                    int turn = Integer.parseInt(parts[0].split(" =")[1]);
                    game.getPlayerManager().setTurn(turn);
                }

                else if (line.startsWith("Player 1 Score")) {
                    String scoreInfo = parts[0].split(" =")[1];
                    String[] scoreParts = scoreInfo.split("-");

                    int wins = Integer.parseInt(scoreParts[0]);
                    int losses = Integer.parseInt(scoreParts[1]);

                    game.getPlayerManager().getYellowPlayer().setScore(wins, losses);
                }

                else if (line.startsWith("Player 2 Score")) {
                   String scoreInfo = parts[0].split(" =")[1];
                    String[] scoreParts = scoreInfo.split("-");
                    
                    int wins = Integer.parseInt(scoreParts[0]);
                    int losses = Integer.parseInt(scoreParts[1]);

                    game.getPlayerManager().getBluePlayer().setScore(wins, losses);
                }

            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}