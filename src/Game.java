import java.text.NumberFormat;
import java.util.Scanner;

public class Game {
    private enum GameState {
        INIT,
        STARTED,
        GAMEOVER,
        ENDED
    }

    private Board board;
    private Player player;
    private GameState state = GameState.INIT;
    private final static Scanner userInput = new Scanner(System.in);

    public Game() {
        // Here user can choose board size. Minimum is 2
        int size;
        do {
            System.out.print("Welcome to Minesweeper.\nPlease choose board size:\n>> ");
            size = readNumberFromInput().intValue();
            board = new Board(size);
        } while (size < 2);

        System.out.print("Please write your name:\n>> ");
        player = new Player(userInput.nextLine());
        
        // Change the GameState and start the GameLoop
        state = GameState.STARTED;
        GameLoop();
    }

    private void GameLoop() {
        board.show();
        // Loop the game until the GameState has been changed
        while (state == GameState.STARTED) {
            System.out.print(player.getName() + ", please make your move (f: for flag)\n>> ");

            String userSelection = userInput.nextLine();
            if (userSelection.equals("f")) {
                System.out.print(player.getName() + ", please write which position you would like to flag\n>> ");
                String flagPosition = userInput.nextLine();
                if (board.getFields().containsKey(flagPosition)) { 
                    Boolean canFlag = board.getFields().get(flagPosition).addFlag();
                    if (!canFlag) {
                        System.out.println("You cannot flag this position.");
                        continue;
                    }
                }
            } else {
                if (board.getFields().containsKey(userSelection)) { 
                    Tile tile = board.getFields().get(userSelection);
                    if (tile.hasFlag()) {
                        System.out.print("This tile is flagged, are you sure you want to explore it? (Y/N)\n>> ");
                        if (userInput.nextLine().toLowerCase().equals("n")) {
                            continue;
                        }
                    }

                    tile.reveal();
                    if (tile.getState() == TileState.MINE) {
                        board.revealAllMines();
                        state = GameState.GAMEOVER;
                     
                    }
                }
            }

            board.show();
        }
    }

    // This function will run until a user write a valid number, such as int, double, float, byte and etc ...
    private Number readNumberFromInput() {
        while (true) {
            try {
                return NumberFormat.getInstance().parse(userInput.nextLine());
            } catch (Exception e) {
                System.out.print("Something went wrong, try again.\n>> ");
            }
        }
    }
}
