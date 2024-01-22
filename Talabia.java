import Controller.Controller;
import Model.*;
import View.StartMenu;

public class Talabia {
    public static void main(String[] args) {
        // There are two views, one for the start menu and one for the game.
        // The start menu view is created here, and the game view is created in the ButtonActionListener class.
        Game model = new Game(); // model
        StartMenu startMenu = new StartMenu(); // view
        Controller controller = new Controller(model, startMenu); // controller
        }
    }

