import java.util.ArrayList;

public class Board {
    private Tile[][] board;
    private int size;

    public Board() {
        // Default size
        initBoard(10);
    }

    public Board(int size) {
        initBoard(size);
    }

    private void initBoard(int size) {
        // Create board depending on which size they choose
        this.board = new Tile[size][size];
        this.size = size;
        for (int y = 0; y < size; y++){
            for (int x = 0; x < size; x++) {
                board[y][x] = new Tile(TileState.NONE, new Position(x, y));
            }
        }
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

    public ArrayList<Tile> getTiles() {
        // Lets fetch all the tiles and store them into an Array
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                tiles.add(board[y][x]);
            }
        }

        return tiles;
    }

    public int getSize() {
        return size * size;
    }
}

