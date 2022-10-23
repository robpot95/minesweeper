import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private int size;
    private SortedMap<String, Tile> fields = new TreeMap<String, Tile>();
    private HashSet<Tile> mines = new HashSet<Tile>();
    private ArrayList<Tile> tiles = new ArrayList<Tile>();
    private String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");

    public Board() {
        // Default size
        initBoard(10);
    }

    public Board(int size) {
        initBoard(size);
    }

    private void initBoard(int size) {
        // Create board depending on which size they choose
        this.size = size;
        for (int row = 0; row < size; row++){
            for (int col = 0; col < size; col++) {
                Tile tile = new Tile(new Position(col, row));
                tiles.add(tile);

                // Store the tiles as value in a HashMap and the keys will be e.g a1, a2, a3, b1, b2 depending on board size
                fields.putIfAbsent(alphabet[row % alphabet.length] + (col + 1), tile);
            }
        }

        placeMines(size);
    }

    public void show() {
        for (int i = 0; i < getTiles().size(); i++) {
            if (i != 0 && i % size == 0) {
                System.out.println();
            }

            Tile tile = getTiles().get(i);
            if (i % size + 1 == 0) {
                System.out.print(tile.getSymbol());
            } else {
                System.out.print(tile.getSymbol() + " | ");
            }
        }

        System.out.println("\n");
    }

    /* 
        * We take a different approach here, instead of looping through the board and
        * placing the mines. We are shuffling board size (indexs) around from (0, 1, 2, 3) to e.g (3, 1, 0, 2)
        * This way we avoid looping through the board multiple times and check if a tile has already a mine
    */
    public void placeMines(int amount) {
        // We create a array of index, based on the size of the board. Then we shuffle all the index around
        List<Integer> indexRange = IntStream.rangeClosed(0, tiles.size() - 1).boxed().collect(Collectors.toList());
        Collections.shuffle(indexRange);

        // Here we loop the number of amount and add a mine based on the shuffled indexRange arraylist
        for (int i = 0; i < amount; i++) {
            Tile tile = tiles.get(indexRange.get(i));
            mines.add(tile);
            tile.setState(TileState.MINE);
        }
    }

    public void revealAllMines() {
        for (Tile tile : mines) {
            tile.reveal();
        }
    }

    public SortedMap<String, Tile> getFields() {
        return fields;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public int getSize() {
        return size * size;
    }
}

