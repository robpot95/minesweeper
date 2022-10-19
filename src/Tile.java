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

    public TileState getState() {
        return state;
    }

    public Position getPosition() {
        return position;
    }
}
