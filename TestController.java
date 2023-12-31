import Controller.Controller;
import Model.*;
import View.GameView;

public class TestController {
    public static void main(String[] args) {
        Game game = new Game();
        GameView gameBoard = new GameView();
        game.setBluePlayer(new Player(true));
        game.setYellowPlayer(new Player(false));
        Controller controller = new Controller(gameBoard, game);
    }
}
