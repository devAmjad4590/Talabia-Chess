import Controller.Controller;
import Model.*;
import VIEW.GameBoard;

public class TestController {
    public static void main(String[] args) {
        Game game = new Game();
        GameBoard gameBoard = new GameBoard();
        game.setBluePlayer(new Player(true));
        game.setYellowPlayer(new Player(false));
        Controller controller = new Controller(gameBoard, game);
    }
}
