public class Board {

    private String[][] minesBoard;
    private String [][] userBoard;

    public Board() {
        this.minesBoard = new String[6][6];
        this.userBoard = new String [6][6];
    }

    public void userBoard(){
        for(int i = 0; i<userBoard.length; i++){
            for(int j = 0; j< userBoard[i].length; j++){
                userBoard[i][j] = "?";
            }
        }
    }

    public void print(){
        userBoard();
        for(int i = 0; i<userBoard.length; i++){
            for(int j = 0; j< userBoard[i].length; j++){
                System.out.print(userBoard[i][j] + " | ");
            }
            System.out.println();
            System.out.println("--+---+---+---+---+----");
        }
    }
}

