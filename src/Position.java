//This class defines a position
import java.util.Objects;

public class Position {
    public final int row;
    public final int col;
    
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public int hashCode(){
        return Objects.hash(row, col);
    }

    @Override
    public boolean equals(Object o) {
        return this.row == ((Position)o).row && this.col == ((Position)o).col;
    }
}
