enum TileState {
    EMPTY,
    MINE,
    FLAGGED
}

public class Tile {
    private TileState state;
    private Boolean revealed;
    private Position position;

    public Tile(Position position) {
        this.state = TileState.EMPTY;
        this.revealed = false;
        this.position = position;
    }

    public void setState(TileState state) {
        this.state = state;
    }

    public void reveal() {
        revealed = true;
    }

    public Boolean flag() {
        if (revealed) {
            return false;
        }

        state = TileState.FLAGGED;
        return true;
    }

    public String getSymbol() {
        if (!revealed && state != TileState.FLAGGED) {
            return "🔳"; 
        }

        switch (state) {
            case EMPTY:
                return "⬜";
            case FLAGGED:
                return "🚩";
            case MINE:
                return "💣";
            default:
                return "?";
        }
    }

    public TileState getState() {
        return state;
    }

    public Position getPosition() {
        return position;
    }
}
