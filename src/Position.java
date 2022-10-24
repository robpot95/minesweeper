//This class defines a position

import java.util.Objects;

public class Position {
    public final int col;
    public final int row;
    
    public Position(int row, int col) {
        this.col = col;
        this.row = row;
    }

    @Override
    public int hashCode(){
        return Objects.hash(row, col);
    }

    @Override
    public boolean equals(Object o) {
        return this.col == ((Position)o).col && this.row == ((Position)o).row;
    }
}
