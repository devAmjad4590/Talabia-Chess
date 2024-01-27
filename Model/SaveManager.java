package Model;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Scanner;
import Model.Movements.PieceMovement;
import Model.Pieces.Piece;

/**
 * SaveManager class is responsible for saving and loading game states to and
 * from a file.
 * It provides methods to save the current game state, including the positions,
 * types, and movements of
 * all pieces on the board, as well as player scores and the number of turns
 * taken.
 * It also offers functionality to load a saved game and restore the state of
 * the game accordingly.
 * This class was implemented by Amgad Elrashid Gurashi Eltayeb .
 */
public class SaveManager {
    private Game game;

    /**
     * Constructs a SaveManager object associated with a specific game instance.
     *
     * @param game The game instance to be associated with the SaveManager.
     */
    public SaveManager(Game game) {
        this.game = game;
    }

    /**
     * Saves the current state of the game, including piece positions, types,
     * movements, player scores,
     * and the number of turns taken, to a text file.
     */
    public void saveGame() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./Model/game.txt"));

            // Iterate through the pieces on the board and save their information
            for (Map.Entry<Piece, Tile> entry : Board.getMap().entrySet()) {
                Piece piece = entry.getKey();
                Tile tile = entry.getValue();
                Boolean isYellow = piece.isYellow();

                // Write piece information to the file
                writer.write("piece" + " " + "=" + piece.getClass().getSimpleName()
                        + ";" + " " +
                        "position" + " " + "=" + tile.toString()
                        + ";" + " " +
                        "isYellow" + " " + "=" + isYellow
                        + ";" + " " +
                        "movement" + " " + "=" + piece.getPieceMovement().getClass().getSimpleName()
                        + "\n");

            }
            // Write player scores and the number of turns taken to the file
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

    /**
     * Loads a saved game state from a text file and restores the game accordingly.
     */
    public void loadGame() {

        // Clear the game board before loading the new state
        Board.clearBoard();
        try {
            File file = new File("./Model/game.txt");
            Scanner scanner = new Scanner(file);

            // Iterate through each line in the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("; ");

                // check if the line contains piece information
                if (line.startsWith("piece")) {
                    loadPieces(parts);
                    
                } 
                // check if the line contains number of turns taken
                else if (line.startsWith("Number of turns taken")) {
                    loadTurn(parts);
                }

                // check if the line contains player scores
                else if (line.startsWith("Player 1 Score")) {
                    loadPlayer(parts, game.getPlayerManager().getYellowPlayer());
                }

                else if (line.startsWith("Player 2 Score")) {
                    loadPlayer(parts, game.getPlayerManager().getBluePlayer());
                }

            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads information about pieces from a string array and creates and places the pieces on the board.
     *
     * @param parts The string array containing information about the piece.
     */
    private void loadPieces(String[] parts) {
        // Extract information from the string array
        String pieceType = parts[0].split(" =")[1];
        String piecePos = parts[1].split(" =")[1];
        boolean isYellow = Boolean.parseBoolean(parts[2].split(" =")[1]);
        String movementType = parts[3].split(" =")[1];
        String[] position = piecePos.split(",");
        int x = Integer.parseInt(position[0]);
        int y = Integer.parseInt(position[1]);

        try {
            // creating the pieces class
            Class<?> pieceClass = Class.forName("Model.Pieces." + pieceType);
            Constructor<?> pieceConstructor = pieceClass.getConstructor(boolean.class);
            Piece piece = (Piece) pieceConstructor.newInstance(isYellow);

            // creating the movement class
            Class<?> movementClass = Class.forName("Model.Movements." + movementType);
            Constructor<?> movementConstructor = movementClass.getConstructor();
            PieceMovement movement = (PieceMovement) movementConstructor.newInstance();

            // setting the pieces and their movements
            Board.addPiece(piece, x, y);
            piece.setPieceMovement(movement);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            ((Throwable) e).printStackTrace();
        }
    }

    /**
     * Loads player information (scores) from a string array and sets the player's scores.
     *
     * @param parts  The string array containing information about the player.
     * @param player The player whose scores need to be set.
     */
    private void loadPlayer(String[] parts, Player player) {
        String scoreInfo = parts[0].split(" =")[1];
        String[] scoreParts = scoreInfo.split("-");

        int wins = Integer.parseInt(scoreParts[0]);
        int losses = Integer.parseInt(scoreParts[1]);

        game.getPlayerManager().getYellowPlayer().setScore(wins, losses);
    }

    /**
     * Loads the number of turns taken from a string array and sets the number of turns taken.
     *
     * @param parts The string array containing information about the number of turns taken.
     */
    private void loadTurn(String[] parts) {
        int turn = Integer.parseInt(parts[0].split(" =")[1]);
        game.getPlayerManager().setTurn(turn);
        game.setCurrentPlayer();
    }
}