public class Board{
    private static Board instance;
    private static int length = 6;
    private static int width = 7;

    private Board(int length, int width) {
        //implemtation
    };

    public static Board getInstance() {
        if (instance == null){
            instance = new Board(length, width);
        }
        return instance;
    }
}