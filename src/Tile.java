enum TileState {
    EMPTY,
    MINE
}

public class Tile {
    private Position position;
    private TileState state = TileState.EMPTY;
    private Boolean revealed = false;
    private Boolean flag = false;

    public Tile(Position position) {
        this.position = position;
    }

    public void reveal() {
        revealed = true;
    }

    public Boolean addFlag() {
        if (revealed) {
            return false;
        }

        flag = true;
        return true;
    }

    public void setState(TileState state) {
        this.state = state;
    }

    public String getSymbol() {
        if (!revealed) {
            if (flag) {
                return "🚩";
            }

            return "🔳";
        }

        switch (state) {
            case EMPTY:
                return "⬜";
            case MINE:
                return "💣";
            default:
                return "?";
        }
    }

    public Position getPosition() {
        return position;
    }

    public TileState getState() {
        return state;
    }

    public Boolean hasFlag() {
        return flag;
    }
}
