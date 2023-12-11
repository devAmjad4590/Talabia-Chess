public abstract class Piece {
    private boolean captured;
    private boolean white;

    public Piece(boolean captured, boolean white) {
        this.captured = captured;
        this.white = white;
    }

    public boolean isCaptured() {
        return captured;
    }

    public boolean isWhite() {
        return white;
    }
    public boolean canMove() {
        //implementation
        return false;
    }
}
