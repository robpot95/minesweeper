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
                board[y][x] = new Tile();
            }
        }
    }

    public void show() {
        /*for(int y = 0; y < board.length; y++){
            for(int x = 0; x < board[y].length; x++){
                System.out.print(board[y][x] + " | ");
            }
            System.out.println();
            System.out.println("--+---+---+---+---+----");
        }*/
    }

    public int getSize() {
        return size * size;
    }
}

