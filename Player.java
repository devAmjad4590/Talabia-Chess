
public class Player {
    private boolean isTurn = false;
    private boolean yellow;

    public Player(boolean yellow){
        this.yellow = yellow;
    }

    // by amgad elrashid gurashi eltayeb (ill document the comment later)
    public boolean getTurn() {
        return isTurn;
    }

    // by amgad elrashid gurashi eltayeb
    public void setTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public boolean isYellow() {
        return yellow;
    }

    

}
