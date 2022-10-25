import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/*
    Build the board with a Board class with the variables: size integer for the board size, a Map with type String
    Keys and values of type "Tile" defined in Tile class (Also vice-versa). The mines are stored in a Hashset randomly by shuffling the
    board array list "indexRange" indexes which vary depending on the size.
*/
public class Board {
    private int size;
    private Map<String, Tile> fields = new HashMap<String, Tile>();
    private Map<Position, Tile> positions = new HashMap<Position, Tile>();
    private HashSet<Tile> mines = new HashSet<Tile>();
    private final String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");

    public Board() {
        // Default size
        initBoard(10, 5);
    }

    public Board(int size, int mines) {
        initBoard(size, mines);
    }

    // Method to create board depending on which size chosen by player
    private void initBoard(int size, int mines) {
        this.size = size;
        for (int row = 0; row < size; row++){
            for (int col = 0; col < size; col++) {
                Tile tile = new Tile(new Position(col, row));
                // Store the tiles as value in a HashMap and the keys will be e.g a1, a2, a3, b1, b2 depending on board size
                fields.putIfAbsent(alphabet[row % alphabet.length] + (col + 1), tile);
                positions.putIfAbsent(tile.getPosition(), tile);
            }
        }

        placeMines(mines);
    }

    // Method to display the board with tiles using methods from the tile class.
    public void show() {
        // First we display the column numbers
        for (int colNumber = 0; colNumber < size; colNumber++) {
            System.out.format("%s%d%s", " ".repeat(2), colNumber + 1, colNumber < size - 1 ? "" : "\n");
        }
        
        // Then we display row letters
        for (int row = 0; row < size; row++) {
            String letter = alphabet[row % alphabet.length];
            System.out.print(letter);
            for (int col = 0; col < size; col++) {
                Tile tile = fields.get(letter + (col + 1));
                System.out.print("|" + tile.getSymbol());
            }
            System.out.print("|\n");
        }
    }

    /* 
        * We take a different approach here, instead of looping through the board and
        * placing the mines. We are shuffling board size (indexs) around from (0, 1, 2, 3) to e.g (3, 1, 0, 2)
        * This way we avoid looping through the board multiple times and check if a tile has already a mine
    */
    public void placeMines(int amount) {
        // We create a array of index, based on the size of the board. Then we shuffle all the index around
        ArrayList<Tile> tiles = new ArrayList<Tile>(fields.values());
        List<Integer> indexRange = IntStream.rangeClosed(0, tiles.size() - 1).boxed().collect(Collectors.toList());
        Collections.shuffle(indexRange);

        // Here we loop the number of amount and add a mine based on the shuffled indexRange arraylist
        for (int i = 0; i < amount; i++) {
            Tile tile = tiles.get(indexRange.get(i));
            mines.add(tile);
            tile.setState(TileState.MINE);
        }

        for (Tile mine : mines) {
            Position minePosition = mine.getPosition();
            for (Direction direction : Direction.values()) {
                Tile tile = positions.get(new Position(minePosition.row + direction.position.row, minePosition.col + direction.position.col));
                if (tile != null) {
                    tile.incrementNearMinesCount();
                }
            }
        }
    }

    /*
     * Here we loop through all the tiles.
     * If the tile is not a mine and is not revealed
     * then the game should still continue (user has not won) and we return false
    */
    public Boolean checkWin() {
        for (Tile tile : fields.values()) {
            if (tile.getState() != TileState.MINE && !tile.isRevealed()) {
                return false;
            }
        }

        return true;
    }

    /*
     * Reveals a tile and also recursively its neighbors if the tile has no mine in neighborhood.
     * Tile is revealed only if certain requirements are met
     * If the tile does not have any mine carrying neighbors the reveal operation is spread recursively to
     * all of its neighbors eventually stopping at the tiles near the mine or at the side of the mine field.
    */
    public void revealAdjacentTiles(Tile tile) {
        if (tile.isRevealed() || tile.hasFlag()) {
            return;
        }

        tile.reveal();
        if (tile.getNearMinesCount() != 0) {
            return;
        }

        for (Direction direction : Direction.values()) {
            Tile nextTile = positions.get(new Position(tile.getPosition().row + direction.position.row, tile.getPosition().col + direction.position.col));
            if (nextTile != null) {
                revealAdjacentTiles(nextTile);
            }
        }
    }

    // Method to reveal all the mines
    public void revealAllMines() {
        for (Tile tile : mines) {
            tile.reveal();
        }
    }

    // Method to send Tiles information to the "fields" map
    public Map<String, Tile> getFields() {
        return fields;
    }
}

