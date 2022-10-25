import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    // Enumeration Method with the different game states
    private enum GameState {
        INIT,
        STARTED,
        GAMEOVER,
        ENDED
    }
    private enum GamePlay {
        NONE,
        DEFAULT,
        CUSTOM,
    }

    private Board board;
    private final Player player;
    private GameState state = GameState.INIT;
    private final static Scanner userInput = new Scanner(System.in);
    private final Map<String, Difficulty> difficulties = new LinkedHashMap<String, Difficulty>() {{
        put("easy", new Difficulty(8, 16));
        put("medium", new Difficulty(16, 18));
        put("hard", new Difficulty(24, 21));
    }};

    public Game() {
        /*
         * Here we choose game play we want to play
         * We can also customize the default difficulties
         * User have also the option to customize the board aswell
         */
        GamePlay gameplay = GamePlay.NONE;
        do {
            System.out.print("Welcome to Minesweeper.\nPlease choose your gameplay:\n1. Difficulties options\n2. Custom option\n>> ");
            gameplay = GamePlay.values()[readNumberFromInput().intValue() % GamePlay.values().length];
        } while (gameplay == GamePlay.NONE);

        switch (gameplay) {
            case DEFAULT:
                Difficulty difficulty;
                do {
                    System.out.print("Which difficulty would you like to play?\n" + String.join("\n", difficulties.keySet()) + "\n>> ");
                    difficulty = difficulties.get(userInput.nextLine());
                    if (difficulty != null) {
                        board = new Board(difficulty.getSize(), difficulty.getPercentage());
                    }
                } while (difficulty == null);
                break;
            case CUSTOM:
                int size, mines;
                do {
                    System.out.print("Please choose board size:\n>> ");
                    size = readNumberFromInput().intValue();
                    System.out.print("Please choose the amount of mines:\n>> ");
                    mines = readNumberFromInput().intValue();
                    board = new Board(size, mines);
                } while (size < 2 && mines > 0);
                break;
            default:
                break;
        }

        System.out.print("Please write your name:\n>> ");
        player = new Player(userInput.nextLine());
        
        // Change the GameState and start the GameLoop
        state = GameState.STARTED;
        gameLoop();
    }
    
    // Method to Loop the game until the GameState has been changed
    private void gameLoop() {
        board.show();

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
