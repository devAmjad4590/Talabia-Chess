package Controller;

import java.awt.event.ActionListener;

import Model.Game;
import View.GameView;

/**
 * The ButtonActionListener class represents the ActionListener for various buttons in the chess game GUI.
 * It handles actions such as starting a new game, resigning from the current game, quitting the application,
 * and saving the game state.
 * by Amgad Elrashid Gurashi Eltayeb.
 */

public class ButtonActionListener {
    private Game model; 
    private GameView view;
    private Controller controller;

    /**
     * Constructs a ButtonActionListener object.
     * 
     * @param controller The Controller object associated with the listener.
     */
    public ButtonActionListener(Controller controller) {
        this.model = controller.getModel();
        this.view = controller.getView();
        this.controller = controller;
    }

    /**
     * Returns an ActionListener for the "New Game" button.
     * 
     * @return ActionListener for the "New Game" button.
     */
    public ActionListener newGameListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                view.getNorthPanel().showNewGame();
                int response =  view.getNorthPanel().getResponse();
                if(response == 0){
                    model.init();
                    controller.showBoard();
                }
            }
        };
    }

    /**
     * Returns an ActionListener for the "Resign" button.
     * 
     * @return ActionListener for the "Resign" button.
     */
    public ActionListener resignActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                view.getEastPanel().showResign();
                int response =  view.getEastPanel().getResponse();
                if(response == 0){
                    model.getPlayerManager().resign();
                    controller.handleGameOver();
                }
            }
        };
    }

    /**
     * Returns an ActionListener for the "Quit" button.
     * 
     * @return ActionListener for the "Quit" button.
     */
    public ActionListener quitActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                view.getNorthPanel().showQuit();
                int response =  view.getNorthPanel().getResponse();
                if(response == 0){
                    System.exit(0);
                }
            }
        };
    }

    /**
     * Returns an ActionListener for the "Save" button.
     * 
     * @return ActionListener for the "Save" button.
     */
    public ActionListener saveActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                view.getNorthPanel().showSave();
                int response =  view.getNorthPanel().getResponse();
                if(response == 0){
                    controller.getSaveManager().saveGame();
                    view.getNorthPanel().showSuccessSave();
                }               
            }
        };
    }


    /**
     * Loads a saved game using the SaveManager and displays the game board.
     */
    private void loadGame(){
        controller.getSaveManager().loadGame();
        controller.showBoard();
    }

    
}
