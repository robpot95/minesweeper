enum TileState {
    NONE,
    REVEALED,
    MINE,
    FLAGGED
}

public class Tile {
    private TileState state;
    private Position position;

    public Tile(TileState state, Position position) {
        this.state = state;
        this.position = position;
    }

    public void setState(TileState state) {
        this.state = state;
    }

    public String getSymbol() {
        switch (state) {
            case NONE:
                return "🔳";
            case REVEALED:
                return "⬜";
            case FLAGGED:
                return "🚩";
            case MINE:
                return "💣";
            default:
                break;
            
        }

        return "";
    }

    public TileState getState() {
        return state;
    }

    public Position getPosition() {
        return position;
    }
}
