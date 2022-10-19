import java.text.NumberFormat;
import java.util.Scanner;

public class Game {
    enum GameState {
        INIT,
        STARTED,
        ENDED,
    }

    private Board board;
    private Player player;
    private GameState state = GameState.INIT;
    private final static Scanner userInput = new Scanner(System.in);

    public Game() {
        // Here we write what size of the board we want. Minimum is 2
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
        }
    }

    private Number readNumberFromInput() {
        // We run this function until user write a number, Also this function accept double, float, byte and etc ...
        while (true) {
            try {
                return NumberFormat.getInstance().parse(userInput.nextLine());
            } catch (Exception e) {
                System.out.print("Something went wrong, try again.\n>> ");
            }
        }
    }
}
