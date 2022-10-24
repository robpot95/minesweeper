
//Enum with empty or mined states for the Tile class. This class creates objects of the Type "tile" which have a position
//defined by the class position, a state and can be revealed or flagged controlled by 2 boolean variables.
enum TileState {
    EMPTY,
    MINE
}

public class Tile {
    private Position position;
    private TileState state = TileState.EMPTY;
    private Boolean revealed = false;
    private Boolean flag = false;

    //Constructor for tile object.
    public Tile(Position position) {
        this.position = position;
    }

    public void reveal() {
        revealed = true;
    }
    //Method to add flags to tiles.
    public Boolean addFlag() {
        if (revealed) {
            return false;
        }

        flag = true;
        return true;
    }
    //Constructor for tile state.
    public void setState(TileState state) {
        this.state = state;
    }

    //Method to set string Symbols for the Tiles. Uses a switch to set symbol depending on the tile state.
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
    //Getters for tiles state and position.
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
