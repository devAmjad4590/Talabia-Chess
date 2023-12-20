package Model;

public class Test{
    public static void main(String[] args) {
        Game game = new Game();
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        game.setBluePlayer(player1);
        game.setYellowPlayer(player2);
        game.startGame();
        game.printBoard();
        System.out.println(game.getTile(3, 2).getY());
        game.setPlayerMove(player1, game.getTile(5, 1), game.getTile(3, 2));
        game.printBoard();
        game.setPlayerMove(player2, game.getTile(0, 1), game.getTile(2, 2));
        game.printBoard();
        game.setPlayerMove(player1, game.getTile(3, 2), game.getTile(1, 1));
        game.printBoard();

    }

    // print board method

}
