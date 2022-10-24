import java.util.ArrayList;

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
    private int nearMinesCount = 0;
    private static final ArrayList<String> numbersEmoji = new ArrayList<String>() {
        {
            add("1️⃣ ");
            add("2️⃣ ");
            add("3️⃣ ");
            add("4️⃣ ");
            add("5️⃣ ");
            add("6️⃣ ");
            add("7️⃣ ");
            add("8️⃣ ");
        }
    };


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

    // Method to return string Symbols for the Tiles. Uses a switch to return symbol depending on the tile state.
    public String getSymbol() {
        if (!revealed) {
            if (flag) {
                return "🚩";
            }

            return "🔳";
        }

        switch (state) {
            case EMPTY:
                return nearMinesCount == 0 ? "⬜" : String.valueOf(numbersEmoji.get(nearMinesCount - 1));
            case MINE:
                return "💣";
            default:
                return "?";
        }
    }

    public void incrementNearMinesCount(){
        nearMinesCount++;
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
