import java.util.ArrayList;

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
                return nearMinesCount == 0 ? "⬜" : String.valueOf(numbersEmoji.get(nearMinesCount - 1));
            case MINE:
                return "💣";
            default:
                return "?";
        }
    }

    public void incrementNearMinesCount() {
        this.nearMinesCount++;
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
