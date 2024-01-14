package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * SaveManager
 */
public class SaveManager {
    Board board = Board.getInstance();

    // save & load game function
    public void SaveGame() {
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
            BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                System.out.println("test");

            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}