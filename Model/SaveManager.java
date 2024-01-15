package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * SaveManager
 */
public class SaveManager {
    Board board = Board.getInstance();

    // save & load game function
    public void saveGame() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            for (int i = 0; i < board.getLength(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (board.getTile(i, j).getPiece() != null) {
                        String piece = board.getTile(i, j).getPiece().toString();
                        String position = board.getTile(i, j).toString();
                        writer.write("piece" + " " + "=" + " " + piece
                                + ";" + " " +
                                "position" + " " + "=" + " " + position
                                + "\n");

                    }

                }
            }
            ;
            writer.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    };

    public void loadGame() {
        try {
            File myObj = new File("output.txt");
            Scanner myReader = new Scanner(myObj);
    
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
    
                // Split the line into "piece" and "position" parts
                String[] parts = data.split(";");
                for (String part : parts) {
                    // Split each part into attribute and value
                    String[] attributeValue = part.trim().split("=");
                    String attribute = attributeValue[0].trim();
                    String value = attributeValue[1].trim();
    
                    // Process the attribute and value as needed
                    if (attribute.equals("piece")) {
                        //TODO handlePieceInformation(value);
                    } else if (attribute.equals("position")) {
                        //TODO Handle position information
                        System.out.println("Position: " + value);
                    }
                }
            }

            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}