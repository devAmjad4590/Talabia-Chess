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
        game.setPlayerMove(player1, game.getTile(4, 0), game.getTile(3, 0));
        System.out.println(game.getTile(4, 0).getPiece());
         game.printBoard();
         System.out.println(game.getTile(1, 0).getPiece().canPass(game.getTile(1, 0), 1));
        game.setPlayerMove(player2, game.getTile(1, 0), game.getTile(3, 0));
        game.printBoard();
        System.out.println(game.getTile(1, 0).getPiece());
        



    }


}
